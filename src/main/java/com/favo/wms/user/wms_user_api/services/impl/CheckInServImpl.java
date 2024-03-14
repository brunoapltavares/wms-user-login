package com.favo.wms.user.wms_user_api.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.payload.HeaderAttributes;
import com.favo.wms.user.wms_user_api.models.CheckIn;
import com.favo.wms.user.wms_user_api.models.User;
import com.favo.wms.user.wms_user_api.repository.CheckInRepo;
import com.favo.wms.user.wms_user_api.services.CheckInServ;

@Service
public class CheckInServImpl implements CheckInServ {
	
	@Autowired
	private CheckInRepo checkInRepo;
	
	public void registerCheckIn( HeaderAttributes headers, User user ) throws TechnicalException {
		
		if ( !headers.hasAppIdentified() ) {
			throw new TechnicalException( "App not identified." );
		}
		
		try {

			var checkIn = new CheckIn( user.getId(), headers.getAppId(), headers.getAppVersion(), new Date() );
			
			checkInRepo.save( checkIn );
			
		}
		catch ( Exception ex ) {
			throw new TechnicalException( ex );
		}
		
	}

}
