package com.favo.wms.user.wms_user_api.services;

import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.payload.HeaderAttributes;
import com.favo.wms.user.wms_user_api.models.User;

public interface CheckInServ {

	void registerCheckIn( HeaderAttributes headers, User user ) throws BusinessException, TechnicalException;

}
