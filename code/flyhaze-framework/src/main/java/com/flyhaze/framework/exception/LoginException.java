/**
 * flyhaze-framework
 * LoginException.java
 */
package com.flyhaze.framework.exception;

import com.flyhaze.framework.core.exception.ApplicationException;

/**
 * LoginException
 *
 * 登录异常
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-09-23 10:07:54
 * @see com.flyhaze.framework.core.exception.ApplicationException
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public class LoginException extends ApplicationException {

    public static final String PASSWORD_WRONG = "密码错误！";

    public LoginException() {
        super();
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }
}