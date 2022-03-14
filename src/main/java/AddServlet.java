import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!HibernateHelper.verifySession(req)){
            resp.sendRedirect("login");
            return;
        }
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Submit Request</title>");

            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            req.getRequestDispatcher("add.html").include(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementDAO reimbursementDAO = ReimbursementDAOFactory.getReimbursementDAO();
        double amount = Double.parseDouble(req.getParameter("amount"));
        String description = req.getParameter("description");
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setEmp_id((Integer) req.getSession().getAttribute("id"));
        reimbursement.setStatus("PENDING");
        reimbursement.setAmount(amount);
        reimbursement.setDescription(description);
        Part part = req.getPart("file");
        if(part!=null) {
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String save = HibernateHelper.uploadImage(req, resp, filename);
            if(save != null)
                reimbursement.setSrc(save);
        }
        boolean success = reimbursementDAO.addReimbursement(reimbursement);

        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {

            out.print("<link rel=\"stylesheet\" href=\"style.css\">");
            out.print("<title>Submit Request</title>");

            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            if (success)
                out.println("<p style='color:green; position: absolute;'>Submitted reimbursement request!</p>");
            else
                out.println("<p style='color:red; position: absolute;'>Failed to submit reimbursement request!</p>");
            req.getRequestDispatcher("add.html").include(req, resp);
        }
    }
}
