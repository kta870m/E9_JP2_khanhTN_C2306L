package Data;

import Entity.*;
import Global.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public void saveOrder(Map<String, Order> stringOrderMap){
        String sql = "insert into `order` values (?,?,?)";
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            Iterator<Map.Entry<String, Order>> iterator = stringOrderMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Order> entry = iterator.next();
                Timestamp ts = Timestamp.valueOf(entry.getValue().getDatetime());
                pstmt.setString(1, entry.getKey());
                pstmt.setInt(2, entry.getValue().getCus_id());
                pstmt.setTimestamp(3, ts);
            }
            pstmt.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveOrderDetail(Map<String, OrderDetail> orderDetailMap){
        String sql = "insert into orderdetail values (?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            Iterator<Map.Entry<String, OrderDetail>> it = orderDetailMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, OrderDetail> entry = it.next();
                pstmt.setInt(1, entry.getValue().getId());
                pstmt.setString(2, entry.getKey());
                pstmt.setString(3, entry.getValue().getProduct_id());
                pstmt.setInt(4, entry.getValue().getQuantity());
                pstmt.setString(5, entry.getValue().getStatus().getLabel());
            }
            pstmt.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateQuantity(Product product){
        String sql = "update product set quantity=? where id=?";
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, product.getQuantity());
            pstmt.setString(2, product.getId());
            pstmt.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
