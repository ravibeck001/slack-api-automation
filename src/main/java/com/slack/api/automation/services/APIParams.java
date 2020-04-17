package com.slack.api.automation.services;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

class APIParams {
    static RequestSpecification setParams() {
        return RestAssured.given()
                .param("Content-type", "application/x-www-form-urlencoded")
                .param("token","xoxp-1063335876244-1063335876292-1056308849475-f3171137b585d12e58e201040821cb4e");
    }
}
