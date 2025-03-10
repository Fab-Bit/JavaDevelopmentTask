package org.fabbit;


import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.fabbit.ConfigUtil;


public class LoginPage {

    private String loginUrl;
    private Platform platform;

    public LoginPage(Platform platform) {
        this.platform = platform;
        this.loginUrl = ConfigUtil.getApiUrl(platform);
    }

    public void loginWithToken(String token) {

        RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get(loginUrl + "/repos/Fab-Bit/JavaDevelopmentTask")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("JavaDevelopmentTask"))
                .body("owner.login", equalTo(ConfigUtil.getUsername(platform)))
                .extract().response();
    }
}