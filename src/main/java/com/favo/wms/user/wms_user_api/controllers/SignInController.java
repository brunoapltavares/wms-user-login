package com.favo.wms.user.wms_user_api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.favo.wms.api_integration.payload.SignInRequestPayload;
import com.favo.wms.aws.client.model.Message;
import com.favo.wms.aws.client.service.PublisherService;
import com.favo.wms.commons.controller.CommonController;
import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.exceptions.UnauthorizedException;
import com.favo.wms.user.wms_user_api.enums.UserEventNames;
import com.favo.wms.user.wms_user_api.enums.UserEventType;
import com.favo.wms.user.wms_user_api.payload.UserAuthorizationPayload;
import com.favo.wms.user.wms_user_api.services.SignInServ;

@RestController
@RequestMapping("/v1")
public class SignInController extends CommonController {
 
    public static final Logger console = LoggerFactory.getLogger(SignInController.class);

    @Autowired
    private SignInServ signInServ;

    @Autowired
    private PublisherService publisherService;

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody UserAuthorizationPayload postSignIn(@RequestBody SignInRequestPayload request) throws BusinessException, TechnicalException, UnauthorizedException, CloneNotSupportedException {

    	var headers = getHeaders();
    	
        try {
        	
            var signIn = signInServ.signIn( headers, request );
            
            if ( signIn != null ){
            	var signInCopy = (UserAuthorizationPayload) signIn.clone();
            	signInCopy.setAuthorization(null);
            	publisherService
            		.publish(Message
            				.build(UserEventType.USER.value(),
            						UserEventNames.LOGIN.value(),
            						signInCopy.toString()));
                return signIn;
            } 
            else {
            	throw new UnauthorizedException("access_denied");
            }
            
        } 
        catch (BusinessException be) {
        	request.obfuscatePassword();
        	console.info( String.format( ">> postSignIn {headers: %s, payload: %s}", headers.toString(), request.toString() ) );
            throw be;
            
        } 
        catch (TechnicalException te) {
        	request.obfuscatePassword();
        	console.info( String.format( ">> postSignIn {headers: %s, payload: %s}", headers.toString(), request.toString() ) );
        	throw te;
        }
    }

}