package Service.Thread;

import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import Service.ShopManagement;

import java.util.List;

public class ProductThread extends ShopManagement implements Runnable{
    public ProductThread(){;}
    public ProductThread(Order order, Product product, OrderDetail orderDetail){
        super(order, product, orderDetail);
    }

    public Product updateProductQuantity(){
        if(super.getOrderDetail().getQuantity() <= super.getProduct().getQuantity()) {
            super.getProduct().setQuantity(
                    super.getProduct().getQuantity() - super.getOrderDetail().getQuantity()
            );
        }
        return super.getProduct();
    }

    @Override
    public void run(){
        updateProductQuantity();
    }

}
