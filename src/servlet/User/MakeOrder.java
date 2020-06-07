package servlet.User;

import beans.Cart;
import beans.Contact;
import beans.Order;
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

@WebServlet(urlPatterns = { "/makeOrder" })
public class MakeOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakeOrder() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        int product_id = Integer.parseInt(request.getParameter("id"));//можно обрабатывать кол-во на складе
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float product_price = Float.parseFloat(request.getParameter("product_price"));
        String product_name = request.getParameter("product_name");


        String errorString = null;
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        try {
            DBUtils.inBlackList(loginedUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (loginedUser == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String address;
        String number;
        Order order=new Order();
        int user_account_id = loginedUser.getId();
        //тут нужно по айди юзера найти его контакты
        try {
            if (DBUtils.findContacts(conn,loginedUser)!=null){
                Contact contact=DBUtils.findContacts(conn,loginedUser);
                 address=contact.getAddress();
                number=contact.getPhone_number();
                order.setProduct_price(product_price);
                order.setUserName(loginedUser.getUserName());
                order.setName_product(product_name);
                order.setQuantity(quantity);
                order.setAddress_and_phone_number("Address:"+address+". Number:"+number);
                DBUtils.addOrder(conn, order);
              }
            else {
                //тут делать что-то если контактов юзера нет
            }
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
