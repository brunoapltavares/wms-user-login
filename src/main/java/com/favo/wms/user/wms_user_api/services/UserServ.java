package com.favo.wms.user.wms_user_api.services;

import java.util.List;

import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.NotFoundException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.user.wms_user_api.models.User;
import org.springframework.data.repository.query.Param;

public interface UserServ {

    /**
     * Gets user data searching by id
     * @param userId
     * @return user
     * @throws BusinessException
     * @throws TechnicalException
     */
    User findById(Integer userId) throws BusinessException, TechnicalException;

    /**
     * Gets user data searching by external id
     * @param externalId
     * @return user
     * @throws BusinessException
     * @throws TechnicalException
     */
    User findByExternalId(String externalId) throws BusinessException, TechnicalException;

    /**
     * Gets allowed facilities by user id
     * @param userId
     * @return user
     * @throws BusinessException
     * @throws TechnicalException
     */
    List< Integer > getAllowedFacilities( Integer userId ) throws BusinessException, TechnicalException;

    /**
     * Get user list data searching by facility id
     * @param facilityId
     * @return users
     */
    List<User> findByFacilityId(@Param("facilityId") Integer facilityId) throws BusinessException, TechnicalException;

    /**
     * Get all users from database
     * @return users
     */
    List<User> findAll() throws TechnicalException;

    /**
     * To save user info on database
     * @param user
     * @param createdBy
     * @param facilityId
     * @return saved user
     * @throws BusinessException
     * @throws TechnicalException
     */
    User save(User user, Integer createdBy, Integer facilityId) throws BusinessException, TechnicalException;

    /**
     * To set user to logical delete
     * @param id
     * @param deletedBy
     * @return saved user
     * @throws BusinessException
     * @throws TechnicalException
     */
    User logicalDelete(Integer id, Integer deletedBy) throws BusinessException, TechnicalException, NotFoundException;
    
    List<User> findByIdList(List<Long> ids) throws BusinessException, TechnicalException, NotFoundException;

}