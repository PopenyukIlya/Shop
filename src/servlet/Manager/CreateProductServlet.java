package servlet.Manager;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


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

        String errorString = null;
        HttpSession session = request.getSession();


            UserAccount loginedUser = MyUtils.getLoginedUser(session);

        if (loginedUser == null||loginedUser.getRole()==null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loginedUser);
        request.setAttribute("errorString", errorString);



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


        String name = (String) request.getParameter("name");
        String priceStr = (String) request.getParameter("price");
        String errorString = null;
        boolean hasError = false;
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }

        if (name == null || price == 0) {
            hasError = true;
            errorString = "Required name and price!";
        }else {


            try {
                Product product = DBUtils.findProductByName(conn, name);
                if (product != null) {
                    hasError = true;
                    errorString = "This product already exist";
                }
                else {
                     product = new Product(name, price);
                    DBUtils.insertProduct(conn, product);
                }
            }  catch (SQLException e)
            {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }


        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        if (hasError) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");

            dispatcher.forward(request, response);
        }
        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/admin");
        }
    }

}