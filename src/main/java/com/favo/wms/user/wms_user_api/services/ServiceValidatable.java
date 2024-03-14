package com.favo.wms.user.wms_user_api.services;

import com.favo.wms.commons.exceptions.BusinessException;

public interface ServiceValidatable <T>{

	/**
	 * To do a basic validation over sended instance
	 * @param instance
	 * @throws BusinessException
	 */
    void validate(T instance) throws BusinessException;
    
}
