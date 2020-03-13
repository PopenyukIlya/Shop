package beans;

public class Contact {
    private int user_account_id;
    private String address;
    private String phone_number;

    public int getUser_account_id() {return user_account_id; }

    public void setUser_account_id(int user_account_id) {this.user_account_id = user_account_id;  }

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getPhone_number() {return phone_number;}

    public void setPhone_number(String phone_number) {this.phone_number = phone_number;}

    public Contact() {  }

    public Contact(int user_account_id, String address, String phone_number) {
        this.user_account_id = user_account_id;
        this.address = address;
        this.phone_number = phone_number;
    }
}
