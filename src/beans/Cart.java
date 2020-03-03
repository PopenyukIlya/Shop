package beans;

public class Cart {
    private int user_account_id;
    private int product_id;
    private int quantity;

    public Cart() {
    }

    public Cart(int user_account_id, int product_id, int quantity) {
        this.user_account_id = user_account_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getUser_account_id() {
        return user_account_id;
    }

    public void setUser_account_id(int user_account_id) {
        this.user_account_id = user_account_id;
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
}
