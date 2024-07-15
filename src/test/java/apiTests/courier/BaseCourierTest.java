package apiTests.courier;

import apiTests.entities.Courier;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;

import java.util.Random;

import static apiTests.handlers.CourierAPIHandler.*;

public class BaseCourierTest {
    protected String login;
    protected String password;
    protected String firstName;

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

}
