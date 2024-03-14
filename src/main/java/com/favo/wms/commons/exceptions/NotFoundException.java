package com.favo.wms.commons.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{

    private static final long serialVersionUID = 1814384303470046879L;

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }


    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
