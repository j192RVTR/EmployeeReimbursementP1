import jakarta.servlet.*;
import org.hibernate.Session;

public class MyServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {
        Session session = HibernateHelper.getSession();
    }

    public void contextDestroyed(ServletContextEvent e) {

    }
}