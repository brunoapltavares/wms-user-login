package com.favo.wms.user.wms_user_api.enums;

public enum UserEventType {

	USER("USER");

	private String userEventType;

    private UserEventType(String userEventType){
        this.userEventType = userEventType;
    }
    
    public String value(){
        return this.userEventType;
    }
}
