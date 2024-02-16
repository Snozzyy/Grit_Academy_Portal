package servlets;

import org.example.Grit_Academy_Portal.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Search both student and teacher table for matching username and password.
        // Get their name, user_type and privilege_type
        String query = String.format(
                "(SELECT fname, lname, 'student' AS user_type, 'user' AS privilege_type " +
                "FROM students" +
                "WHERE username = '%s' AND password = '%s') " +
                "UNION ALL " +
                "(SELECT fname, lname, 'teacher' AS user_type, privilege_type " +
                "FROM teachers " +
                "WHERE username = '%s' AND password = '%s');", username, password, username, password);

        LinkedList<String[]> userData = MySQLConnector.selectQuery("root", "", query);

        // Checks if user with username and password exists
        if (!userData.isEmpty()) {
            // userData[0][2] contains user_type
            String userType = userData.get(0)[2];
            // userData[0][3] contains privilege_type
            String privilegeType = userData.get(0)[3];

            UserBean userBean = new UserBean();
            userBean.setStateType(StateType.confirmed);
            userBean.setUserType(UserType.valueOf(userType));
            userBean.setPrivilegeType(PrivilegeType.valueOf(privilegeType));
        } else {
            System.out.println("User doesn't exist");
        }
    }
}
