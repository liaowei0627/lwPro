/**
 * flyhaze-framework
 * WhereClauseException.java
 */
package com.flyhaze.framework.core.query.exception;

import com.flyhaze.framework.core.exception.ApplicationException;

/**
 * WhereClauseException
 *
 * HQL Where条件对象组装异常
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-01 12:05:36
 * @see com.flyhaze.framework.core.exception.ApplicationException
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class WhereClauseException extends ApplicationException {

    public static final String NEXT_WHERE_EXISTS = "nextWhere已存在，请使用andChildWhere或orChildWhere方法添加带括号的后续条件";
    public static final String CHILD_WHERE_EXISTS = "childWhere已存在，请使用childWhere的andChildWhere或orChildWhere方法添加带括号的后续条件";
    public static final String VALUE_IS_COLLECTION = "值不能是集合或数组";
    public static final String VALUE_NOT_COLLECTION = "值只能是集合或数组";

    public WhereClauseException() {
        super();
    }

    public WhereClauseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WhereClauseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WhereClauseException(String message) {
        super(message);
    }

    public WhereClauseException(Throwable cause) {
        super(cause);
    }
}