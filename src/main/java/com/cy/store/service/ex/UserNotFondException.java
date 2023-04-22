package com.cy.store.service.ex;
//用户没有找到产生异常
public class UserNotFondException extends ServiceException{
    public UserNotFondException() {
        super();
    }

    public UserNotFondException(String message) {
        super(message);
    }

    public UserNotFondException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFondException(Throwable cause) {
        super(cause);
    }

    protected UserNotFondException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
