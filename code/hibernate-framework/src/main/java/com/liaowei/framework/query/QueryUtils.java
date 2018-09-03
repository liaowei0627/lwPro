package com.liaowei.framework.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.page.Pagination.OrderEnum;
import com.liaowei.framework.query.operator.CollectionValueComparisonOperator;
import com.liaowei.framework.query.operator.NoValueComparisonOperator;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.query.operator.PredicateOperator;
import com.liaowei.framework.query.operator.TwoValueComparisonOperator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * QueryUtils
 *
 * HQL查询器构建工具
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-02 00:06:38
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class QueryUtils {

    public static <E extends IBasisIdEntity<E>> Query<Long> createCountQuery(Class<E> entityClass, Session session, Where where) {
        log.debug("DEBUG：HQL总数查询器构建");
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from " + entityClass.getSimpleName() + " " + Where.ALIAS + " ");

        Map<String, Object> param = null;
        if (null != where) {
            WhereClause whereClause = toWhereClause(where);
            param = whereClause.getParam();
            hql.append(whereClause.getWhereClause());
        }

        log.debug("DEBUG：HQL：" + hql.toString());
        Query<Long> query = session.createQuery(hql.toString(), Long.class);
        if (null != param && !param.isEmpty()) {
            Set<String> paramKeySet = param.keySet();
            for (String key : paramKeySet) {
                query.setParameter(key, param.get(key));
            }
        }

        return query;
    }

    public static <E extends IBasisIdEntity<E>> Query<E> createQuery(Pagination<E> pagination, Class<E> entityClass, Session session, Where where) {
        log.debug("DEBUG：HQL列表查询器构建");
        StringBuilder hql = new StringBuilder();
        hql.append("from " + entityClass.getSimpleName() + " " + Where.ALIAS + " ");

        Map<String, Object> param = null;
        if (null != where) {
            WhereClause whereClause = toWhereClause(where);
            param = whereClause.getParam();
            hql.append(whereClause.getWhereClause());
        }

        if (null != pagination) {
            Map<String, OrderEnum> orderBy = pagination.getOrderBy();
            if (null != orderBy && !orderBy.isEmpty()) {
                hql.append("order by ");
                Set<String> orderKeySet = orderBy.keySet();
                StringBuilder orderbyClause = new StringBuilder();
                for (String key : orderKeySet) {
                    if (orderbyClause.length() > 0) {
                        orderbyClause.append(",");
                    }
                    orderbyClause.append(Where.ALIAS + "." + key + " " + orderBy.get(key).getText());
                }
                hql.append(orderbyClause);
            }
        }

        log.debug("DEBUG：HQL：" + hql.toString());
        Query<E> query = session.createQuery(hql.toString(), entityClass);

        if (null != param && !param.isEmpty()) {
            Set<String> paramKeySet = param.keySet();
            for (String key : paramKeySet) {
                query.setParameter(key, param.get(key));
            }
        }

        if (null != pagination && pagination.getPageable().booleanValue()) {
            int startPosition = pagination.getStartPosition();
            if (0 < startPosition) {
                query.setFirstResult(startPosition);
            }
            query.setMaxResults(pagination.getRows());
        }
        
        return query;
    }

    /**
     * 将Where对象输出成where语句和条件map
     * 
     * @return
     */
    public static WhereClause toWhereClause(Where where) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        sb.append("where ");

        // 解析当前对象
        WhereClause wc = assemble(where);
        sb.append(wc.getWhereClause());
        param.putAll(wc.getParam());

        WhereClause whereClause = new WhereClause(sb.toString(), param);
        return whereClause;
    }

    /**
     * 组装条件对象
     * 
     * @param where
     * @return
     */
    private static WhereClause assemble(Where where) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> param = new HashMap<>();

        // 当前对象条件
        String propertyName = where.getPropertyName();
        Object value = where.getValue();
        String paramKey = Where.ALIAS + "_" + propertyName.replace(".", "_") + "_" + String.valueOf(new Date().getTime());
        Enum<?> operatorEnum = where.getOperator();
        if (operatorEnum instanceof NoValueComparisonOperator) {
            NoValueComparisonOperator operator = (NoValueComparisonOperator) operatorEnum;
            sb.append(Where.ALIAS + "." + propertyName + " " + operator.getText() + " ");
        } else if (operatorEnum instanceof OneValueComparisonOperator) {
            OneValueComparisonOperator operator = (OneValueComparisonOperator) operatorEnum;
            if (operator.equals(OneValueComparisonOperator.MEMBER_OF) || operator.equals(OneValueComparisonOperator.NOT_MEMBER_OF)) {
                sb.append(":" + paramKey + " " + operator.getText() + " " + Where.ALIAS + "." + propertyName + " ");
                param.put(paramKey, value);
            } else {
                sb.append(Where.ALIAS + "." + propertyName + " " + operator.getText() + " ");
                if (operator.equals(OneValueComparisonOperator.LIKE)) {
                    sb.append(":" + paramKey + " ");
                    param.put(paramKey, "%" + value + "%");
                } else {
                    sb.append(":" + paramKey + " ");
                    param.put(paramKey, value);
                }
            }
        } else if (operatorEnum instanceof TwoValueComparisonOperator) {
            TwoValueComparisonOperator operator = (TwoValueComparisonOperator) operatorEnum;
            sb.append(Where.ALIAS + "." + propertyName + " " + operator.getText() + " ");
            sb.append(":" + paramKey + " and :" + paramKey + "_2 ");
            param.put(paramKey, value);
            param.put(paramKey + "_2", where.getValue2());
        } else if (operatorEnum instanceof CollectionValueComparisonOperator) {
            CollectionValueComparisonOperator operator = (CollectionValueComparisonOperator) operatorEnum;
            sb.append(Where.ALIAS + "." + propertyName + " " + operator.getText() + " ");
            sb.append("(:" + paramKey + ") ");
            param.put(paramKey, value);
        }

        // nextWhere
        Where nextWhere = where.getNextWhere();
        if (null != nextWhere) {
            sb.append(where.getNextWhereOperator().getText() + " ");
            WhereClause wc1 = assemble(nextWhere);
            sb.append(wc1.getWhereClause());
            param.putAll(wc1.getParam());
        }

        // 括号条件集合
        List<Map<PredicateOperator, Where>> children = where.getChildrenWhere();
        if (!children.isEmpty()) {
            WhereClause wc2 = assembleChildren(children);
            sb.append(wc2.getWhereClause());
            param.putAll(wc2.getParam());
        }

        WhereClause wc = new WhereClause(sb.toString(), param);
        return wc;
    }

    private static WhereClause assembleChildren(List<Map<PredicateOperator, Where>> children) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        WhereClause whereClause = null;
        if (!children.isEmpty()) {
            WhereClause wc;
            for (Map<PredicateOperator, Where> child : children) {
                wc = assemble(child);
                sb.append(wc.getWhereClause());
                param.putAll(wc.getParam());
            }
            whereClause = new WhereClause(sb.toString(), param);
        }
        return whereClause;
    }

    private static WhereClause assemble(Map<PredicateOperator, Where> child) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        PredicateOperator operator = child.keySet().iterator().next();
        sb.append(operator.getText() + " ");
        sb.append("(");
        Where where = child.get(operator);
        WhereClause wc = assemble(where);
        sb.append(wc.getWhereClause());
        sb.append(")");
        param.putAll(wc.getParam());
        WhereClause whereClause = new WhereClause(sb.toString(), param);
        return whereClause;
    }
}