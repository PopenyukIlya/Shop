package beans;

public class Order {

  private int order_id;
  private String product_name;
  private float product_price;
  private int quantity;
  private String userName;
  private String address_and_phone_number;
  private boolean completed;

  public float getProduct_price() {
    return product_price;
  }

  public void setProduct_price(float product_price) {
    this.product_price = product_price;
  }



  public Order(String product_name, float product_price, int quantity, String userName, String address_and_phone_number, boolean completed) {
    this.product_name = product_name;
    this.product_price = product_price;
    this.quantity = quantity;
    this.userName = userName;
    this.address_and_phone_number = address_and_phone_number;
    this.completed = completed;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public int getOrder_id() {
    return order_id;
  }

  public void setOrder_id(int order_id) {
    this.order_id = order_id;
  }

  public String getName_product() {
    return product_name;
  }

  public void setName_product(String product_name) {
    this.product_name = product_name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAddress_and_phone_number() {
    return address_and_phone_number;
  }

  public void setAddress_and_phone_number(String address_and_phone_number) {
    this.address_and_phone_number = address_and_phone_number;
  }


  public Order(int order_id, String product_name, float product_price, int quantity, String userName, String address_and_phone_number, boolean paid) {
    this.order_id = order_id;
    this.product_name = product_name;
    this.product_price = product_price;
    this.quantity = quantity;
    this.userName = userName;
    this.address_and_phone_number = address_and_phone_number;
    this.completed = paid;
  }

  public Order() {
  }
}
