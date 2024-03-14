package com.favo.wms.api_integration.service;

import com.favo.wms.api_integration.payload.SignInRequestPayload;
import com.favo.wms.api_integration.payload.SignInResponsePayload;
import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;

public interface ApiIntegrationServ {

	SignInResponsePayload signIn(SignInRequestPayload request) throws BusinessException, TechnicalException;

	}
