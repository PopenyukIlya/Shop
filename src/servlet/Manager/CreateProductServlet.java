package servlet.Manager;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Product;
import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/createProduct" })
public class CreateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateProductServlet() {
        super();
    }

    // Отобразить страницу создания продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        String errorString = null;
       int syze=0;
        try {
            syze = DBUtils.queryProduct(conn).size()+1;
            DBUtils dbUtils=new DBUtils();

            UserAccount loginedUser = MyUtils.getLoginedUser(session);
            String role=dbUtils.findRole(conn,loginedUser);
        if (loginedUser == null||role==null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
        }
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loginedUser);
        request.setAttribute("errorString", errorString);
        request.setAttribute("syze", syze);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();

            return;
        }
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        dispatcher.forward(request, response);
    }

    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        int id =  Integer.parseInt(request.getParameter("id"));
        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }
        Product product = new Product(id, name, price);

        String errorString = null;




        if (errorString == null) {
            try {
                DBUtils.insertProduct(conn, product);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }

}