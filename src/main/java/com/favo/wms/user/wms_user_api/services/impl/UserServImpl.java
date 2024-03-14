package com.favo.wms.user.wms_user_api.services.impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.NotFoundException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.user.wms_user_api.models.User;
import com.favo.wms.user.wms_user_api.models.UserFacility;
import com.favo.wms.user.wms_user_api.models.UserFacilityPK;
import com.favo.wms.user.wms_user_api.repository.UserFacilityRepo;
import com.favo.wms.user.wms_user_api.repository.UserRepo;
import com.favo.wms.user.wms_user_api.services.ServiceValidatable;
import com.favo.wms.user.wms_user_api.services.UserServ;

@Service
public class UserServImpl implements UserServ, ServiceValidatable<User> {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private UserFacilityRepo userFacilityRepo; 

    @Override
    public User findById(Integer userId) throws BusinessException, TechnicalException {
        
        if(userId == null){
            throw new BusinessException("The user id is required.");
        }
    
        try {

            var optional = this.userRepo.findById(userId);

            return optional.isPresent() ? optional.get() : null;
            
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }

    @Override
    public User findByExternalId(String externalId) throws BusinessException, TechnicalException {

        if(externalId == null){
            throw new BusinessException("The external id is required.");
        }
    
        try {

            var optional = this.userRepo.findByExternalId(externalId);

            return optional.isPresent() ? optional.get() : null;
            
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
    
    @Override
    public List< Integer > getAllowedFacilities( Integer userId ) throws BusinessException, TechnicalException {
    	
        if(userId == null){
            throw new BusinessException("The user id is required.");
        }
        
        try {

            var optional = this.userFacilityRepo.findByIdUserId(userId);

            var userFacilityList = optional.isPresent() ? optional.get() : null;
            
            var result = userFacilityList.stream().map((e) -> e.getId().getFacilityId()).collect(Collectors.toList());
            
            return result;
            
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
    }

    @Override
    public List<User> findByFacilityId(Integer facilityId)  throws BusinessException, TechnicalException{

        if(facilityId == null) throw new BusinessException("facility_id_is_required");

        try {

            return this.userRepo.findByFacilityId(facilityId);

        }catch (DataAccessException dae){
            throw new TechnicalException(dae.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public List<User> findAll() throws TechnicalException {
        try {

            return this.userRepo.findAll();

        }catch (DataAccessException dae){
            throw new TechnicalException(dae.getMostSpecificCause().getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {BusinessException.class, TechnicalException.class})
    public User save(User user, Integer createdBy, Integer facilityId) throws BusinessException, TechnicalException {

        user.setFacilityId(facilityId);
        user.setCreatedBy(createdBy);

        this.validate(user);

        var transactionAt = Calendar.getInstance().getTime();
        var withFacility = false;

        try {

            if(user.getId() == null){
                user.setCreatedAt(transactionAt);
                user.setCreatedBy(createdBy);
                withFacility = true;
            } else {
                user.setUpdatedAt(transactionAt);
                user.setUpdatedBy(createdBy);
            }
            if(user.getCountry().compareToIgnoreCase("BR") == 0){
                user.setExternalId( "+55" + user.getExternalId().substring(3) );

            } else if(user.getCountry().compareToIgnoreCase("PE") == 0){
                user.setExternalId( "+51" + user.getExternalId().substring(3));
            }
            user = this.userRepo.save(user);

            if(withFacility){
                var userFacility = new UserFacility();
                userFacility.setCreatedBy(createdBy);
                userFacility.setCreatedAt(transactionAt);

                var userFacilityId = new UserFacilityPK();
                userFacilityId.setFacilityId(facilityId);
                userFacilityId.setUserId(user.getId());

                userFacility.setId(userFacilityId);
                this.userFacilityRepo.save(userFacility);
            }

            return user;

        }catch (DataAccessException dae){
            throw new TechnicalException(dae.getMostSpecificCause().getMessage());
        }

    }

    @Override
    @Transactional(rollbackFor = {BusinessException.class, TechnicalException.class, NotFoundException.class})
    public User logicalDelete(Integer id, Integer deletedBy) throws BusinessException, TechnicalException, NotFoundException {

        if(id == null) throw new BusinessException("user_id_is_required");
        if(deletedBy == null) throw new BusinessException("deleted_by_is_required");

        try {

            var user = this.findById(id);

            if(user == null) throw new NotFoundException("not_found_for_id_" + id);

            var transactionAt = Calendar.getInstance().getTime();
            user.setDeletedAt(transactionAt);
            user.setDeletedBy(deletedBy);

            this.userRepo.save(user);

            return user;

        }catch (DataAccessException dae){
            throw new TechnicalException(dae.getMostSpecificCause().getMessage());
        }

    }

    @Override
    public void validate(User instance) throws BusinessException {
        if(instance.getName() == null || instance.getName().isBlank() || instance.getName().isEmpty()) throw new BusinessException("user_name_is_required");

        if(instance.getFullName() == null || instance.getFullName().isBlank() || instance.getFullName().isEmpty()) throw new BusinessException("user_name_is_required");

        if(instance.getExternalId() == null || instance.getExternalId().isBlank() || instance.getExternalId().isEmpty()) throw new BusinessException("external_id_is_required");

        if(instance.getCountry() == null || instance.getCountry().isBlank() || instance.getCountry().isEmpty()) throw new BusinessException("country_is_required");

        if(instance.getFacilityId() == null) throw new BusinessException("facility_id_is_required");

        var email = instance.getEmail();

        if(email == null){

            if(instance.getName().contains(" ")){
                var firstName = instance.getName().substring(0, instance.getName().indexOf(" ")).toLowerCase();
                var lastName = instance.getName().substring(instance.getName().lastIndexOf(" ") +1, instance.getName().length()).toLowerCase();
                instance.setEmail(String.format("%s.%s@mercadofavo.com", firstName, lastName));
            } else {
                instance.setEmail(String.format("%s@mercadofavo.com", instance.getName().toLowerCase()));
            }

        }

    }
    
    @Override
    public List<User> findByIdList(List<Long> ids) throws BusinessException, TechnicalException, NotFoundException {

        if(ids == null || ids.isEmpty()) throw new BusinessException("id_list_is_required");

        try {

            var result = this.userRepo.findByIdList(ids);

            if(result != null && !result.isEmpty()) {
                return result;
            } else {
                throw new NotFoundException("not_found");
            }
            
        } catch (DataAccessException dae) {
            throw new TechnicalException(dae.getMostSpecificCause().getMessage());
        }
    }
    
}