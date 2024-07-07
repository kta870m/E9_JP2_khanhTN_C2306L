package Service.Thread;

import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import Entity.Status;
import Service.ShopManagement;

public class StatusUpdate extends ShopManagement implements Runnable {
    public StatusUpdate(Order order, Product product, OrderDetail orderDetail){
        super(order, product, orderDetail);
    }

    public OrderDetail updateOrderDetail(){
        if(super.getOrderDetail().getQuantity() > super.getProduct().getQuantity()){
            super.getOrderDetail().setStatus(Status.CANCEL);
        }else {
            super.getOrderDetail().setStatus(Status.PAID);
        }
        return super.getOrderDetail();
    }

    @Override
    public void run(){
        updateOrderDetail();
    }
}
