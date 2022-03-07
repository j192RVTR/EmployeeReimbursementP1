import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HibernateHelper.addTestEmployee();

        if(!HibernateHelper.verifySession(req)){
            resp.sendRedirect("login");
            return;
        }
        resp.setContentType("text/html");
        resp.getWriter().print("<link rel=\"stylesheet\" href=\"style.css\">");
        req.getRequestDispatcher("navbar.jsp").include(req, resp);
        req.getRequestDispatcher("home.html").include(req, resp);
    }
}
