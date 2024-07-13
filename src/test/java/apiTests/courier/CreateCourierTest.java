package apiTests.courier;


import apiTests.entities.Courier;
import apiTests.handlers.CourierAPIHandler;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static apiTests.handlers.CourierAPIHandler.*;


@DisplayName("1. Создание курьера")
public class CreateCourierTest {
    private String login;
    private String password;
    private String firstName;

    @Before
    @Step("Подготовка тестовых данных")
    public void prepareTestClient(){
        this.login = "courier-" + new Random().nextInt();
        this.password = "password-" + new Random().nextInt();
        this.firstName = "firstName-" + new Random().nextInt();
    }
    @After
    @Step("Удаление данных после теста")
    public void clear(){
        deleteCourier(getCourierID(new Courier(login, password)));
    }

    @Test
    @DisplayName("Создание курьера")
    public void createCourierIsCorrect(){
        Response response = createCourier(login, password, firstName);
        isCourierCreated(response);
        checkStatusCode(response, 201);
        checkResponseMessage(response, "ok", true);
    }

    @Test
    @DisplayName("Создание 2 одинаковых курьеров")
    public void create2sameCouriersIsFail(){
        Response response = createCourier(login, password, firstName); //Создали первого курьера
        isCourierCreated(response);
        checkStatusCode(response, 201);
        checkResponseMessage(response, "ok", true);
        //Проверили что курьер создался

        Response response2 = createCourier(login, password, firstName); //Создали 2 курьера, с теми же данными
        checkStatusCode(response2, 409);
        checkResponseMessage(response2, "message", "Этот логин уже используется. Попробуйте другой.");
    }
}
