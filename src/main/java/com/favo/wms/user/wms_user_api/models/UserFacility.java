package com.favo.wms.user.wms_user_api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.favo.wms.commons.builder.CustomJsonToStringStyle;

import io.micrometer.core.lang.Nullable;

@Entity(name = "UserFacility")
@Table(name = "user_facility", schema = "s_user")
public class UserFacility implements Serializable{

	private static final long serialVersionUID = -5928553093420231331L;

	@EmbeddedId
    private UserFacilityPK id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "deleted_at")
    @Nullable
    private Date deletedAt;

    @Column(name = "deleted_by")
    @Nullable
    private Integer deletedBy;

    public UserFacility() {
    }

    public UserFacility(UserFacilityPK id, Date createdAt, Date deletedAt, Integer createdBy, Integer deletedBy) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
    }

    public UserFacilityPK getId() {
        return this.id;
    }
    public void setId(UserFacilityPK id) {
        this.id = id;
    }
    public UserFacility id(UserFacilityPK id) {
        this.id = id;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public UserFacility createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public UserFacility createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getDeletedAt() {
        return this.deletedAt;
    }
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
    public UserFacility deletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Integer getDeletedBy() {
        return this.deletedBy;
    }
    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }
    public UserFacility deletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserFacility)) {
            return false;
        }
        UserFacility user = (UserFacility) o;
        return Objects.equals(id, user.id) 
        		&& Objects.equals(createdAt, user.createdAt) 
        		&& Objects.equals(createdBy, user.createdBy) 
        		&& Objects.equals(deletedAt, user.deletedAt) 
        		&& Objects.equals(deletedBy, user.deletedBy); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, createdBy, deletedAt, deletedBy);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, CustomJsonToStringStyle.CUSTOM_JSON_STYLE);
    }
    
}