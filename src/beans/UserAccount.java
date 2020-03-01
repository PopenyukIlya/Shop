package beans;

public class UserAccount {

    public static final String GENDER_MALE ="M";
    public static final String GENDER_FEMALE = "F";
    public String role;



    private String userName;
    private String gender;
    private String password;


    public UserAccount() {

    }

    public UserAccount(String userName, String gender, String password) {
        this.userName = userName;
        this.gender = gender;
        this.password = password;
    }

    public UserAccount(String role, String userName, String gender, String password) {
        this.role = role;
        this.userName = userName;
        this.gender = gender;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}