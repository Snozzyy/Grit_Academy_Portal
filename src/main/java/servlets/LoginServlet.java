package servlets;

import org.example.Grit_Academy_Portal.model.MySQLConnector;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String query = String.format("SELECT * FROM students WHERE username = '%s' AND password = '%s'", username, password);
        LinkedList<String[]> userData = MySQLConnector.selectQuery(query, "root", "");
    }
}
