package com.favo.wms.commons.controller;

import javax.servlet.http.HttpServletRequest;

import com.favo.wms.commons.exceptions.*;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.favo.wms.commons.payload.HeaderAttributes;
import com.favo.wms.commons.vo.ErrorLog;

public class CommonController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	private Environment env;
	
	protected static final Logger console = LoggerFactory.getLogger(CommonController.class);
	
	protected ResponseEntity<?> throwExceptionResponse(BusinessException e) {
    	console.warn(e.getMessage(), e);
    	return this.buildResponse(this.buildMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<?> throwExceptionResponse(BusinessException e, HeaderAttributes headers) {
    	console.warn( buildExceptionInfo( e, headers ) );
    	return this.buildResponse(this.buildMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<?> throwExceptionResponse(TechnicalException e) {
    	console.error(e.getMessage(), e);
    	return this.buildResponse(this.buildMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected ResponseEntity<?> throwExceptionResponse(TechnicalException e, HeaderAttributes headers) {
		console.warn( buildExceptionInfo( e, headers ) );
    	return this.buildResponse(this.buildMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private String buildExceptionInfo(Exception e, HeaderAttributes headers) {
		return new ErrorLog( headers.getSessionId()
							, e.getClass().getSimpleName()
							, e.getMessage()
							, env.getProperty("info.app.name")
							, env.getProperty("info.app.version") 
						).toString();
	}
	
    /**
     * Checks if sponsored user has sended
     * @param sponsor
     * @return
     */
    protected boolean validateAuthorization(Integer sponsor){
        return sponsor != null && sponsor.compareTo(0) > 0  ? true : false;
    }

    /**
     * Gets user id from http header request
     * @param request
     * @return user id
     */
    protected Integer getSponsorFrom(HttpServletRequest request) throws RequestWithoutUserException {
        
        try {
            return Integer.valueOf(request.getHeader("X-App-UserId")); 
        } catch (Exception e) {
            throw new RequestWithoutUserException("user_id_is_required");
        }
    }
    
    /**
     * Build an not authorized error message
     * @return
     */
    protected ObjectNode buildNotAuthorizedMessage(){
        return new ObjectMapper().createObjectNode().put("message", "Not authorized.");
    }

    /**
     * Build a generic message with sended content
     * @param message
     * @return generic message
     */
    protected ObjectNode buildMessage(String message){
        return new ObjectMapper().createObjectNode().put("message", message);
    }

    /**
     * Build a generic response
     * @param message
     * @param status
     * @return response
     */
    protected ResponseEntity<?> buildResponse(Object message, HttpStatus status){
        return new ResponseEntity<Object>(message, status);
    }
	
    /**
     * Provides an object with header parameters.
     * @return HeaderAttributes
     */
    protected HeaderAttributes getHeaders() {
    	return new HeaderAttributes( request );
    }
    
	@ExceptionHandler( BusinessException.class )
	public ResponseEntity<?> handleBusinessException( HttpServletRequest request, BusinessException e ) {
    	console.warn(e.getMessage(), e);
    	return this.buildResponse(this.buildMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler( TechnicalException.class )
	public ResponseEntity<?> handleTechnicalException( HttpServletRequest request, TechnicalException e ) { 
    	console.error(e.getMessage(), e);
    	return this.buildResponse(this.buildMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler( RequestWithoutUserException.class )
	public ResponseEntity<?> handleRequestWithoutUserException( HttpServletRequest request, RequestWithoutUserException e ) {
		return this.buildResponse(this.buildMessage(e.getMessage()), RequestWithoutUserException.STATUS);
	}

	@ExceptionHandler( NotFoundException.class )
	public ResponseEntity<?> handleNotFoundException( HttpServletRequest request, NotFoundException e ) {
		return this.buildResponse(this.buildMessage(e.getMessage()), NotFoundException.STATUS);
	}

	@ExceptionHandler( UnauthorizedException.class )
	public ResponseEntity<?> handleUnauthorizedException( HttpServletRequest request, UnauthorizedException e ) {
		return this.buildResponse(this.buildMessage(e.getMessage()), UnauthorizedException.STATUS);
	}


	/**
     * Get the user id from header request
     * @return user id
     * @throws TechnicalException
     * @throws UnauthorizedException
     */
    public Integer getUserId() throws TechnicalException, UnauthorizedException{

        try{

            var userId = this.getHeaders().getUserId();

            if(userId.compareTo(0) <= 0){
                throw new UnauthorizedException("The user id is required.");
            }

            return userId;

        } catch (Exception e){
            throw new TechnicalException(e);
        }

    }

    
}