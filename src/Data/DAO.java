package Data;

import Entity.*;
import Global.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection conn = MYSQLConnection.getConnection();
    public DAO(){;}
    public DAO(Connection conn){this.conn = conn;}

    public List<Customer> getCustomer(){
        String sql = "select * from customer where 1=1";
        PreparedStatement pstmt = null;
        List<Customer> customers = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customers.add(customer);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public List<Product> getProduct(){
        String sql = "select * from product where 1=1";
        PreparedStatement pstmt = null;
        List<Product> products = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Product pr = new Product();
                pr.setId(rs.getString("id"));
                pr.setName(rs.getString("name"));
                pr.setQuantity(rs.getInt("quantity"));
                products.add(pr);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    public List<Order> getOrder(){
        String sql = "select * from `order` where 1=1";
        PreparedStatement pstmt = null;
        List<Order> orders = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getString("id"));
                order.setCus_id(rs.getInt("cus_id"));
                order.setDatetime(Date.parseDate(rs.getString("datetime")));
                orders.add(order);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public List<OrderDetail> getOrderDetail(){
        String sql = "select * from orderdetail where 1=1";
        PreparedStatement pstmt = null;
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrder_id(rs.getString("order_id"));
                orderDetail.setProduct_id(rs.getString("product_id"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(Status.valueOf(rs.getString("status")));
                orderDetails.add(orderDetail);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return orderDetails;
    }


}
