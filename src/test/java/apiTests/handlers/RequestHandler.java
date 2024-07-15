package apiTests.handlers;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestHandler {
    public Response sendPostRequest(Object object, String url){
        return given()
                .header("Content-Type", "application/json")
                .body(object)
                .when()
                .post(url);
    }

    public Response sendDeleteRequest(String url){
        return given().delete(url);
    }

    public Response sendPutRequest(HashMap<String, Integer> params, String url){
         return given()
                .queryParams(params)
                .when()
                .put(url);
    }

    public Response sendGetRequest(String url){
        return given().get(url);
    }




}
