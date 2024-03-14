package com.favo.wms.user.wms_user_api.security;

import com.favo.wms.commons.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class Security {

    //TODO - this is not final implementation

    private static final String TOKEN = "d21zLXVzZXItYXBpIGRldmVsb3BlZCBieSBSb2RvbHBobywgU3RyYXVzcyBhbmQgVmljY2FyaS4gRmF2byAyMDIxCg==";

    public void authenticate(HttpServletRequest request) throws UnauthorizedException {

        var token = request.getHeader("Authorization");
        token = token.substring(7, token.length()); // discard Baerer tag

        if(!token.equals(TOKEN)){
            throw new UnauthorizedException("unauthorized");
        }

    }
}

