package Entity;

public enum Status {
    PENDING("Chờ xử lý"),COMPLETE("Đã hoàn thành"),CANCEL("Hủy Đơn"), PAID("Chờ Thanh Toán");
    private String label;

    Status(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
