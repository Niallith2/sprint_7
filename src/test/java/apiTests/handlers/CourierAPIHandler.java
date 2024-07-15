package apiTests.handlers;

import apiTests.entities.Courier;
import apiTests.entities.CourierResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class CourierAPIHandler extends BaseAPIHandler{
    @Step("Отправка запроса на создание курьера")
    public static Response createCourier(Courier courier){
        return new RequestHandler()
                .sendPostRequest(courier, ScooterServiceUrls.CREATE_COURIER);
    }



    @Step("Проверка создания курьера")
    public static void isCourierCreated(Response response){
        response.then().statusCode(201); //Если пришел код HTTP 201 Created, считаем что курьер создан
    }

    @Step("Удаление курьера")
    public static Response deleteCourier(int courierID){
        String url = ScooterServiceUrls.DELETE_COURIER + courierID;
        return new RequestHandler().sendDeleteRequest(url);
    }

    @Step("Логин курьера в системе")
    public static Response loginCourier(Courier courier){
        return new RequestHandler()
                .sendPostRequest(courier, ScooterServiceUrls.LOGIN_COURIER);
    }

    public static int getCourierID(Courier courier){
        try {
            return loginCourier(courier).as(CourierResponse.class).getId();
        }catch (NullPointerException e){
            return -1;//Вернем -1, если поймали NullPointerException, в случае если пытаемся получить несуществующий ID
        }

    }
    @Step("Проверка ID на !NULL")
    public static void checkCourierIdIsNotNull(Courier courier){
        Assert.assertTrue(getCourierID(courier) != -1);
    }
}
