package com.envision.tests;

import com.envision.core.DataProviderArgs;
import com.envision.core.DataProviderUtils;
import com.envision.core.RestAssuredActions;
import com.envision.utils.ApiUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class FirstRestAssuredTest {

    @DataProviderArgs(value = "getListUser=baseUri,endPoint,payload,statusCode,method")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testGetListUsersAPI(String baseUri, String endPoint, String payload, String statusCode, String method) {
        Response response = RestAssuredActions.doGetUserRequest(baseUri, endPoint, method);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("george.bluth@reqres.in"));

    }


    @DataProviderArgs(value = "createUser=baseUri,endPoint,payload,statusCode,method,name,job")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testUserCreationPostAPI(String baseUri, String endPoint, String payload, String statusCode, String method, String name, String job) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%name%", name);
        jsonBody = jsonBody.replaceAll("%job%", job);
        Response response = RestAssuredActions.doPostRequest(baseUri, endPoint, method, jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("createdAt"));

    }

    @DataProviderArgs(value = "updateUser=baseUri,endPoint,payload,statusCode,method,name,job")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testUpdateUserPutAPI(String baseUri, String endPoint, String payload, String statusCode, String method, String name, String job) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%name%", name);
        jsonBody = jsonBody.replaceAll("%job%", job);
        Response response = RestAssuredActions.doPutRequest(baseUri, endPoint, method, jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("updatedAt"));
    }

    @DataProviderArgs(value = "createPetDetailsData=contentType,baseUri,endPoint,payload,statusCode,method,id,category,name,photoUrls,status")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testCreatePetPostAPI(String contentType, String baseUri, String endPoint, String payload, String statusCode, String method, String id, String category, String name, String photoUrls, String status) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%id%", id);
        jsonBody = jsonBody.replaceAll("%category%", category);
        jsonBody = jsonBody.replaceAll("%name%", name);
        jsonBody = jsonBody.replaceAll("%photoUrls%", photoUrls);
        jsonBody = jsonBody.replaceAll("%status%", status);
        Response response = RestAssuredActions.doPetPostRequest(contentType, baseUri, endPoint, method, jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("status"));
    }

    @DataProviderArgs(value = "deletePetById=contentType,baseUri,endPoint,payload,statusCode,method,id")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testDeletePetDetailsByIdAPI(String contentType,String baseUri, String endPoint, String payload, String statusCode, String method, String id) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%id%", id);
        Response response = RestAssuredActions.doDeleteRequest(contentType,baseUri, endPoint, method,jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode));
    }


}



