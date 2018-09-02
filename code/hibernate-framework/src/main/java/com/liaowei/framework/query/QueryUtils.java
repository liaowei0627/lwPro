package com.liaowei.framework.query;

import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.liaowei.framework.core.entity.IBasisIdEntity;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.page.Pagination.OrderEnum;

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

    public static <E extends IBasisIdEntity> Query<Long> createCountQuery(Class<E> entityClass, Session session, Where where) {
        log.debug("HQL总数查询器构建");
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from " + entityClass.getSimpleName() + " " + Where.ALIAS + " ");

        Map<String, Object> param = null;
        if (null != where) {
            WhereClause whereClause = where.toWhereClause();
            param = whereClause.getParam();
            hql.append(whereClause.getWhereClause());
        }

        Query<Long> query = session.createQuery(hql.toString(), Long.class);
        if (null != param && !param.isEmpty()) {
            Set<String> paramKeySet = param.keySet();
            for (String key : paramKeySet) {
                query.setParameter(key, param.get(key));
            }
        }

        return query;
    }

    public static <E extends IBasisIdEntity> Query<E> createQuery(Pagination<E> pagination, Class<E> entityClass, Session session, Where where) {
        log.debug("HQL列表查询器构建");
        StringBuilder hql = new StringBuilder();
        hql.append("from " + entityClass.getSimpleName() + " " + Where.ALIAS + " ");

        Map<String, Object> param = null;
        if (null != where) {
            WhereClause whereClause = where.toWhereClause();
            param = whereClause.getParam();
            hql.append(whereClause.getWhereClause());
        }

        if (null != pagination) {
            Map<String, OrderEnum> orderBy = pagination.getOrderBy();
            if (null != orderBy && !orderBy.isEmpty()) {
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

        Query<E> query = session.createQuery(hql.toString(), entityClass);

        if (null != param && !param.isEmpty()) {
            Set<String> paramKeySet = param.keySet();
            for (String key : paramKeySet) {
                query.setParameter(key, param.get(key));
            }
        }

        if (null != pagination && pagination.isPageable()) {
            query.setFirstResult(pagination.getStartPosition());
            query.setMaxResults(pagination.getRows());
        }
        
        return query;
    }
}