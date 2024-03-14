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

import io.micrometer.core.lang.Nullable;

@Entity(name = "User")
@Table(name = "user", schema = "s_user")
public class User implements Serializable{

    private static final long serialVersionUID = -5616572670526846277L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "facility_id")
    private Integer facilityId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @Nullable
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Nullable
    private Date deletedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    @Nullable
    private Integer updatedBy;

    @Column(name = "deleted_by")
    @Nullable
    private Integer deletedBy;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "country")
    private String country;

    @Column(name = "level")
    private Integer level;

    @Column(name = "pass")
    private String pass;

    public User() {
    }

    public User(Integer id, String externalId, String name, String email, Integer facilityId,
    		Date createdAt, Date updatedAt, Date deletedAt, Integer createdBy, Integer updatedBy, Integer deletedBy,
    		String fullName, String country, Integer level, String pass) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.facilityId = facilityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.fullName = fullName;
        this.country = country;
        this.level = level;
        this.pass = pass;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public User id(Integer id) {
        this.id = id;
        return this;
    }

    public String getExternalId() {
        return this.externalId;
    }
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
    public User externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User name(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public User email(String email) {
        this.email = email;
        return this;
    }
    
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public User country(String country) {
        this.country = country;
        return this;
    }
    
    public Integer getFacilityId() {
        return this.facilityId;
    }
    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }
    public User facilityId(Integer facilityId) {
        this.facilityId = facilityId;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public User createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public User updatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getDeletedAt() {
        return this.deletedAt;
    }
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
    public User deletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public User createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Integer getUpdatedBy() {
        return this.updatedBy;
    }
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    public User updatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Integer getDeletedBy() {
        return this.deletedBy;
    }
    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }
    public User deletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public User fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getLevel() {
        return this.level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public User level(Integer level) {
        this.level = level;
        return this;
    }

    public String getPass() {
        return this.pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public User pass(String pass) {
        this.pass = pass;
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) 
        		&& Objects.equals(externalId, user.externalId) 
        		&& Objects.equals(name, user.name) 
        		&& Objects.equals(email, user.email)
        		&& Objects.equals(country, user.country)
        		&& Objects.equals(facilityId, user.facilityId) 
        		&& Objects.equals(createdAt, user.createdAt) 
        		&& Objects.equals(updatedAt, user.updatedAt) 
        		&& Objects.equals(deletedAt, user.deletedAt) 
        		&& Objects.equals(createdBy, user.createdBy) 
        		&& Objects.equals(updatedBy, user.updatedBy) 
        		&& Objects.equals(deletedBy, user.deletedBy) 
        		&& Objects.equals(fullName, user.fullName)
                && Objects.equals(level, user.level)
                && Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, name, email, facilityId, country, createdAt, updatedAt, deletedAt, createdBy, updatedBy, deletedBy, fullName, level, pass);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, CustomJsonToStringStyle.CUSTOM_JSON_STYLE);
    }

    
}