package com.favo.wms.user.wms_user_api.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class UserFacilityPK implements Serializable{

	private static final long serialVersionUID = 7207689784755110584L;

	@Column(name = "user_id")
    private Integer userId;

    @Column(name = "facility_id")
    private Integer facilityId;
    
    public UserFacilityPK() {
    }

    public UserFacilityPK(Integer userId, Integer facilityId) {
        this.userId = userId;
        this.facilityId = facilityId;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public UserFacilityPK userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getFacilityId() {
        return this.facilityId;
    }
    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }
    public UserFacilityPK facilityId(Integer facilityId) {
        this.facilityId = facilityId;
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserFacilityPK)) {
            return false;
        }
        UserFacilityPK userFacilityPK = (UserFacilityPK) o;
        return Objects.equals(userId, userFacilityPK.userId) 
        		&& Objects.equals(facilityId, userFacilityPK.facilityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, facilityId);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
}