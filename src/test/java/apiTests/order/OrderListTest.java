package apiTests.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import io.restassured.response.Response;

import static apiTests.handlers.BaseAPIHandler.checkStatusCode;
import static apiTests.handlers.OrderAPIHandler.checkOrdersInResponse;
import static apiTests.handlers.OrderAPIHandler.getOrdersList;

@DisplayName("4. Проверка, что в тело ответа возвращается список заказов.")
public class OrderListTest {

   @Test
   @DisplayName("Получение списка заказов")
   public void getOrderListIsCorrect() {
       Response response = getOrdersList();
       checkStatusCode(response, 200);
       checkOrdersInResponse(response);
   }
}

