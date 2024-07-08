package Entity;

public enum Status {
    PENDING("PENDING"),COMPLETE("COMPLETE"),CANCEL("CANCEL"), PAID("PAID");
    private String label;

    Status(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
