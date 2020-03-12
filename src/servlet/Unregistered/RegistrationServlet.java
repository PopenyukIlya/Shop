package servlet.Unregistered;

import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Connection conn = MyUtils.getStoredConnection(request);

        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        UserAccount userAccount = new UserAccount(userName, gender, password);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
            } else

            try {
                user = DBUtils.findUser(conn, userName);
                if (user != null) {
                    hasError = true;
                    errorString = "This user already exist";
                }
                else {
                    DBUtils.insertUser(conn, userAccount);
                }
                }  catch (SQLException e)
            {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }

            if (hasError) {
                user = new UserAccount();
                user.setUserName(userName);
                user.setPassword(password);

                // Сохранить информацию в request attribute перед forward.
                request.setAttribute("errorString", errorString);
                request.setAttribute("user", user);

                // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");

                dispatcher.forward(request, response);
            }

            response.sendRedirect(request.getContextPath() + "/login");

    }
}

