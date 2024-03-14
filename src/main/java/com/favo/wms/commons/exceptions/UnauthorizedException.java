package com.favo.wms.commons.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends Exception{

    private static final long serialVersionUID = 1814384303470046879L;

    public static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }


    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }


    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

}
