package com.favo.wms.api_integration.payload;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignInResponsePayload {

	private String accessToken;
	private String refreshToken;
	private String token;
	
	@JsonProperty("ExpiresIn")
	private Long expiresIn;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SignInResponsePayload)) {
            return false;
        }
        SignInResponsePayload signIn = (SignInResponsePayload) o;
        return Objects.equals(accessToken, signIn.accessToken) && Objects.equals(refreshToken, signIn.refreshToken) && Objects.equals(token, signIn.token) && Objects.equals(expiresIn, signIn.expiresIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken, token, expiresIn);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
