package petstore;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import petstore.entities.Order;

import java.io.IOException;

public class StoreEndpointsTest {

    @DataProvider(name = "data-provider")
    public Object[][] setOrderId() {
        return new Object[][]{{1L}};
    }

    @Test(dataProvider = "data-provider")
    public void createOrderTest(Long orderId) throws IOException {
        StoreEndpoints storeEndpoints = new StoreEndpoints();
        Order order = new Order();
        order.setPetId(1);
        order.setId(orderId);
        order.setQuantity(-1);
        order.setStatus("Placed");
        order.setShipDate("2022-10-06T25:57:28.456Z");
        order.setComplete(true);

        Order createdOrder = storeEndpoints.placeOrderForPet(order);
        Assert.assertEquals(createdOrder.getId(), order.getId(),
                "IDs are not equal");
    }

    @Test(dataProvider = "data-provider")
    public void getOrderByIdTest(Long orderId) throws IOException {
        createOrderTest(orderId);
        Order storeEndpoints = new StoreEndpoints().getOrderById(orderId);
        Assert.assertEquals(orderId, storeEndpoints.getId());
    }

    @Test(dataProvider = "data-provider")
    public void deleteOrderByIdTest(Long orderId) throws IOException {
        createOrderTest(orderId);
        new StoreEndpoints().deleteOrderById(orderId);
        Order storeEndpoints = new StoreEndpoints().getOrderById(orderId);
        Assert.assertEquals(storeEndpoints.getMessage(), "Order not found");
    }

    @Test
    public void returnInventoryTest () throws IOException {
        new StoreEndpoints().returnInventory();
    }
}
