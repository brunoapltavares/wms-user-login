package com.favo.wms.user.wms_user_api.enums;

public enum UserEventNames {

	LOGIN("LOGIN");

	private String userEventNames;

    private UserEventNames(String userEventNames){
        this.userEventNames = userEventNames;
    }
    
    public String value(){
        return this.userEventNames;
    }
}
