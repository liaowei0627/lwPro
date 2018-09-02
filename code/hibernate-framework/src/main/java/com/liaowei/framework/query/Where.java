package com.liaowei.framework.query;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liaowei.framework.query.exception.WhereClauseException;
import com.liaowei.framework.query.operator.CollectionValueComparisonOperator;
import com.liaowei.framework.query.operator.NoValueComparisonOperator;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.query.operator.PredicateOperator;
import com.liaowei.framework.query.operator.TwoValueComparisonOperator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Where
 *
 * HQL Where条件语句
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-08-31 22:25:33
 * @see com.liaowei.framework.query.IBasisWhere
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Where implements IBasisWhere {

    /** 别名 */
    public static final String ALIAS = "t";

    /** 属性名 */
    private String propertyName;
    /** 条件操作符 */
    private Enum<?> operator;
    /** 值 */
    private Object value;
    /** 值2（可选，between这种需要两个值的条件时使用） */
    private Object value2;

    /**
     * 后续查询条件操作符 只能是Operator.AND或Operator.OR
     */
    private PredicateOperator nextWhereOperator;

    /**
     * 后续查询条件
     */
    private Where nextWhere;

    /**
     * 后续括号里的查询条件 list的每个元素都是一组括号包括的条件语句 key操作符只能是Operator.AND或Operator.OR
     */
    private List<Map<PredicateOperator, Where>> childrenWhere = Lists.newArrayList();

    private Where(String propertyName, NoValueComparisonOperator operator) {
        this.propertyName = propertyName;
        this.operator = operator;
    }

    private Where(String propertyName, OneValueComparisonOperator operator, Object value) throws WhereClauseException {
        if (Where.isCollection(value)) {
            throw new WhereClauseException(WhereClauseException.VALUE_IS_COLLECTION);
        }
        this.propertyName = propertyName;
        this.operator = operator;
        this.value = value;
    }

    private Where(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2) {
        this.propertyName = propertyName;
        this.operator = operator;
        this.value = value;
        this.value2 = value2;
    }

    private Where(String propertyName, CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        if (!Where.isCollection(value)) {
            throw new WhereClauseException(WhereClauseException.VALUE_NOT_COLLECTION);
        }
        this.propertyName = propertyName;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 创建Where对象，并构建第一个查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @return
     * @throws WhereClauseException
     */
    public static Where rootWhere(String propertyName, NoValueComparisonOperator operator) throws WhereClauseException {
        Where where = new Where(propertyName, operator);
        return where;
    }

    /**
     * 创建Where对象，并构建第一个查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public static Where rootWhere(String propertyName, OneValueComparisonOperator operator, Object value) throws WhereClauseException {
        Where where = new Where(propertyName, operator, value);
        return where;
    }

    /**
     * 创建Where对象，并构建第一个查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public static Where rootWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2)
            throws WhereClauseException {
        Where where = new Where(propertyName, operator, value, value2);
        return where;
    }

    /**
     * 创建Where对象，并构建第一个查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public static Where rootWhere(String propertyName, CollectionValueComparisonOperator operator, Object value)
            throws WhereClauseException {
        Where where = new Where(propertyName, operator, value);
        return where;
    }

    /**
     * 添加一个and后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @return
     * @throws WhereClauseException
     */
    public void andWhere(String propertyName, NoValueComparisonOperator operator) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.AND, propertyName, operator);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个and后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public void andWhere(String propertyName, OneValueComparisonOperator operator, Object value) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.AND, propertyName, operator, value);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个and后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public void andWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.AND, propertyName, operator, value, value2);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个and后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public void andWhere(String propertyName, CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.AND, propertyName, operator, value);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个or后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @return
     * @throws WhereClauseException
     */
    public void orWhere(String propertyName, NoValueComparisonOperator operator) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.OR, propertyName, operator);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个or后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public void orWhere(String propertyName, OneValueComparisonOperator operator, Object value) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.OR, propertyName, operator, value);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个or后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public void orWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.OR, propertyName, operator, value, value2);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个or后续查询条件
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return
     * @throws WhereClauseException
     */
    public void orWhere(String propertyName, CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        if (null == nextWhere) {
            addNextWhere(PredicateOperator.OR, propertyName, operator, value);
        } else {
            throw new WhereClauseException(WhereClauseException.NEXT_WHERE_EXISTS);
        }
    }

    /**
     * 添加一个and后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childAndWhere(String propertyName, NoValueComparisonOperator operator) throws WhereClauseException {
        return addChildWhere(PredicateOperator.AND, propertyName, operator);
    }

    /**
     * 添加一个and后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childAndWhere(String propertyName, OneValueComparisonOperator operator, Object value) throws WhereClauseException {
        return addChildWhere(PredicateOperator.AND, propertyName, operator, value);
    }

    /**
     * 添加一个and后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @param value 值
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childAndWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2) throws WhereClauseException {
        return addChildWhere(PredicateOperator.AND, propertyName, operator, value, value2);
    }

    /**
     * 添加一个and后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childAndWhere(String propertyName, CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        return addChildWhere(PredicateOperator.AND, propertyName, operator, value);
    }

    /**
     * 添加一个or后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childOrWhere(String propertyName, NoValueComparisonOperator operator) throws WhereClauseException {
        return addChildWhere(PredicateOperator.OR, propertyName, operator);
    }

    /**
     * 添加一个or后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childOrWhere(String propertyName, OneValueComparisonOperator operator, Object value) throws WhereClauseException {
        return addChildWhere(PredicateOperator.OR, propertyName, operator, value);
    }

    /**
     * 添加一个or后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @param value 值
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childOrWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2) throws WhereClauseException {
        return addChildWhere(PredicateOperator.OR, propertyName, operator, value, value2);
    }

    /**
     * 添加一个or后面括号里的条件语句
     * 
     * @param propertyName 属性名
     * @param operator 操作符
     * @param value 值
     * @return 这个新增的条件语句对象
     * @throws WhereClauseException
     */
    public Where childOrWhere(String propertyName, CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        return addChildWhere(PredicateOperator.OR, propertyName, operator, value);
    }

    /**
     * 将当前Where对象输出成where语句和条件map
     * 
     * @return
     */
    public WhereClause toWhereClause() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        sb.append("where ");

        // 解析当前对象
        WhereClause wc = assemble(this);
        sb.append(wc.getWhereClause());
        param.putAll(wc.getParam());

        WhereClause whereClause = new WhereClause(sb.toString(), param);
        return whereClause;
    }

    /**
     * 判断值是否是集合或数组
     * 
     * @param value
     * @return
     */
    private static boolean isCollection(Object value) {
        if (value instanceof Collection<?> || value instanceof Object[]) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 添加一个后续查询条件
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @throws WhereClauseException
     */
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName, NoValueComparisonOperator operator)
            throws WhereClauseException {
        nextWhereOperator = predicateOperator;
        nextWhere = new Where(propertyName, operator);
    }

    /**
     * 添加一个后续查询条件
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @param value 值
     * @throws WhereClauseException
     */
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName, OneValueComparisonOperator operator, Object value)
            throws WhereClauseException {
        nextWhereOperator = predicateOperator;
        nextWhere = new Where(propertyName, operator, value);
    }

    /**
     * 添加一个后续查询条件
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @param value 值
     * @param value 值
     * @throws WhereClauseException
     */
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName, TwoValueComparisonOperator operator, Object value,
            Object value2) throws WhereClauseException {
        nextWhereOperator = predicateOperator;
        nextWhere = new Where(propertyName, operator, value, value2);
    }

    /**
     * 添加一个后续查询条件
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @param value 值
     * @throws WhereClauseException
     */
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName, CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        nextWhereOperator = predicateOperator;
        nextWhere = new Where(propertyName, operator, value);
    }

    /**
     * 添加一个括号里的条件语句
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @throws WhereClauseException
     */
    private Where addChildWhere(PredicateOperator predicateOperator, String propertyName, NoValueComparisonOperator operator)
            throws WhereClauseException {
        Map<PredicateOperator, Where> child = Maps.newHashMap();
        Where childWhere = new Where(propertyName, operator);
        child.put(predicateOperator, childWhere);
        childrenWhere.add(child);
        return childWhere;
    }

    /**
     * 添加一个括号里的条件语句
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @param value 值
     * @throws WhereClauseException
     */
    private Where addChildWhere(PredicateOperator predicateOperator, String propertyName, OneValueComparisonOperator operator,
            Object value) throws WhereClauseException {
        Map<PredicateOperator, Where> child = Maps.newHashMap();
        Where childWhere = new Where(propertyName, operator, value);
        child.put(predicateOperator, childWhere);
        childrenWhere.add(child);
        return childWhere;
    }

    /**
     * 添加一个括号里的条件语句
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @param value 值
     * @param value 值
     * @throws WhereClauseException
     */
    private Where addChildWhere(PredicateOperator predicateOperator, String propertyName, TwoValueComparisonOperator operator,
            Object value, Object value2) throws WhereClauseException {
        Map<PredicateOperator, Where> child = Maps.newHashMap();
        Where childWhere = new Where(propertyName, operator, value, value2);
        child.put(predicateOperator, childWhere);
        childrenWhere.add(child);
        return childWhere;
    }

    /**
     * 添加一个括号里的条件语句
     * 
     * @param predicateOperator where操作符
     * @param propertyName 属性名
     * @param operator 条件操作符
     * @param value 值
     * @throws WhereClauseException
     */
    private Where addChildWhere(PredicateOperator predicateOperator, String propertyName, CollectionValueComparisonOperator operator,
            Object value) throws WhereClauseException {
        Map<PredicateOperator, Where> child = Maps.newHashMap();
        Where childWhere = new Where(propertyName, operator, value);
        child.put(predicateOperator, childWhere);
        childrenWhere.add(child);
        return childWhere;
    }

    /**
     * 组装条件对象
     * 
     * @param where
     * @return
     */
    private WhereClause assemble(Where where) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> param = new HashMap<>();

        // 当前对象条件
        String paramKey = Where.ALIAS + "_" + where.propertyName.replace(".", "_") + "_" + String.valueOf(new Date().getTime());
        Enum<?> operatorEnum = where.operator;
        if (operatorEnum instanceof NoValueComparisonOperator) {
            NoValueComparisonOperator operator = (NoValueComparisonOperator) operatorEnum;
            sb.append(Where.ALIAS + "." + where.propertyName + " " + operator.getText() + " ");
        } else if (operatorEnum instanceof OneValueComparisonOperator) {
            OneValueComparisonOperator operator = (OneValueComparisonOperator) operatorEnum;
            if (operator.equals(OneValueComparisonOperator.MEMBER_OF) || operator.equals(OneValueComparisonOperator.NOT_MEMBER_OF)) {
                sb.append(":" + paramKey + " " + operator.getText() + " " + Where.ALIAS + "." + where.propertyName + " ");
                param.put(paramKey, where.value);
            } else {
                sb.append(Where.ALIAS + "." + where.propertyName + " " + operator.getText() + " ");
                if (operator.equals(OneValueComparisonOperator.LIKE) || operator.equals(OneValueComparisonOperator.NOT_LIKE)) {
                    sb.append(":" + paramKey + " ");
                    param.put(paramKey, "%" + where.value + "%");
                } else {
                    sb.append(":" + paramKey + " ");
                    param.put(paramKey, where.value);
                }
            }
        } else if (operatorEnum instanceof TwoValueComparisonOperator) {
            TwoValueComparisonOperator operator = (TwoValueComparisonOperator) operatorEnum;
            sb.append(Where.ALIAS + "." + where.propertyName + " " + operator.getText() + " ");
            sb.append(":" + paramKey + " and :" + paramKey + "_2 ");
            param.put(paramKey, where.value);
            param.put(paramKey + "_2", where.value2);
        } else if (operatorEnum instanceof CollectionValueComparisonOperator) {
            CollectionValueComparisonOperator operator = (CollectionValueComparisonOperator) operatorEnum;
            sb.append(Where.ALIAS + "." + where.propertyName + " " + operator.getText() + " ");
            sb.append("(:" + paramKey + ") ");
            param.put(paramKey, where.value);
        }

        // nextWhere
        Where nextWhere = where.nextWhere;
        if (null != nextWhere) {
            sb.append(where.nextWhereOperator.getText() + " ");
            WhereClause wc1 = assemble(nextWhere);
            sb.append(wc1.getWhereClause());
            param.putAll(wc1.getParam());
        }

        // 括号条件集合
        List<Map<PredicateOperator, Where>> children = where.childrenWhere;
        if (!children.isEmpty()) {
            WhereClause wc2 = assembleChildren(children);
            sb.append(wc2.getWhereClause());
            param.putAll(wc2.getParam());
        }

        WhereClause wc = new WhereClause(sb.toString(), param);
        return wc;
    }

    private WhereClause assembleChildren(List<Map<PredicateOperator, Where>> children) {
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

    private WhereClause assemble(Map<PredicateOperator, Where> child) {
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