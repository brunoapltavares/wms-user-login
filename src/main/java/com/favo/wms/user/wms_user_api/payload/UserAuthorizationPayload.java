package com.favo.wms.user.wms_user_api.payload;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.favo.wms.api_integration.payload.SignInResponsePayload;
import com.favo.wms.commons.builder.CustomJsonToStringStyle;

public class UserAuthorizationPayload implements Serializable, Cloneable {

	private static final long serialVersionUID = 8646905780909829760L;
	
	private Integer id;
    private String externalId;
    private String name;
    private String email;
    private Integer level;
    private Integer facilityId;
    private String country;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private Integer deletedBy;
    private SignInResponsePayload authorization;
    private List< Integer > allowedFacilities;

    public UserAuthorizationPayload() {
    }

    public UserAuthorizationPayload(Integer id, String externalId, String name, String email, Integer level, 
    		Integer facilityId, String country, Date createdAt, Date updatedAt, Date deletedAt, 
    		Integer createdBy, Integer updatedBy, Integer deletedBy, SignInResponsePayload authorization, 
    		List< Integer > allowedFacilities) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.level = level;
        this.facilityId = facilityId;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.authorization = authorization;
        this.allowedFacilities = allowedFacilities;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public UserAuthorizationPayload id(Integer id) {
        this.id = id;
        return this;
    }

    public String getExternalId() {
        return this.externalId;
    }
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
    public UserAuthorizationPayload externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UserAuthorizationPayload name(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserAuthorizationPayload email(String email) {
        this.email = email;
        return this;
    }

    public Integer getLevel() {
        return this.level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public UserAuthorizationPayload level(Integer level) {
        this.level = level;
        return this;
    }
    
    public Integer getFacilityId() {
        return this.facilityId;
    }
    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }
    public UserAuthorizationPayload facilityId(Integer facilityId) {
        this.facilityId = facilityId;
        return this;
    }

    public String getCountry() {
    	return this.country;
    }
    public void setCountry( String country ) {
    	this.country = country;
    }
    public UserAuthorizationPayload country( String country ) {
    	this.country = country;
    	return this;
    }
    
    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public UserAuthorizationPayload createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UserAuthorizationPayload updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getDeletedAt() {
        return this.deletedAt;
    }
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
    public UserAuthorizationPayload deletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public UserAuthorizationPayload createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Integer getUpdatedBy() {
        return this.updatedBy;
    }
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    public UserAuthorizationPayload updatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Integer getDeletedBy() {
        return this.deletedBy;
    }
    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }
    public UserAuthorizationPayload deletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }
    
    public SignInResponsePayload getAuthorization() {
        return this.authorization;
    }
    public void setAuthorization(SignInResponsePayload authorization) {
        this.authorization = authorization;
    }
    public UserAuthorizationPayload authorization(SignInResponsePayload authorization) {
    	this.authorization = authorization;
        return this;
    }
    
    public List< Integer > getAllowedFacilities() {
    	return this.allowedFacilities;
    }
    public void setAllowedFacilities( List< Integer > allowedFacilities ) {
    	this.allowedFacilities = allowedFacilities;
    }
    public UserAuthorizationPayload allowedFacilities( List< Integer > allowedFacilities ) {
    	this.allowedFacilities = allowedFacilities;
    	return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserAuthorizationPayload)) {
            return false;
        }
        UserAuthorizationPayload user = (UserAuthorizationPayload) o;
        return Objects.equals(id, user.id) 
        		&& Objects.equals(externalId, user.externalId) 
        		&& Objects.equals(name, user.name) 
        		&& Objects.equals(email, user.email)
        		&& Objects.equals(level, user.level) 
        		&& Objects.equals(facilityId, user.facilityId) 
        		&& Objects.equals(createdAt, user.createdAt) 
        		&& Objects.equals(updatedAt, user.updatedAt) 
        		&& Objects.equals(deletedAt, user.deletedAt) 
        		&& Objects.equals(createdBy, user.createdBy) 
        		&& Objects.equals(updatedBy, user.updatedBy) 
        		&& Objects.equals(deletedBy, user.deletedBy) 
        		&& Objects.equals(authorization, user.authorization)
        		&& Objects.equals(allowedFacilities, user.allowedFacilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, name, email, level, facilityId, createdAt, updatedAt, deletedAt, createdBy, updatedBy, deletedBy, authorization, allowedFacilities);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, CustomJsonToStringStyle.CUSTOM_JSON_STYLE);
    }
    
}