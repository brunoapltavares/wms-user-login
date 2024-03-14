package com.favo.wms.user.wms_user_api.services.impl;

import java.nio.charset.StandardCharsets;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favo.wms.api_integration.payload.SignInRequestPayload;
import com.favo.wms.api_integration.payload.SignInResponsePayload;
import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.payload.HeaderAttributes;
import com.favo.wms.user.wms_user_api.models.User;
import com.favo.wms.user.wms_user_api.payload.UserAuthorizationPayload;
import com.favo.wms.user.wms_user_api.services.CheckInServ;
import com.favo.wms.user.wms_user_api.services.SignInServ;
import com.favo.wms.user.wms_user_api.services.UserServ;
import com.google.common.hash.Hashing;

@Service
public class SignInServImpl implements SignInServ {

	public static final Logger console = LoggerFactory.getLogger(SignInServImpl.class);
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private CheckInServ checkInServ;
	
	@Override
	public UserAuthorizationPayload signIn( HeaderAttributes headers, SignInRequestPayload signInRequest ) throws BusinessException, TechnicalException {

		UserAuthorizationPayload result = null;
		
		//var signin = apiIntegration.signIn( signInRequest );
		
		var user = userServ.findByExternalId( signInRequest.getPhoneNumber() );
		
		String sha256hex = Hashing.sha256()
				  .hashString(signInRequest.getPassword(), StandardCharsets.UTF_8)
				  .toString();
		
		if ( user == null || user.getDeletedAt() != null || !user.getPass().equals(sha256hex)) {
			console.warn( "user " + signInRequest.getPhoneNumber() + " not registered in WMS." );
			throw new BusinessException( "user_not_registered_in_wms" );
		}
			
		result = castUserAuthorization( user );
		SignInResponsePayload signInResponse = new SignInResponsePayload();
		signInResponse.setAccessToken("");
		signInResponse.setExpiresIn((long) 36000);
		signInResponse.setRefreshToken("");
		signInResponse.setToken("");
		
		result.setAuthorization( signInResponse );

		checkInServ.registerCheckIn( headers, user );
		
		result.setAllowedFacilities( userServ.getAllowedFacilities( user.getId() ) );
		
		signInRequest.obfuscatePassword();
		console.info( String.format( "@@ User authenticated: {headers:%s,request:%s,user:%s}" , headers, signInRequest, user ) );
		
		return result;
	
	}

	private UserAuthorizationPayload castUserAuthorization(User from) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(from, UserAuthorizationPayload.class);
	}
	
}
