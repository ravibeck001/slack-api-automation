package com.slack.api.automation.services;

import com.slack.api.automation.utilities.PropertyFileReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIServices {
    private static Logger logger = Logger.getLogger(APIServices.class.getName());

    public static void setRestAssuredRequestResource() {
        RestAssured.baseURI = PropertyFileReader.getBASE_URI();
        RestAssured.useRelaxedHTTPSValidation();
    }

    public static Response createGetResponse(String pathURI) {
        Response response = null;
        try {
            response = APIParams.setParams()
                    .when().log().all().get(pathURI)
                    .then().log().all()
                    .extract().response();
        } catch (Exception e) {
            logger.log(Level.INFO, "RestAssured response error found with '" + pathURI + "' API", e);
        }
        return response;
    }

    public static Response createGetResponseWithQueryParameter(String pathURI, Map<String, String> queryParameter) {
        Response response = null;
        try {
            response = APIParams.setParams()
                    .queryParams(queryParameter)
                    .when().log().all().get(pathURI)
                    .then().log().all()
                    .extract().response();
        } catch (Exception e) {
            logger.log(Level.INFO, "RestAssured response error found with '" + pathURI + "' API", e);
        }
        return response;
    }

    public static Response createPostResponseWithFormParameter(String pathURI, Map<String, String> formParameter) {
        Response response = null;
        try {
            response = APIParams.setParams()
                    .params(formParameter)
                    .when().log().all().post(pathURI)
                    .then().log().all()
                    .extract().response();
        } catch (Exception e) {
            logger.log(Level.INFO, "RestAssured response error found with '" + pathURI + "' API", e);
        }
        return response;
    }
}