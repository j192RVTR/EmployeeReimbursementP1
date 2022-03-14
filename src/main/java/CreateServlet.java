import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class CreateServlet extends HttpServlet {
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
            req.setAttribute("header", "Create Profile");
            req.setAttribute("username", "");
            req.setAttribute("name", "");
            req.setAttribute("email", "");
            req.setAttribute("gender", "");
            req.setAttribute("country", "");
            req.setAttribute("action", "create");
            req.getRequestDispatcher("profile.jsp").include(req, resp);
        }
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
        out.print("<title>Create Employee</title>");

        EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
        Employee e = new Employee();
        e.setUsername(req.getParameter("username"));
        e.setPassword(req.getParameter("username"));
        e.setName(req.getParameter("name"));
        e.setEmail(req.getParameter("email"));
        e.setGender(req.getParameter("gender"));
        e.setCountry(req.getParameter("country"));
        e.setManager(req.getParameter("manager") != null);
        employeeDAO.add(e);
        sendEmail(e);

        req.getRequestDispatcher("navbar.jsp").include(req, resp);
        out.println(String.format("<p style='color:green; position: absolute;'>Sent email to %s!</p>", e.email));
        req.setAttribute("header", "Create Employee");
        req.setAttribute("username", "");
        req.setAttribute("name", "");
        req.setAttribute("email", "");
        req.setAttribute("gender", "");
        req.setAttribute("country", "");
        req.setAttribute("action", "create");
        req.getRequestDispatcher("profile.jsp").include(req, resp);
        out.close();
    }

    private String wrap(String in){
        return "\"" + in + "\"";
    }
    private void sendEmail(Employee employee){
        String header = String.format("<h1>Employee details for %s.</h1>" +
                "<p>Initially, username is the same as password. Reset with reset password.</p>", employee.name);

        String msg = String.format("""
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Manager</td>
                        <td>%s</td>
                    </tr>
                </table>
                """, employee.name, employee.username, employee.email, employee.gender, employee.country, employee.manager);
        try(InputStream in = getServletContext().getResourceAsStream(
                "WEB-INF/credentials.properties")) {
            EmailService.sendMail(employee.email, "Welcome to the Employee Reimbursement App,  " + employee.username, header+msg, in);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
