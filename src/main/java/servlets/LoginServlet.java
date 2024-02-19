package servlets;

import org.example.Grit_Academy_Portal.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletContext().getAttribute("stateType") == StateType.anonymous)
            req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
        else
            req.getRequestDispatcher("/user").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletContext().getAttribute("stateType") == StateType.anonymous) {
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
                userBean.setStateType(StateType.confirmed); // Is also set in context, ask Lukas which is correct
                userBean.setUserType(UserType.valueOf(userType));
                userBean.setPrivilegeType(PrivilegeType.valueOf(privilegeType));
                userBean.setFname(fname);

                req.getServletContext().setAttribute("stateType", StateType.confirmed);
                req.getSession().setAttribute("userBean", userBean);
                req.getRequestDispatcher("JSP/userpage.jsp").forward(req, resp);

            } else {
                req.getSession().setAttribute("errorMessage", "User does not exist");
                req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
            }

        } else {
            // Fail-safe mechanism, only log out the user when they press the log-out button
            if (req.getParameter("logout").equals("Log out")) {
                req.getServletContext().setAttribute("stateType", StateType.anonymous);
                req.getSession().invalidate();
                resp.sendRedirect("index.jsp");

            // If user somehow sends post-request to /login without clicking log-out button, redirect to userpage
            } else {
                req.getRequestDispatcher("/user").forward(req, resp);
            }
        }
    }
}