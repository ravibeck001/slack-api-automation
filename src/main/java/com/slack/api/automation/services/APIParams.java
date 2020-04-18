package com.slack.api.automation.services;

import com.slack.api.automation.utilities.PropertyFileReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

class APIParams {
    static RequestSpecification setParams() {
        return RestAssured.given()
                .param("Content-type", "application/x-www-form-urlencoded")
                .param("token", PropertyFileReader.getTOKEN());
    }
}
