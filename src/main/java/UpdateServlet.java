import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!HibernateHelper.verifySession(req)){
            resp.sendRedirect("login");
            return;
        }

        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            int id = (Integer) req.getSession().getAttribute("id");
            EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
            Employee e = employeeDAO.getEmployeeByID(id);
            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            req.setAttribute("header", "Update Profile");
            req.setAttribute("username", wrap(e.username));
            req.setAttribute("name", wrap(e.name));
            req.setAttribute("email", wrap(e.email));
            req.setAttribute("gender", e.gender);
            req.setAttribute("country", e.country);
            req.setAttribute("manager", e.manager ? "true" : "false");
            req.setAttribute("id", wrap(Integer.toString(e.id)));
            req.setAttribute("action", "update");
            req.getRequestDispatcher("profile.jsp").include(req, resp);
        }
    }

    private String wrap(String in){
        return "\"" + in + "\"";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!HibernateHelper.verifySession(req)){
            resp.sendRedirect("login");
            return;
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<link rel=\"stylesheet\" href=\"style.css\">");
        out.print("<title>Update Profile</title>");

        int id = (Integer) req.getSession().getAttribute("id");
        EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
        Employee e = employeeDAO.getEmployeeByID(id);
        e.setUsername(req.getParameter("username"));
        e.setName(req.getParameter("name"));
        e.setEmail(req.getParameter("email"));
        e.setGender(req.getParameter("gender"));
        e.setCountry(req.getParameter("country"));
        employeeDAO.update(e);
        req.getRequestDispatcher("navbar.jsp").include(req, resp);
        out.println("<p style='color:green; position: absolute;'>Update Success!</p>");
        req.setAttribute("header", "Update Profile");
        req.setAttribute("username", wrap(e.username));
        req.setAttribute("name", wrap(e.name));
        req.setAttribute("email", wrap(e.email));
        req.setAttribute("gender", e.gender);
        req.setAttribute("country", e.country);
        req.setAttribute("manager", e.manager ? "true" : "false");
        req.setAttribute("id", wrap(Integer.toString(e.id)));
        req.setAttribute("action", "update");
        req.getRequestDispatcher("profile.jsp").include(req, resp);
        out.close();
    }
}
