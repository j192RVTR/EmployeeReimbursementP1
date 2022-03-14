import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ResetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sessToken = (String) req.getSession().getAttribute("token");
        String paramToken = req.getParameter("token");
        Date sessDate = (Date) req.getSession().getAttribute("date");
        Date currentDate = new Date();



        if( paramToken== null || !paramToken.equals(sessToken) || currentDate.getTime() - sessDate.getTime() > 7200 * 1000){
            resp.sendRedirect("login");
            return;
        }

        String username = (String) req.getSession().getAttribute("username");


        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Reset Password</title>");

            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            out.print(String.format("""
                    <div class="form-wrapper">
                        <h1>Reset Password</h1>
                        <form action="reset" method="post">
                            <div><label for="password">New Password </label><input type="password" name="password" id="password" required></div>
                            <input hidden type="text" name="username" id="username" value=%s>
                            
                            <div class="center">
                                <input class="continue" type="submit" value="Reset">
                            </div>
                        </form>
                    </div>
                    """, username));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean manager = req.getSession().getAttribute("manager") != null;
        EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
        Employee employee = employeeDAO.getEmployeeByUsername(username, manager);
        employee.setPassword(password);
        employeeDAO.update(employee);
        req.getSession().invalidate();
        resp.sendRedirect("login");
    }
}
