import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!HibernateHelper.verifySession(req)){
            resp.sendRedirect("login");
            return;
        }

        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Profile</title>");

            int id = (Integer) req.getSession().getAttribute("id");
            EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
            Employee e = employeeDAO.getEmployeeByID(id);
            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            req.setAttribute("header", "View Profile");
            req.setAttribute("username", wrap(e.username));
            req.setAttribute("name", wrap(e.name));
            req.setAttribute("email", wrap(e.email));
            req.setAttribute("gender", e.gender);
            req.setAttribute("country", e.country);
            req.setAttribute("id", wrap(Integer.toString(e.id)));
            req.setAttribute("action", "None");
            req.getRequestDispatcher("profile.jsp").include(req, resp);
        }
    }

    private String wrap(String in){
        return "\"" + in + "\"";
    }
}
