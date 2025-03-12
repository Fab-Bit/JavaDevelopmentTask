package org.fabbit;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.UUID;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.equalTo;

public class RepositoryUseCase {
    protected String apiUrl;
    protected String username;
    protected String token;

    public RepositoryUseCase(Platform platformEnum, String apiUrl, String username, String token) {
        this.apiUrl = apiUrl;
        this.username = username;
        this.token = token;

    }
    private String newBranch = "new-feature-branch" + UUID.randomUUID().toString();

    public void createCommit() {
        try {
            Response response = RestAssured.given()
                    .auth().oauth2(token)
                    .contentType(ContentType.JSON)
                    .body("{\"message\": \"Test commit\", \"content\": \"dGVzdCBjb250ZW50Cg==\", \"branch\": \"test-branch\"}")
                    .when()
                    .put(apiUrl + "/repos/" + username + "/JavaDevelopmentTask/contents/testfile" + UUID.randomUUID().toString());

            if (response.getStatusCode() == 201) {
                response.then()
                        .statusCode(201)
                        .body("commit.message", equalTo("Test commit"))
                        .log().all();
            } else {
                System.err.println("API call failed with status code: " + response.getStatusCode() + " Response body: " + response.getBody().asString());
            }
        } catch (Exception e) {
            System.err.println("Exception during API call: " + e.getMessage());
        }
    }

    public void createPullRequest() {
        try {
            Response response = RestAssured.given()
                    .auth().oauth2(token)
                    .contentType(ContentType.JSON)
                    .body("{\"title\": \"Add new feature\", \"head\": \"test-branch\", \"base\": \"master\"}")
                    .when()
                    .post(apiUrl + "/repos/" + username + "/JavaDevelopmentTask/pulls");

            if (response.getStatusCode() == 201) {
                response.then()
                        .log().all();
               throw new AssertionError("Failed to create pull request: Validation Failed with status code 422");
            } else {
                System.err.println("Failed to create pull request with status code: " + response.getStatusCode() + " Response body: " + response.getBody().asString());
            }
        } catch (Exception e) {
            System.err.println("Exception during API call: " + e.getMessage());
        }
    }

    public String getBaseBranchSHA() {
        return RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .get(apiUrl + "/repos/" + username + "/JavaDevelopmentTask" + "/git/refs/heads/master")
                .then()
                .statusCode(200)
                .extract()
                .path("object.sha");  // Extracting SHA
    }

    // Method to create a new branch based on the SHA of the base branch
    public void createBranch(String sha) {
        RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body("{\"ref\": \"refs/heads/" + newBranch + "\", \"sha\": \"" + sha + "\"}")
                .when()
                .post(apiUrl + "/repos/" + username + "/JavaDevelopmentTask" + "/git/refs")
                .then()
                .statusCode(201)  // Expecting HTTP 201 Created
                .body("ref", equalTo("refs/heads/" + newBranch))  // Verify the branch name
                .body("object.sha", Matchers.notNullValue());  // Check if SHA is returned for the new branch
    }
}