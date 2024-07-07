package Global;

public class Validation {
    public static boolean validateProductId(String productId) {
        String pattern = "^(MS|NE|SE)[0-9]{6}$";
        return productId.matches(pattern);
    }

    public static boolean validateProductName(String productName) {
        String pattern = "^[a-zA-Z ]+$";
        return productName.matches(pattern);
    }

    public static boolean validateCustomerName(String customerName) {
        String pattern = "^[a-zA-Z ]?{3,50}+$";
        return  customerName.matches(pattern);
    }

    public static boolean validateOrderId(String orderId) {
        String pattern = "^ORDPM[0-9]{8}$";
        return orderId.matches(pattern);
    }


}
