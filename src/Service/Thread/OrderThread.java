package Service.Thread;

import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import Entity.Status;
import Service.ShopManagement;

import java.util.List;

public class OrderThread extends ShopManagement implements Runnable{
    public static List<Order> orders;

    public OrderThread(Order order, Product product, OrderDetail orderDetail){
        super(order, product, orderDetail);
    }

    public Order addOrder() {
        if(super.getProduct().getQuantity() > super.getOrderDetail().getQuantity()) {
            orders.add(super.getOrder());
        }else{
            System.out.println("Not Enough Quantity");
        }
        return super.getOrder();
    }

    @Override
    public void run() {
        addOrder();
    }
}
