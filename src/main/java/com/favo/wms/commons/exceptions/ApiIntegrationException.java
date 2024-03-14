package com.favo.wms.commons.exceptions;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ApiIntegrationException extends Exception implements Serializable {
	
	private static final long serialVersionUID = -8624996791321013980L;

	private String errorMessage;
	private String errorType;
	private String requestId;
	
    public ApiIntegrationException() {
        super();
    }
    public ApiIntegrationException(String message) {
        super(message);
    }
    public ApiIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApiIntegrationException(Throwable cause) {
        super(cause);
    }    
    
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ApiIntegrationException)) {
            return false;
        }
        ApiIntegrationException signIn = (ApiIntegrationException) o;
        return Objects.equals(errorMessage, signIn.errorMessage) && Objects.equals(errorType, signIn.errorType) && Objects.equals(requestId, signIn.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage, errorType, requestId);
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
	
    public void normalizeErrorMessage() {
    	this.errorMessage = this.errorMessage.toLowerCase().replace(" ", "_").replace(".", "") ;
    }
    
}
