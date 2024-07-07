package Service;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;

public abstract class ShopManagement {
    private Order order;
    private Product product;
    private OrderDetail orderDetail;

    public ShopManagement() {;
    }

    public ShopManagement(Order order, Product product, OrderDetail orderDetail) {
        this.order = order;
        this.product = product;
        this.orderDetail = orderDetail;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
