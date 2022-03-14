import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class ForgotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Reset Password</title>");

            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            req.getRequestDispatcher("forgot.html").include(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        boolean manager = req.getParameter("manager") != null;
        if(manager){
            req.getSession().setAttribute("manager", req.getParameter("manager"));
        }
        EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
        Employee employee = employeeDAO.getEmployeeByUsername(username, manager);
        if(employee != null) {
            String resetToken = getToken();
            Date date = new Date();
            req.getSession().setAttribute("token", resetToken);
            req.getSession().setAttribute("username", username);

            req.getSession().setAttribute("date", date);
            sendEmail(employee, resetToken);
        }
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Reset Password</title>");

            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            out.println("<p style='color:green; position: absolute;'>Password Reset Email Sent!</p>");

            req.getRequestDispatcher("forgot.html").include(req, resp);

        }
    }

    private String getToken(){
        StringBuilder token = new StringBuilder();
        long currentTimeInMilisecond = Instant.now().toEpochMilli();
        return token.append(currentTimeInMilisecond).append("-")
                .append(UUID.randomUUID().toString()).toString();
    }

    private void sendEmail(Employee employee, String token){
        String msg = String.format("<a href=\"http://localhost:8080/EmployeeReimbursementP1/reset?token=%s\">Click to Reset Password</a><p>Expires after 2 hours</p>", token);
        try(InputStream in = getServletContext().getResourceAsStream(
                "WEB-INF/credentials.properties")) {
            EmailService.sendMail(employee.email, "Reset password request for " + employee.username, msg, in);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
