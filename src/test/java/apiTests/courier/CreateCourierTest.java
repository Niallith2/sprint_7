package apiTests.courier;

import apiTests.entities.Courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static apiTests.handlers.CourierAPIHandler.*;


@DisplayName("1. Создание курьера")
public class CreateCourierTest extends BaseCourierTest{
    @Test
    @DisplayName("Создание курьера")
    public void createCourierIsCorrect(){
        Response response = createCourier(new Courier(login, password, firstName));
        isCourierCreated(response);
        checkStatusCode(response, 201);
        checkResponseMessage(response, "ok", true);
    }

    @Test
    @DisplayName("Создание 2 одинаковых курьеров")
    public void create2sameCouriersIsFail(){
        Response response = createCourier(new Courier(login, password, firstName)); //Создали первого курьера
        isCourierCreated(response);
        checkStatusCode(response, 201);
        checkResponseMessage(response, "ok", true);
        //Проверили что курьер создался

        Response response2 = createCourier(new Courier(login, password, firstName)); //Создали 2 курьера, с теми же данными
        checkStatusCode(response2, 409);
        checkResponseMessage(response2, "message", "Этот логин уже используется. Попробуйте другой.");
    }

    @Test
    @DisplayName("Создание курьера без логина - курьер не создан")
    public void createCourierWithoutLoginIsFail(){
        Response response = createCourier(new Courier("", password, firstName));
        checkStatusCode(response, 400);
        checkResponseMessage(response, "message", "Недостаточно данных для создания учетной записи");
    }

    @Test
    @DisplayName("Создание курьера без пароля - курьер не создан")
    public void createCourierWithoutPasswordIsFail(){
        Response response = createCourier(new Courier(login, "", firstName));
        checkStatusCode(response, 400);
        checkResponseMessage(response, "message", "Недостаточно данных для создания учетной записи");
    }

}
