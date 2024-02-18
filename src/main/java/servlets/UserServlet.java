package servlets;

import org.example.Grit_Academy_Portal.model.StateType;
import org.example.Grit_Academy_Portal.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
        if (userBean.getStateType() == StateType.confirmed) {
            req.getRequestDispatcher("JSP/userpage.jsp").forward(req,resp);
        } else {
            req.getRequestDispatcher("/login").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
