package com.favo.wms.user.wms_user_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favo.wms.user.wms_user_api.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{

    /**
     * Gets user data searching by external id
     * @param externalId
     * @return user
     */
    Optional<User> findByExternalId(String externalId);

    /**
     * Get user list data searching by facility id
     * @param facilityId
     * @return
     */
    List<User> findByFacilityId(@Param("facilityId") Integer facilityId);
    
    /**
     * Get user data searching by array of ids
     * @param ids
     * @return user list
     */
    @Query(value = 
    " select *\n" + 
    "   from s_user.user u \n" + 
    "  where u.id in (:ids) \n", nativeQuery = true)
    List< User > findByIdList(@Param("ids") List<Long> ids);
    
}