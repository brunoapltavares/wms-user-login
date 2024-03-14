package com.favo.wms.user.wms_user_api.services;

import com.favo.wms.api_integration.payload.SignInRequestPayload;
import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.payload.HeaderAttributes;
import com.favo.wms.user.wms_user_api.payload.UserAuthorizationPayload;

public interface SignInServ {

	UserAuthorizationPayload signIn( HeaderAttributes headers, SignInRequestPayload signInRequest ) throws BusinessException, TechnicalException;
	
}
