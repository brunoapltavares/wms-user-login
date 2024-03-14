package com.favo.wms.user.wms_user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favo.wms.user.wms_user_api.models.CheckIn;

public interface CheckInRepo extends JpaRepository<CheckIn, Integer>{

}