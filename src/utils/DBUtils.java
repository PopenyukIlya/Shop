package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Product;
import beans.UserAccount;

public class DBUtils {

    public static UserAccount findUser(Connection conn, //
                                       String userName, String password) throws SQLException {

        String sql = "Select a.id, a.Gender,a.role from User_Account a " //
                + " where a.User_Name = ? and a.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {

            int id=rs.getInt("id");
            String gender = rs.getString("Gender");
            String role=rs.getString("role");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setId(id);
            user.setGender(gender);
            user.setRole(role);
            return user;
        }
        return null;
    }

    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
        String sql = "Select a.id, a.Password, a.Gender, a.role from User_Account a "//
                + " where a.User_Name = ? ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            int id=rs.getInt("id");
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            String role=rs.getString("role");
            UserAccount user = new UserAccount();
            user.setId(id);
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            user.setRole(role);
            return user;
        }
        return null;
    }



    public static void insertUser(Connection conn, UserAccount userAccount) throws SQLException {
        String sql = "Insert into user_account(USER_NAME, GENDER,PASSWORD) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, userAccount.getUserName());
        pstm.setString(2, userAccount.getGender());
        pstm.setString(3, userAccount.getPassword());

        pstm.executeUpdate();
    }

    public static List<Product> Product(Connection conn) throws SQLException {
        String sql = "Select a.id, a.Name, a.Price from Product a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }
    public static List<Product> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.id, a.Name, a.Price from Product a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }

    public static Product findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.id, a.Name, a.Price from Product a where a.id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int id=rs.getInt("id");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product(id, name, price);
            return product;
        }
        return null;
    }

    public static Product findProductByName(Connection conn, String name) throws SQLException {
        String sql = "Select a.id, a.Name, a.Price from Product a where a.NAME=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
             name = rs.getString("Name");
            int id=rs.getInt("id");
            float price = rs.getFloat("Price");
            Product product = new Product(id, name, price);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update Product set Name =?, Price=? where id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setInt(3, product.getId());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Name,Price) values (?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, int id) throws SQLException {
        String sql = "Delete From shop.product where id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        pstm.executeUpdate();
    }

    public static void addProduct(Connection conn, Cart cart) throws SQLException {
            String sql = "Insert into cart(user_account_id, product_id,quantity) values (?,?,?)";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, cart.getUser_account_id());
            pstm.setInt(2, cart.getProduct_id());
            pstm.setInt(3, cart.getQuantity());

            pstm.executeUpdate();

    }

    public static List<Cart> findCart(Connection conn, UserAccount loginedUser) throws SQLException {
        String sql = "Select a.user_account_id, a.product_id, a.quantity from cart a where user_account_id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        int user_account_id=loginedUser.getId();
        pstm.setInt(1, user_account_id);
        ResultSet rs = pstm.executeQuery();
        List<Cart> list = new ArrayList<Cart>();
        while (rs.next()) {
            int product_id = rs.getInt("product_id");
            int quantity = rs.getInt("quantity");
            Cart cart = new Cart();
            cart.setProduct_id(product_id);
            cart.setQuantity(quantity);
            list.add(cart);
        }
        return list;
    }

}
