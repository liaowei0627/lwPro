/**
 * flyhaze-framework
 * DuplicationTreeCodeException.java
 */
package com.flyhaze.framework.core.query.exception;

import com.flyhaze.framework.core.exception.ApplicationException;

/**
 * DuplicationCodeException
 *
 * 编号重复异常
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-12 23:16:27
 * @see com.flyhaze.framework.core.exception.ApplicationException
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class DuplicationCodeException extends ApplicationException {

    public static final String DUPLICATION_CODE = "编号重复";
    public static final String DUPLICATION_FULL_CODE = "全路径编号重复";

    public DuplicationCodeException() {
        super();
    }

    public DuplicationCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DuplicationCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicationCodeException(String message) {
        super(message);
    }

    public DuplicationCodeException(Throwable cause) {
        super(cause);
    }
}