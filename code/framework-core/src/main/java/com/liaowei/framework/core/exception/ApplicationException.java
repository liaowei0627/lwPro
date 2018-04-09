/**
 * framework-core
 * ApplicationException.java
 */
package com.liaowei.framework.core.exception;

/**
 * ApplicationException
 *
 * 自定义应用程序异常
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:25:25
 * @see java.lang.Exception
 * @since jdk1.8
 */
public class ApplicationException extends Exception {

    private static final long serialVersionUID = 1427673274640746353L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}