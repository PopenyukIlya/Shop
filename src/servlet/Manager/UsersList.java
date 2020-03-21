package servlet.Manager;

import beans.Product;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = { "/usersList" })
public class UsersList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsersList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        String errorString = null;
        String name = (String) request.getParameter("userName");

        List<UserAccount> list = null;
        try {
            list = DBUtils.queryUsers(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("listOfUsers", list);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/usersList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String name = String.valueOf(request.getParameter("userName"));
        HttpSession session = request.getSession();
        String errorString = null;

        List<UserAccount> list = null;
        List<UserAccount> listSearch=new ArrayList<UserAccount>();
        try {
            list = DBUtils.queryUsers(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
if (name.length()!=0) {
    for (int i = 0; i < list.size(); i++) {
        int index1=-1;
        index1 = list.get(i).getUserName().indexOf(name); // 2
        if (index1!=-1) {
            listSearch.add(list.get(i));
        }
    }
    request.setAttribute("listOfUsers", listSearch);
}else request.setAttribute("listOfUsers", list);
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);


        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/usersList.jsp");
        dispatcher.forward(request, response);
    }

}
