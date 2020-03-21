package servlet.User;


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

import beans.Contact;
import beans.Product;
import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/contacts" })
public class ContactsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address;
        String number;
        String errorString = null;
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);
        UserAccount loginedUser = MyUtils.getLoginedUser(session);


        if (loginedUser == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        try {
            if (DBUtils.findContacts(conn,loginedUser)!=null){
            Contact contact=DBUtils.findContacts(conn,loginedUser);
            address=contact.getAddress();
            number=contact.getPhone_number();
            // Сохранить информацию в request attribute перед тем как forward (перенаправить).
            request.setAttribute("address", address);
            request.setAttribute("phone_number", number);}
            else {
                request.setAttribute("address", null);
                request.setAttribute("phone_number", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("user", loginedUser);
        request.setAttribute("errorString", errorString);



        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/contacts.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        String address = (String) request.getParameter("address");
        String phone_number = (String) request.getParameter("phone_number");
        String errorString = null;
        boolean hasError = false;
        if (address == null || phone_number == null) {
            hasError = true;
            errorString = "Required name and price!";
        }else {
            try {
                if (DBUtils.findContacts(conn,loginedUser)!=null){
                    Contact contact = new Contact(loginedUser.getId(), address, phone_number);
                    DBUtils.updateContact(conn, contact);
                }else {
                    Contact contact = new Contact(loginedUser.getId(), address, phone_number);
                    DBUtils.insertContact(conn, contact);
                }}
             catch (SQLException e)
            {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/contacts.jsp");

            dispatcher.forward(request, response);
        }

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/userInfo.jsp");
            dispatcher.forward(request, response);
        }

        else {
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

}