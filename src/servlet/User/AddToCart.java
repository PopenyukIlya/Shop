package servlet.User;

import beans.Cart;
import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/addProduct" })
public class AddToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddToCart() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        int product_id = Integer.parseInt(request.getParameter("id"));
        String product_name = request.getParameter("name");
        float product_price = Float.parseFloat(request.getParameter("price"));


        String errorString = null;
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        if (loginedUser == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        int user_account_id= 0;
        user_account_id = loginedUser.getId();
        Cart cart=new Cart();
        cart.setProduct_price(product_price);
        cart.setProduct_name(product_name);
        cart.setProduct_id(product_id);
        cart.setUser_account_id(user_account_id);
        cart.setQuantity(1);
        try {
            DBUtils.addProduct(conn, cart);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Если происходит ошибка, forward (перенаправить) к странице оповещающей ошибку.
        if (errorString != null) {
            // Сохранить информацию в request attribute перед тем как forward к views.
            request.setAttribute("errorString", errorString);
            //
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
