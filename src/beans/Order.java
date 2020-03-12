package beans;

public class Order {

    private int user_accaunt_id;
    private int product_id;
    private int quantity;
    private String address;
    private int phone_number;
    private boolean paid;

    public int getUser_accaunt_id() {
        return user_accaunt_id;
    }

    public void setUser_accaunt_id(int user_accaunt_id) {
        this.user_accaunt_id = user_accaunt_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Order() {
    }

    public Order(int user_accaunt_id, int product_id, int quantity, String address, int phone_number, boolean paid) {
        this.user_accaunt_id = user_accaunt_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.address = address;
        this.phone_number = phone_number;
        this.paid = paid;
    }
}
