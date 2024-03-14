package com.favo.wms.commons.http;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class HttpResponse {

    private String body;
    private Integer status;

    public HttpResponse() {
    }

    public HttpResponse(String body, Integer status) {
        this.body = body;
        this.status = status;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public HttpResponse body(String body) {
        this.body = body;
        return this;
    }

    public HttpResponse status(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HttpResponse)) {
            return false;
        }
        HttpResponse httpResponse = (HttpResponse) o;
        return Objects.equals(body, httpResponse.body) && Objects.equals(status, httpResponse.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, status);
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public boolean isSuccessful(){
        if(this.status == 0){
            return false;
        }
        return this.status >= 200 && this.status <= 299 ? true : false;
    }

}
