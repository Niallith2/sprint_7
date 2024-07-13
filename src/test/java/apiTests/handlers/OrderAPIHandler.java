package apiTests.handlers;

import apiTests.entities.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderAPIHandler {
    @Step("Отправка запроса на создание заказа")
    public static Response createOrder(Order order){
        return new RequestHandler()
                .sendPostRequest(order, ScooterServiceUrls.CREATE_ORDER);
    }

    @Step("Проверка на создание заказа")
    public static void checkTrackInOrder(Response response){
        response.then().statusCode(201).and().body("track", notNullValue());
    }

    @Step("Отменить заказ")
    public static Response cancelOrder(int track){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("track", track);
        return new RequestHandler().sendPutRequest(map, ScooterServiceUrls.CANCEL_ORDER);
    }

    @Step("Получить трек-номер")
    public static int getTrack(Response response){
        return response.as(Order.class).getTrack();
    }

    @Step("Отправка запроса на получение списка заказов")
    public static Response getOrdersList(){
       return new RequestHandler().sendGetRequest(ScooterServiceUrls.GET_ORDERS);
    }
    @Step("Проверить наличие заказов в ответе")
    public static void checkOrdersInResponse(Response response){
        response.then().body("orders", notNullValue());
    }
}
