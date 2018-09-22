/**
 * flyhaze-framework
 * Where.java
 */
package com.flyhaze.framework.core.query;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.flyhaze.framework.core.query.exception.WhereClauseException;
import com.flyhaze.framework.core.query.operator.CollectionValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.NoValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.PredicateOperator;
import com.flyhaze.framework.core.query.operator.TwoValueComparisonOperator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Where
 *
 * HQL Where条件语句
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-08-31 22:25:33
 * @since jdk1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class Where {

    /**
     * 默认别名
     */
    public static final String DEFAULT_ALIAS = "t";

    /**
     * 查询主表别名
     */
    private String alias;

    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 条件操作符
     */
    private Enum<?> operator;
    /**
     * 值
     */
    private Object value;
    /**
     * 值2（可选，between这种需要两个值的条件时使用）
     */
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

    public void setAlias(String alias) {
        this.alias = alias;
        
    }

    public String getAlias() {
        if (Strings.isNullOrEmpty(alias)) {
            return DEFAULT_ALIAS;
        }
        return alias;
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
    public static Where rootWhere(String propertyName, OneValueComparisonOperator operator, Object value)
            throws WhereClauseException {
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
    public void andWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2)
            throws WhereClauseException {
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
    public void andWhere(String propertyName, CollectionValueComparisonOperator operator, Object value)
            throws WhereClauseException {
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
    public void orWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2)
            throws WhereClauseException {
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
    public void orWhere(String propertyName, CollectionValueComparisonOperator operator, Object value)
            throws WhereClauseException {
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
    public Where childAndWhere(String propertyName, OneValueComparisonOperator operator, Object value)
            throws WhereClauseException {
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
    public Where childAndWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2)
            throws WhereClauseException {
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
    public Where childAndWhere(String propertyName, CollectionValueComparisonOperator operator, Object value)
            throws WhereClauseException {
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
    public Where childOrWhere(String propertyName, OneValueComparisonOperator operator, Object value)
            throws WhereClauseException {
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
    public Where childOrWhere(String propertyName, TwoValueComparisonOperator operator, Object value, Object value2)
            throws WhereClauseException {
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
    public Where childOrWhere(String propertyName, CollectionValueComparisonOperator operator, Object value)
            throws WhereClauseException {
        return addChildWhere(PredicateOperator.OR, propertyName, operator, value);
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
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName, OneValueComparisonOperator operator,
            Object value) throws WhereClauseException {
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
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName, TwoValueComparisonOperator operator,
            Object value, Object value2) throws WhereClauseException {
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
    private void addNextWhere(PredicateOperator predicateOperator, String propertyName,
            CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
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
    private Where addChildWhere(PredicateOperator predicateOperator, String propertyName,
            CollectionValueComparisonOperator operator, Object value) throws WhereClauseException {
        Map<PredicateOperator, Where> child = Maps.newHashMap();
        Where childWhere = new Where(propertyName, operator, value);
        child.put(predicateOperator, childWhere);
        childrenWhere.add(child);
        return childWhere;
    }
}