package com.favo.wms.commons.payload;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.favo.wms.commons.builder.CustomJsonToStringStyle;

public class HeaderAttributes implements Serializable {

	private static final long serialVersionUID = -5940043438721950741L;

	public static final String HEADER_APP_ID 		= "X-App-Id";
	public static final String HEADER_APP_VERSION 	= "X-App-Version";
	public static final String HEADER_APP_DEVICE_OS = "X-App-DeviceOS";
	public static final String HEADER_APP_DEVICE_ID = "X-App-DeviceId";
	public static final String HEADER_APP_USER_ID 	= "X-App-UserId";
	public static final String HEADER_APP_FACILITY 	= "X-App-Facility";
	public static final String HEADER_APP_COUNTRY 	= "X-App-Country";
	public static final String HEADER_APP_GMT 		= "X-App-GMT";
	
	private String token;
	private String deviceOS;	//X-App-DeviceOS
	private String deviceId;	//X-App-DeviceId
	private String appVersion;	//X-App-Version
	private String appId;		//X-App-Id
	private Integer userId;		//X-App-UserId
	private Integer facility;	//X-App-Facility
	private String country;		//X-App-Country
	private String gmt;			//X-App-GMT
	
	private Date timestamp;
	private String sessionId;
	
	public HeaderAttributes() { }
	
	public HeaderAttributes( HttpServletRequest request ) {
		this();
		setAppId( 		request.getHeader(HEADER_APP_ID 		)); 
		setAppVersion( 	request.getHeader(HEADER_APP_VERSION 	));
		setDeviceOS( 	request.getHeader(HEADER_APP_DEVICE_OS 	));
		setDeviceId( 	request.getHeader(HEADER_APP_DEVICE_ID 	));
		setUserId( 		request.getHeader(HEADER_APP_USER_ID 	));
		setFacility( 	request.getHeader(HEADER_APP_FACILITY 	));
		setCountry( 	request.getHeader(HEADER_APP_COUNTRY 	));
		setGMT( 		request.getHeader(HEADER_APP_GMT 		));
		this.timestamp = new Date();
		this.sessionId = request.getSession().getId();
	}
	
	public HeaderAttributes( String appId, Integer userId ) { 
		this.appId = appId;
		this.userId = userId;
	}
	
	public HeaderAttributes( Integer userId ) { 
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeviceOS() {
		return deviceOS;
	}
	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = StringUtils.trimToNull( deviceId );
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		if ( StringUtils.isNumeric( userId ) ) {
			setUserId( Integer.valueOf( userId ));
		}
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		if ( StringUtils.isNumeric( facility ) ) {
			setFacility( Integer.valueOf( facility ));
		}
	}
	public void setFacility(Integer facility) {
		this.facility = facility;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGMT() {
		return gmt;
	}
	private void setGMT(String gmt) {
		this.gmt = gmt;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, CustomJsonToStringStyle.CUSTOM_JSON_STYLE);
	}
	
	public boolean isUserIdentified() {
		return !( this.userId == null || this.userId == 0 );
	}
	
	public boolean hasAppIdentified() {
		return !( StringUtils.isAllBlank( this.appId ) || StringUtils.isAllBlank( this.appVersion ));
	}

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }

}