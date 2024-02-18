package servlets;

import org.example.Grit_Academy_Portal.model.StateType;
import org.example.Grit_Academy_Portal.model.UserBean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Default StateType should be anonymous
        sce.getServletContext().setAttribute("userState", StateType.anonymous);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
