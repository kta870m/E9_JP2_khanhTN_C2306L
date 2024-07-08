import Data.DAO;
import Entity.*;
import Global.Validation;
import Service.ProductService;
import Service.Thread.OrderDetailThread;
import Service.Thread.OrderThread;
import Service.Thread.ProductThread;
import Service.Thread.StatusUpdate;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DAO data = new DAO();
        boolean flag = false;
        List<Product> products = data.getProduct();
        List<Customer> customers = data.getCustomer();
        List<Order> orders = data.getOrder();
        List<OrderDetail> orderDetails = data.getOrderDetail();

        ProductService ps = new ProductService();
        ps.productList = products;

        try{

           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           int choice;
           int cus_id;
           String productId;
           String orderId;
           Thread t1;
           Thread t2;
           Thread t3;
           Thread t4;

            int detail_id = orderDetails.size() + 1;
           do {
               System.out.println("1 - Insert New Order");
               System.out.println("2 - Exit");
               System.out.print("Enter your choice: ");
               choice = Integer.parseInt(br.readLine());
               switch (choice){
                   case 1:
                       System.out.print("Enter customer id: ");
                       cus_id = Integer.parseInt(br.readLine());
                       System.out.print("Enter Product id: ");
                       productId = br.readLine();
                       if(!Validation.validateProductId(productId)){
                           throw new Exception("Invalid Product Id");
                       }
                       System.out.print("Enter Order Id: ");
                       orderId = br.readLine();
                       if(!Validation.validateOrderId(orderId)){
                           throw new Exception("Invalid Order Id");
                       }
                       System.out.print("Enter Quantity: ");
                       int quantity = Integer.parseInt(br.readLine());

                       Order o1 = new Order(orderId, LocalDateTime.now(), cus_id);
                       Product p1 = ps.findById(productId);
                       OrderDetail od1 = new OrderDetail(detail_id, o1.getId(), p1.getId(), quantity, Status.PENDING);

                       OrderThread ot = new OrderThread(o1, p1, od1);
                       ProductThread pt = new ProductThread(o1, p1, od1);
                       OrderDetailThread odt = new OrderDetailThread(o1, p1, od1);
                       StatusUpdate sp = new StatusUpdate(o1, p1, od1);

                       ot.orders = orders;
                       odt.orderDetails = orderDetails;

                       t1 = new Thread(ot);
                       t2 = new Thread(odt);
                       t3 = new Thread(pt);
                       t4 = new Thread(sp);

                       try {
                           t1.start();
                           t1.join();
                           t2.start();
                           t2.join();
                           t3.start();
                           t3.join();
                           t4.start();
                           t4.join();
                       }catch (IOError e){
                           System.out.println(e.getMessage());
                       }
                        orderDetails.forEach(System.out::println);
                       break;
                   case 2:
                       break;
               }
           }while (!flag);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
}