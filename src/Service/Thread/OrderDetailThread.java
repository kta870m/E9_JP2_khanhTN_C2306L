package Service.Thread;

import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import Service.ShopManagement;

import java.util.List;

public class OrderDetailThread extends ShopManagement implements Runnable{
    public static List<OrderDetail> orderDetails;

    public OrderDetailThread(Order order, Product product, OrderDetail orderDetail){
        super(order, product, orderDetail);
    }


    public OrderDetail addOrderDetail(){
        super.getOrderDetail().setId(orderDetails.size() + 1);
        super.getOrderDetail().setOrder_id(super.getOrder().getId());
        super.getOrderDetail().setProduct_id(super.getProduct().getId());
        super.getOrderDetail().setQuantity(super.getOrderDetail().getQuantity());
        super.getOrderDetail().setStatus(super.getOrderDetail().getStatus());
        orderDetails.add(super.getOrderDetail());

        return super.getOrderDetail();
    }

    @Override
    public void run(){
        addOrderDetail();
    }
}
