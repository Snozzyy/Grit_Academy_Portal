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
                "(SELECT id, fname, lname, 'student' AS user_type, 'user' AS privilege_type " +
                "FROM students " +
                "WHERE username = '%s' AND password = '%s') " +
                "UNION ALL " +
                "(SELECT id, fname, lname, 'teacher' AS user_type, privilege_type " +
                "FROM teachers " +
                "WHERE username = '%s' AND password = '%s');", username, password, username, password);

        LinkedList<String[]> userData = MySQLConnector.selectQuery("root", "", query);

        // Checks if user with username and password exists
        if (!userData.isEmpty()) {
            int id = Integer.parseInt(userData.get(0)[0]);
            String userType = userData.get(0)[3];
            String privilegeType = userData.get(0)[4];
            String fname = userData.get(0)[1];

            UserBean userBean = new UserBean();
            // Should be fixed in default constructor
            userBean.setId(id);
            userBean.setStateType(StateType.confirmed); // set in context, ask Lukas if it's correct
            userBean.setUserType(UserType.valueOf(userType));
            userBean.setPrivilegeType(PrivilegeType.valueOf(privilegeType));
            userBean.setFname(fname);

            req.getServletContext().setAttribute("userState", StateType.confirmed);
            req.getSession().setAttribute("userBean", userBean);
            req.getRequestDispatcher("JSP/userpage.jsp").forward(req, resp);

            // Testing only
            System.out.println(userBean.getId() + "\n" + userBean.getStateType() + "\n" + userBean.getUserType() + "\n" + userBean.getPrivilegeType() + "\n");

        } else {
            req.getSession().setAttribute("errorMessage", "User does not exist");
            req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
        }
    }
}
