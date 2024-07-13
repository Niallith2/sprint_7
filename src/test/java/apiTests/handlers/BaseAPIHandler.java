package apiTests.handlers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BaseAPIHandler {
    @Step("Проверка кода ответа")
    public static void checkStatusCode(Response response, int expectedCode){
        response.then().statusCode(expectedCode);
    }

}
