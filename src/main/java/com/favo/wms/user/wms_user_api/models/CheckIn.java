package com.favo.wms.user.wms_user_api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.favo.wms.commons.builder.CustomJsonToStringStyle;

@Entity(name = "CheckIn")
@Table(name = "check_in", schema = "s_user")
public class CheckIn implements Serializable{

	private static final long serialVersionUID = -4171499143658876421L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;
	
    @Column(name = "app_id")
    private String appId;

    @Column(name = "app_version")
    private String appVersion;
    
    @Column(name = "logon_at")
    private Date logonAt;

    public CheckIn() {
    }

    public CheckIn(Integer userId, String appId, String appVersion, Date logonAt) {
    	this(null, userId, appId, appVersion, logonAt);
    }

    public CheckIn(Long id, Integer userId, String appId, String appVersion, Date logonAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.appId = appId;
		this.appVersion = appVersion;
		this.logonAt = logonAt;
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Date getLogonAt() {
		return logonAt;
	}

	public void setLogonAt(Date logonAt) {
		this.logonAt = logonAt;
	}

	public CheckIn id(Long id) {
        this.id = id;
        return this;
    }

    public CheckIn userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public CheckIn appId(String appId) {
        this.appId = appId;
        return this;
    }

    public CheckIn appVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public CheckIn logonAt(Date logonAt) {
        this.logonAt = logonAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CheckIn)) {
            return false;
        }
        CheckIn user = (CheckIn) o;
        return Objects.equals(id, user.id) 
        		&& Objects.equals(userId, user.userId) 
        		&& Objects.equals(appId, user.appId) 
        		&& Objects.equals(appVersion, user.appVersion) 
        		&& Objects.equals(logonAt, user.logonAt) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, appId, appVersion, logonAt);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, CustomJsonToStringStyle.CUSTOM_JSON_STYLE);
    }
    
}