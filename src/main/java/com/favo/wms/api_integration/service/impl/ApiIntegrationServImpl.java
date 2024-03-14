package com.favo.wms.api_integration.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.favo.wms.api_integration.payload.SignInRequestPayload;
import com.favo.wms.api_integration.payload.SignInResponsePayload;
import com.favo.wms.api_integration.service.ApiIntegrationServ;
import com.favo.wms.commons.exceptions.ApiIntegrationException;
import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.user.wms_user_api.service.CommonApi;

@Service
public class ApiIntegrationServImpl extends CommonApi implements ApiIntegrationServ {

	@Value("${app.params.api-integration-uri}")
    private String uri;

    @Override
    public SignInResponsePayload signIn(SignInRequestPayload request) throws BusinessException, TechnicalException {

    	SignInResponsePayload signInResponse = null;
    	
        if ( request == null ){
            throw new BusinessException("The request is required.");
        }
        else if ( StringUtils.isBlank( request.getPhoneNumber() )) {
        	throw new BusinessException("The phone number is required.");
        }
        else if ( StringUtils.isBlank( request.getPassword() )) {
        	throw new BusinessException("The password is required.");
        }

    	try {
    		
        	var response = this.post(uri, DEFAULT_USER_ID, request );

        	if ( response.isSuccessful() ){

                signInResponse = new ObjectMapper().readValue(response.getBody(),
                    new TypeReference<SignInResponsePayload>() {});
                
        	} 
        	else if ( response.getStatus() == HttpStatus.NOT_FOUND.value() ){
        		return null;
        	}
        	else if ( response.getStatus() >= 500 ){
        		throw new TechnicalException( "internal_server_error" );
        	} 
        	else {
        		
				var apiIntegrationException = new ObjectMapper().readValue(response.getBody(),
				    new TypeReference<ApiIntegrationException>() {});
	            
				apiIntegrationException.normalizeErrorMessage();
				
        		throw new BusinessException( apiIntegrationException.getErrorMessage() );
	        
        	}
        	
	    } 
    	catch ( JsonProcessingException e) {
			throw new TechnicalException( e );
		} 
        
        return signInResponse;
        
    }

}
