package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    private String id;
    private int cus_id;
    private LocalDateTime datetime;

    public Order() {;
    }

    public Order(String id, LocalDateTime datetime, int cus_id) {
        this.id = id;
        this.datetime = datetime;
        this.cus_id = cus_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", cus_id=" + cus_id +
                ", datetime=" + datetime +
                '}';
    }
}
