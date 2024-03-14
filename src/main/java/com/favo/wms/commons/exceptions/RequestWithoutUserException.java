package com.favo.wms.commons.exceptions;

import org.springframework.http.HttpStatus;

public class RequestWithoutUserException extends Exception{

    private static final long serialVersionUID = 1814384303470046879L;

    public static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public RequestWithoutUserException() {
        super();
    }

    public RequestWithoutUserException(String message) {
        super(message);
    }


    public RequestWithoutUserException(String message, Throwable cause) {
        super(message, cause);
    }


    public RequestWithoutUserException(Throwable cause) {
        super(cause);
    }

}
