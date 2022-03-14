import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(HibernateHelper.verifySession(req)){
            resp.sendRedirect("home");
        }

        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Login</title>");

            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            req.getRequestDispatcher("login.html").include(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(req.getParameter("manager"));
        Employee employee = employeeDAO.getEmployeeByUsernameAndPassword(username, password, req.getParameter("manager") != null);
        if(employee!=null) {
            HttpSession session = req.getSession();
            session.setAttribute("name", employee.getName());
            session.setAttribute("id", employee.getId());
            session.setAttribute("manager", employee.getManager());
            resp.sendRedirect("/EmployeeReimbursementP1");
            return;
        }


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<link rel=\"stylesheet\" href=\"style.css\">");
        req.getRequestDispatcher("navbar.jsp").include(req, resp);
        out.println("<p style='color:red; position: absolute;'>Login Failed!</p>");
        req.getRequestDispatcher("login.html").include(req, resp);
        out.close();



    }
}
