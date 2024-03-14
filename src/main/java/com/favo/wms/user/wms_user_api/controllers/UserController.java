package com.favo.wms.user.wms_user_api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.favo.wms.commons.controller.CommonController;
import com.favo.wms.commons.exceptions.BusinessException;
import com.favo.wms.commons.exceptions.NotFoundException;
import com.favo.wms.commons.exceptions.RequestWithoutUserException;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.exceptions.UnauthorizedException;
import com.favo.wms.user.wms_user_api.models.User;
import com.favo.wms.user.wms_user_api.security.Security;
import com.favo.wms.user.wms_user_api.services.UserServ;

@RestController
@RequestMapping("/v1")
public class UserController extends CommonController {
 
    public static final Logger console = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServ service;

    @Autowired
    private Security security;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Integer id) throws BusinessException, TechnicalException {

        var user = this.service.findById(id);

        if (user != null) {
            return buildResponse(user, HttpStatus.OK);
        } else {
            return buildResponse("User Id " + id + " not found.", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/externalId/{externalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable String externalId) throws BusinessException, TechnicalException {

        var user = this.service.findByExternalId(externalId);

        if (user != null) {
            return buildResponse(user, HttpStatus.OK);
        } else {
            return buildResponse("External Id " + externalId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() throws BusinessException, TechnicalException, UnauthorizedException {

        security.authenticate(request);

        var users = this.service.findAll();

        if (users != null && !users.isEmpty()) {
            return buildResponse(users, HttpStatus.OK);
        } else {
            return buildResponse(this.buildMessage("not_found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/facility/{facilityId}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByFacilityId(@PathVariable Integer facilityId) throws BusinessException, TechnicalException, UnauthorizedException {

        security.authenticate(request);

        var users = this.service.findByFacilityId(facilityId);

        if (users != null && !users.isEmpty()) {
            return buildResponse(users, HttpStatus.OK);
        } else {
            return buildResponse(this.buildMessage("not_found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody User user) throws BusinessException, TechnicalException, RequestWithoutUserException, UnauthorizedException {

        security.authenticate(request);

        var createdBy = this.getSponsorFrom(request);

        var already = this.service.findByExternalId(user.getExternalId());

        if(already != null) return this.buildResponse(already, HttpStatus.CREATED);

        user.setId(null);
        var savedUser = this.service.save(user, createdBy, user.getFacilityId()); // TODO - change method to receive facility id from another structure

        return this.buildResponse(savedUser, HttpStatus.CREATED);
    }


    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody User user) throws BusinessException, TechnicalException, RequestWithoutUserException, NotFoundException, UnauthorizedException {

        security.authenticate(request);

        var updatedBy = this.getSponsorFrom(request);

        var already = this.service.findByExternalId(user.getExternalId());

        if(already == null) throw new NotFoundException("not_found");

        already.setEmail(user.getEmail());
        already.setName(user.getName());
        already.setFullName(user.getFullName());
        already.setLevel(user.getLevel());
        already.setCountry(user.getCountry());

        var savedUser = this.service.save(already, updatedBy, user.getFacilityId()); // TODO - change method to receive facility id from anther structure

        return this.buildResponse(savedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException, TechnicalException, RequestWithoutUserException, NotFoundException, UnauthorizedException {
        security.authenticate(request);

        var deletedBy = this.getSponsorFrom(request);
        var result = this.service.logicalDelete(id, deletedBy);

        return this.buildResponse(result, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/enable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> enable(@PathVariable Integer id) throws BusinessException, TechnicalException, RequestWithoutUserException, NotFoundException, UnauthorizedException {
        security.authenticate(request);

        var enabledBy = this.getSponsorFrom(request);
        var already = this.service.findById(id);

        if(already == null) throw new NotFoundException("not_found");

        already.setDeletedBy(null);
        already.setDeletedAt(null);

        already = this.service.save(already, enabledBy, already.getFacilityId() );

        return this.buildResponse(already, HttpStatus.OK);
    }
    
    @GetMapping(value = "/searchBy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByIdList(@RequestParam List<Long> ids) throws BusinessException, TechnicalException, NotFoundException{
        var result = service.findByIdList(ids);
        return this.buildResponse(result, HttpStatus.OK);
    }
    
}