package com.favo.wms.commons.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ErrorLog implements Serializable {

	private static final long serialVersionUID = -2470730438169415504L;
	
	private String sessionId;
	private String type;
	private String message;
	private String apiName;
	private String apiVersion;
	
	public ErrorLog() {
		super();
	}
	
	public ErrorLog(String sessionId, String type, String message, String apiName, String apiVersion) {
		super();
		this.sessionId = sessionId;
		this.type = type;
		this.message = message;
		this.apiName = apiName;
		this.apiVersion = apiVersion;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
	
}
