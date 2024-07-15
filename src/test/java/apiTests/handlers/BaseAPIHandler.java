package apiTests.handlers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.core.IsEqual.equalTo;

public class BaseAPIHandler {
    @Step("Проверка кода ответа")
    public static void checkStatusCode(Response response, int expectedCode){
        response.then().statusCode(expectedCode);
    }

    @Step("Проверка текста ответа")
    public static void checkResponseMessage(Response response, String expectedMessage, Object object){
        response.then().assertThat().body(expectedMessage, equalTo(object));
    }
}
