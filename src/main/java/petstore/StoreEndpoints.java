package petstore;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import petstore.entities.Order;
import petstore.urls.Urls;

import java.io.IOException;

public class StoreEndpoints {
    public Order placeOrderForPet(Order order) throws IOException {
        Gson gson = new Gson();
        String orderString = gson.toJson(order);
        RequestBody requestBody = RequestBody.create(orderString.getBytes());
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Urls.ORDER_URL)
                .header("Content-Type", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("POST " + responseBody);

        Order createdOrder = gson.fromJson(responseBody, Order.class);
        return createdOrder;
    }

    public Order getOrderById(Long orderId) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(Urls.ORDER_URL + orderId)
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("GET " + responseBody);
        Gson gson = new Gson();
        Order createdOrder = gson.fromJson(responseBody, Order.class);
        return createdOrder;
    }

    public void deleteOrderById(Long orderId) throws IOException {
        Request request = new Request.Builder()
                .delete()
                .url(Urls.ORDER_URL + orderId)
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("DELETE " + responseBody);
    }

    public void returnInventory() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(Urls.INVENTORY_URL)
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("Inventory: " + responseBody);
    }


/*    public Order updateOrderForPet (Order order) throws IOException {
        Gson gson = new Gson();
        String orderString = gson.toJson(order);
        RequestBody requestBody = RequestBody.create(orderString.getBytes());
        Request request = new Request.Builder()
                .put(requestBody)
                .url(Urls.ORDER_URL + order.getId())
                //.header("Content-Type", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("UPDATE " + responseBody);
        Order updatedOrder = gson.fromJson(responseBody, Order.class);
        return updatedOrder;
    }*/

}
