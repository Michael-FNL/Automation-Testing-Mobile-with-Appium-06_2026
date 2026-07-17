package com.mobile.testing.utils.services;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import com.mobile.testing.annotations.Step;

import java.io.File;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static com.mobile.testing.utils.parameters.TestDataParams.testConfig;

public class SauceLabsService {

    private static final String APP_NAME = "org.wikipedia.apk";

    public SauceLabsService() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        RestAssured.baseURI = "https://api.%s.saucelabs.com/v1/storage"
                .formatted(testConfig().getSauceDataCenter());
    }

    @Step("Upload app to Sauce Storage if not already present")
    public String uploadAppIfNotPresentToStorage() {
        List<String> existing = findExistingUpload();

        if (!existing.isEmpty()) {
            return existing.get(0);
        }
        return uploadApp();
    }

    @Step("Check Sauce Storage for an already uploaded app")
    private List<String> findExistingUpload() {
        RestAssured.basePath = "/files";

        return RestAssured
                .given()
                .auth().preemptive().basic(testConfig().getSauceUsername(), testConfig().getSauceAccessKey())
                .queryParam("name", APP_NAME)
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getList("items.id", String.class);
    }

    @Step("Upload app file to Sauce Storage")
    private String uploadApp() {
        RestAssured.basePath = "/upload";

        File app = new File(testConfig().getAndroidAppPath());

        return RestAssured
                .given()
                .auth().preemptive().basic(testConfig().getSauceUsername(), testConfig().getSauceAccessKey())
                .multiPart("payload", app)
                .multiPart("name", APP_NAME)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getString("item.id");
    }
}
