package com.favo.wms.user.wms_user_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.user.wms_user_api.models.UserFacility;

public interface UserFacilityRepo extends JpaRepository<UserFacility, Integer>{

    /**
     * Gets user data searching by external id
     * @param externalId
     * @return user
     * @throws BusinessException
     * @throws TechnicalException
     */
    Optional<List<UserFacility>> findByIdUserId(Integer userId);
}