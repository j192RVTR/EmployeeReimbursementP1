import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
            resp.sendRedirect("view");
        }
    }
}
