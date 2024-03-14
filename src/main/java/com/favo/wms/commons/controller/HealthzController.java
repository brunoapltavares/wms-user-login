package com.favo.wms.commons.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthzController extends CommonController {

    @GetMapping("/healthz")
    public ResponseEntity<?> healthz(){
        return buildResponse(
            buildMessage("Listening!"), 
            HttpStatus.OK
        );
    }
    
}