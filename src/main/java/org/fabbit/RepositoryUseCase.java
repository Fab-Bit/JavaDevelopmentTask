package org.fabbit;

import io.restassured.RestAssured;
import java.util.UUID;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.equalTo;

public class RepositoryUseCase {
    protected String apiUrl;
    protected String username;
    protected String token;

    public RepositoryUseCase(Platform platform, String apiUrl, String username, String token) {
        this.apiUrl = apiUrl;
        this.username = username;
        this.token = token;
    }

    public void createRepository() {
        RestAssured.given()
                .auth().basic(username, token)
                .body("{\"name\": \"test-repo-" + UUID.randomUUID().toString() + "\"}")
                .when()
                .post(apiUrl + "user/repos")
                .then()
                .statusCode(201)
                .body("name", matchesPattern("test-repo.*"));
    }

    public void createCommit() {
        RestAssured.given()
                .auth().basic(username, token)
                .body("{\"message\": \"Initial commit\", \"content\": \"dGVzdCBjb250ZW50Cg==\"}")
                .when()
                .put(apiUrl + "repos/" + username + "/test-repo/contents/README.md")
                .then()
                .statusCode(201)
                .body("commit.message", equalTo("Initial commit"));
    }

    public void createPullRequest() {
        RestAssured.given()
                .auth().basic(username, token)
                .body("{\"title\": \"Add new feature\", \"head\": \"new-feature\", \"base\": \"main\"}")
                .when()
                .post(apiUrl + "repos/" + username + "/test-repo/pulls")
                .then()
                .statusCode(201)
                .body("title", equalTo("Add new feature"));
    }
}