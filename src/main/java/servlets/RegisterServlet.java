package servlets;

import model.PrivilegeType;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
        if ( userBean != null && userBean.getUserType() == UserType.teacher && userBean.getPrivilegeType() == PrivilegeType.admin) {
            req.getRequestDispatcher("JSP/register.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/user").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
    }
}
