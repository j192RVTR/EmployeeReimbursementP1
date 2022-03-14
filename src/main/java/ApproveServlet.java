import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class ApproveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            if(!HibernateHelper.verifySession(req)){
                resp.sendRedirect("login");
                return;
            }
            if (!HibernateHelper.verifyManager(req)) {
                resp.sendRedirect("view");
                return;
            }
            String status = req.getParameter("status");
            int id = Integer.parseInt(req.getParameter("id"));
            int man_id = (Integer) req.getSession().getAttribute("id");
            ReimbursementDAO dao = ReimbursementDAOFactory.getReimbursementDAO();
            dao.approve(man_id, id, status);
            EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
            Reimbursement reimbursement = dao.getReimbursementById(id);
            int emp_id = reimbursement.emp_id;
            Employee employee = employeeDAO.getEmployeeByID(emp_id);
            sendEmail(employee, reimbursement, status);
            req.getSession().setAttribute("reim.msg", "MSG");
            resp.sendRedirect("view");
        }
    }

    private void sendEmail(Employee employee, Reimbursement reimbursement, String status){
        String msg = getMessage(employee, reimbursement, status);
        try(InputStream in = getServletContext().getResourceAsStream(
                "WEB-INF/credentials.properties")) {
            EmailService.sendMail(employee.email, "Update to reimbursement for " + employee.username, msg, in);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private String getMessage(Employee employee, Reimbursement reimbursement, String status){
        String header = String.format("<h1>Your reimbursement request has been %s!</h1>", status);
        String body = String.format("""
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
                        <td>Description</td>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>%s</td>
                    </tr>
                </table>
                """, employee.name, employee.username, reimbursement.description, status);
        return header + body;
    }
}
