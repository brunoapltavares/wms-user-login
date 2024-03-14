package com.favo.wms.user.wms_user_api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.favo.wms.commons.exceptions.TechnicalException;
import com.favo.wms.commons.http.HttpResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class CommonApi {

    private final int DEFAULT_TIMEOUT_IN_SECS = 50;
    
    protected final int DEFAULT_USER_ID = 1;
    
    private final Logger console = LoggerFactory.getLogger(CommonApi.class);
    
	@Autowired
	private Environment env;
	
    /**
     * Makes a get http request with a basic uri 
     * @param uri
     * @return response
     * @throws TechnicalException
     */
    protected HttpResponse get(String uri, Integer userId) throws TechnicalException{

        try {
            
            var client = this.buildHttpClient().build();
            
            var request = this.buildRequest(uri, userId).build();
            
            logRequestStart(request);

            try (var response = client.newCall(request).execute()) {
                logRequestFinish(request);

                return new HttpResponse()
                    .body(this.responseBodyToString(response))
                    .status(response.code());
            } 

        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
    }

    /**
     * Makes a get http request with a basic uri 
     * @param uri
     * @return response
     * @throws TechnicalException
     */
    protected HttpResponse get(String uri) throws TechnicalException{

        try {
        	
            var client = this.buildHttpClient().build();
            
            var request = this.buildRequest(uri).build();
            
            logRequestStart(request);

            try (var response = client.newCall(request).execute()) {
                logRequestFinish(request);
                return new HttpResponse()
                    .body(this.responseBodyToString(response))
                    .status(response.code());
            } 

        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
    }    

    /**
     * Makes a post http request
     * @param payload
     * @param uri
     * @return response
     * @throws TechnicalException
     */
    protected HttpResponse post(String uri, Integer userId, Object payload) throws TechnicalException{

        try {

            var client = this.buildHttpClient().build();

            var request = this.buildRequest(uri, userId)
                .post(this.buildRequestBody(payload))
                .build();

            logRequestStart(request);

            try (var response = client.newCall(request).execute()) {
                logRequestFinish(request);
                return new HttpResponse()
                    .body(this.responseBodyToString(response))
                    .status(response.code());
            }


        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
    }

    /**
     * Makes a put http request
     * @param payload
     * @param uri
     * @return response<String>
     * @throws TechnicalException
     */
    protected HttpResponse put(String uri, Integer userId, Object payload) throws TechnicalException{

        try {

            var client = this.buildHttpClient().build();

            var request = this.buildRequest(uri, userId)
                .put(this.buildRequestBody(payload))
                .build();

            logRequestStart(request, this.objectAsJson(payload));

            try (var response = client.newCall(request).execute()) {
                logRequestFinish(request);
                return new HttpResponse()
                    .body(this.responseBodyToString(response))
                    .status(response.code());
            }

        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
    }
 
    /**
     * Makes a put http request
     * @param uri
     * @return response<String>
     * @throws TechnicalException
     */
    protected HttpResponse put(String uri, Integer userId) throws TechnicalException{

        try {

            var client = this.buildHttpClient().build();

            var request = this.buildRequest(uri, userId)
                .put(this.buildRequestBody(new String("{\"dummy\" : 0}")))
                .build();

            logRequestStart(request);

            try (var response = client.newCall(request).execute()) {
                logRequestFinish(request);
                return new HttpResponse()
                    .body(this.responseBodyToString(response))
                    .status(response.code());
            }

        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
    }

    private Request.Builder buildRequest(String uri, Integer userId) throws URISyntaxException{
        return new Request.Builder()
            .url(uri)
            .addHeader("X-Origin-ID", "BR");
            //.addHeader("X-App-AppId", getAppIdentifier())
            //.addHeader("X-App-UserId", String.valueOf( userId ) );
    }
    
    private Request.Builder buildRequest(String uri) throws URISyntaxException{
        return new Request.Builder()
            .url(uri)
            .addHeader("X-App-AppId", getAppIdentifier());
    }

    private Request.Builder buildRequest(String uri, HashMap<String, String> headers) throws URISyntaxException{
        return new Request.Builder()
            .url(uri)
            .addHeader("X-App-AppId", getAppIdentifier());
    }
    
    private OkHttpClient.Builder buildHttpClient() throws TechnicalException{

        try {

            var client = new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_TIMEOUT_IN_SECS, TimeUnit.SECONDS);

            return client;                
            
        } catch (Exception e) {
            throw new TechnicalException(e);
        }

    }

    private RequestBody buildRequestBody(Object object) throws TechnicalException{

        var body = RequestBody.create(
            this.objectAsJson(object),
            MediaType.parse("application/json; charset=utf-8")
        );            

        return body;
    }

    protected TechnicalException buildRequestError( HttpResponse response ) throws TechnicalException {
    	return new TechnicalException( "HTTP Status " + response.getStatus() + " | " + response.getBody() );
    }
    
    private String objectAsJson(Object payload) throws TechnicalException {
    	try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString( payload );
    	}
    	catch ( JsonProcessingException e ) {
    		throw new TechnicalException( e );
    	}
    	
    }
    
    private void logRequestStart( Request request ) {
    	console.info( ">>   " + request.method() + " " + request.url().uri().toString() );
    }

    private void logRequestStart( Request request, String payload ) {
    	console.info( ">>   " + request.method() + " " + request.url().uri().toString() + " " + payload);
    }
    
    private void logRequestFinish( Request request ) {
    	console.info( "<<<< " + request.method() + " " + request.url().uri().toString() );
    }
    
    private String getAppIdentifier() {
    	return env.getProperty("info.app.groupId") + "." + env.getProperty("info.app.artifactId");
    }

    /**
     * Compose default error message pattern 
     * @param message
     * @return
     */
    public String buildReturnErrorMessage(String message){
        return this.getClass().getSimpleName() + ": " + message; 
    }  
    
    public String responseBodyToString(Response response) throws TechnicalException{

        StringBuilder textBuilder = new StringBuilder();

        try {

            var stream = response.body().byteStream();

            try (Reader reader = new BufferedReader(new InputStreamReader
            (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
              int c = 0;
              while ((c = reader.read()) != -1) {
                  textBuilder.append((char) c);
              }
          }
          
          return textBuilder.toString();
              
        } catch (IOException e) {
            throw new TechnicalException(e);
        }
    }
    

}