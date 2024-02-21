package servlets;

import model.QuerySelector;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

@WebServlet (name = "userPage", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
        if (userBean.getUserType() == UserType.student) {
            LinkedList<String[]> courses = QuerySelector.userCourses(userBean.getId());
            req.getSession().setAttribute("data", courses);
        }

        req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
        if (userBean.getUserType() == UserType.student && req.getParameter("course") != null) {
            String course = req.getParameter("course");
            LinkedList<String[]> courseInfo = QuerySelector.courseInfo(course);

            req.setAttribute("courseName", course);
            req.setAttribute("courseData", courseInfo);
        }

        req.getSession().getAttribute("userBean");
        req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
    }

}
