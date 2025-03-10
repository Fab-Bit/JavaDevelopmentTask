package org.fabbit;


import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
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
                .when()
                .get(loginUrl)
                .then()
                .statusCode(200)
                .body("login", equalTo(ConfigUtil.getUsername(platform)));
    }
}