package apiTests.courier;

import apiTests.entities.Courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static apiTests.handlers.CourierAPIHandler.*;


@DisplayName("2. Логин курьера")
public class LoginCourierTest extends BaseCourierTest {
    @Test
    @DisplayName("Логин курьера в системе")
    public void loginCourierIsCorrect() {
        Courier courier = new Courier(login, password, firstName);
        createCourier(courier);
        Response response = loginCourier(courier);
        checkStatusCode(response, 200);
        checkCourierIdIsNotNull(courier);
    }


@Test
@DisplayName("Логин курьера в систему без обязательных данных")
public void loginCourierWithoutAllParamsIsFail() {
    Courier courier = new Courier("", "");
    createCourier(courier);
    Response response = loginCourier(courier);
    checkStatusCode(response, 400);
    checkResponseMessage(response, "message", "Недостаточно данных для входа");
}

    @Test
    @DisplayName("Логин курьера в систему без логина")

    public void loginCourierWithoutLoginIsFail() {
        Courier courier = new Courier("", password);
        createCourier(courier);
        Response response = loginCourier(courier);
        checkStatusCode(response, 400);
        checkResponseMessage(response, "message", "Недостаточно данных для входа");
    }

    @Test
    @DisplayName("Логин курьера в систему без пароля")
    public void loginCourierWithoutPasswordIsFail() {
        Courier courier = new Courier(login, "");
        createCourier(courier);
        Response response = loginCourier(courier);
        checkStatusCode(response, 400);
        checkResponseMessage(response, "message", "Недостаточно данных для входа");
    }

    @Test
    @DisplayName("Логин курьера в систему с некорректным логином")
    public void loginCourierIncorrectLoginIsFail() {
        Courier courier = new Courier(login, password, firstName);
        createCourier(courier);
        Response response = loginCourier(new Courier("Некорректный" + login, password, firstName));
        checkStatusCode(response, 404);
        checkResponseMessage(response, "message", "Учетная запись не найдена");
    }

    @Test
    @DisplayName("Логин курьера в систему с некорректным паролем")
    public void loginCourierIncorrectPasswordIsFail() {
        Courier courier = new Courier(login, password, firstName);
        createCourier(courier);
        Response response = loginCourier(new Courier(login, "Некорректный" + password, firstName));
        checkStatusCode(response, 404);
        checkResponseMessage(response, "message", "Учетная запись не найдена");
    }
}
