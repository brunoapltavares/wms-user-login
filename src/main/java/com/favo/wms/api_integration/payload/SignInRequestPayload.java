package com.favo.wms.api_integration.payload;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SignInRequestPayload {

	private String phoneNumber;
	private String password;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SignInRequestPayload)) {
            return false;
        }
        SignInRequestPayload signIn = (SignInRequestPayload) o;
        return Objects.equals(phoneNumber, signIn.phoneNumber) && Objects.equals(password, signIn.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, password);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
    public void obfuscatePassword() {
    	this.password = "********";
    }
    
}
