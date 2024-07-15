package apiTests.order;
import apiTests.entities.Color;
import apiTests.entities.Order;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static apiTests.handlers.BaseAPIHandler.*;
import static apiTests.handlers.OrderAPIHandler.*;

@RunWith(Parameterized.class)
@DisplayName("3. Создание заказа")
public class CreateOrderTest {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private final List<Color> scooterColor;
    private Integer trackId;

    public CreateOrderTest(List<Color> scooterColor) {
        this.scooterColor = scooterColor;
    }

    @Parameterized.Parameters
    public static Object[][] initParamsForTest() {
        return new Object[][] {
                {List.of()},
                {List.of(Color.BLACK)},
                {List.of(Color.GREY)},
                {List.of(Color.BLACK, Color.GREY)},
        };
    }

    @Before
    @Step("Подготовка данных")
    public void prepareTestData() {
        this.firstName = "Азамат";
        this.lastName = "Мурзин";
        this.address = "Казань, ул. Тестовая, 1234";
        this.phone = "8 (800) 555-35-35";
        this.rentTime = "1";
        this.deliveryDate = "2024-07-13";
        this.comment = "Тестовый комментарий";
    }

    @After
    public void cleanUp(){
        cancelOrder(trackId);
    }


    @Test
    @DisplayName("Создание заказа")
    public void createOrderIsCorrect() {
        Response response = createOrder(new Order(firstName, lastName, address, phone, rentTime, deliveryDate, comment, scooterColor));
        checkTrackInOrder(response);
        trackId = getTrack(response);
    }
}
