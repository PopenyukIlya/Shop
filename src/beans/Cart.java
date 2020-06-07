package beans;

public class Cart {
    private int user_account_id;
    private int product_id;
   private float product_price;
    private String product_name;
    private int quantity;

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

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Cart() {
    }

    public Cart(int user_account_id, int product_id, int product_price, String product_name, int quantity) {
        this.user_account_id = user_account_id;
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_name = product_name;
        this.quantity = quantity;
    }


}
