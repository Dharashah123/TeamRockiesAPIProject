package com.envision.core;

import com.envision.utils.ApiUtils;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredActions {


    public static Response doGetUserRequest(String baseURI, String endPoint, String methodType){
       Response response = given()
                .log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .when()
                .request(ApiUtils.getMethodType(methodType));

       return response;
    }

    public static Response doPostRequest(String baseURI, String endPoint, String methodType, String payload){
        Response response = given()
                .log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }
    public static Response doPutRequest(String baseURI, String endPoint, String methodType, String payload){
        Response response=given().log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }
    public static Response doPetPostRequest(String contentType,String baseURI, String endPoint, String methodType, String payload){
        Response response = given()
                .log()
                .all()
                .contentType(contentType)
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));
        return response;
    }

    public static Response doDeleteRequest(String contentType,String baseURI, String endPoint, String methodType, String payload){
        Response response = given()
                .log()
                .all()
                .contentType(contentType)
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));
        return response;
    }
}













