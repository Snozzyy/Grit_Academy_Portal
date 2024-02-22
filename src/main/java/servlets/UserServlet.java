package servlets;

import model.QuerySelector;
import model.StateType;
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

        // Show assigned coursed for logged in students
        if (userBean != null && userBean.getStateType() == StateType.confirmed && userBean.getUserType() == UserType.student) {
            LinkedList<String[]> courses = QuerySelector.userCourses(userBean.getId());
            req.getSession().setAttribute("data", courses);
            req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
        // Show all students and courses for logged in teachers
        } else if (userBean != null && userBean.getStateType() == StateType.confirmed && userBean.getUserType() == UserType.teacher) {
            LinkedList<String[]> students = QuerySelector.allStudents();
            LinkedList<String[]> courses = QuerySelector.allCourses();

            req.getSession().setAttribute("studentsData", students);
            req.getSession().setAttribute("coursesData", courses);
            req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = (UserBean) req.getSession().getAttribute("userBean");
        // For students
        // If requirements are met, show all students and teachers in chosen course
        if (userBean != null && userBean.getUserType() == UserType.student && req.getParameter("course") != null) {
            String course = req.getParameter("course");
            LinkedList<String[]> courseInfo = QuerySelector.courseInfo(course);

            req.setAttribute("courseName", course);
            req.setAttribute("courseData", courseInfo);
            req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
        // For teachers
        } else if (userBean != null && userBean.getUserType() == UserType.teacher) {
            // Show all courses for assigned to chosen student
            if (req.getParameter("student") != null) {
                int studentId = Integer.parseInt(req.getParameter("student"));
                LinkedList<String[]> studentData = QuerySelector.userCourses(studentId);

                req.setAttribute("studentData", studentData);
                req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
            // Show all students assigned for chosen course
            } else if (req.getParameter("course") != null) {
                String course = req.getParameter("course");
                LinkedList<String[]> courseInfo = QuerySelector.courseInfo(course);

                req.setAttribute("courseName", course);
                req.setAttribute("courseData", courseInfo);
                req.getRequestDispatcher("JSP/userPage.jsp").forward(req, resp);
            }
        } else
            // If no user is logged in, redirect to log in
            resp.sendRedirect("/login");
    }
}