import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            if (!HibernateHelper.verifySession(req)) {
                resp.sendRedirect("login");
                return;
            }
            String hidden = HibernateHelper.verifyManager(req) ? "" : "hidden";
            out.write("""
                    <head>
                        <meta charset="UTF-8">
                        <title>Reimbursement List</title>
                        <link rel="stylesheet" href="style.css">
                    </head>
                    """);
            out.write("<body>");
            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            out.write("<div class=\"table-wrapper\">");
            out.write("<h1>Reimbursement List</h1>");
            req.getRequestDispatcher("filter.jsp").include(req, resp);
            out.write("<table border=\"1\">");
            String cols = """
                        <col style="width:13%">
                        $REPLACE
                    	<col style="width:22%">
                    	<col style="width:13%">
                    	<col style="width:13%">
                    """;
            String col = "<col " + hidden + " style=\"width:13%\">";
            String o = cols.replace("$REPLACE", col);
            out.write(o);
            out.write(col);
            out.write(col);
            out.write(String.format("""
                    <thead>
                              <tr>
                                <th>Amount</th>
                                <th %s>Employee</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Image</th>
                                <th %s colspan="2">Actions</th>
                              </tr>
                            </thead>
                            <tbody>
                    """, hidden, hidden));

            ReimbursementDAO dao = ReimbursementDAOFactory.getReimbursementDAO();
            EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
            List<Reimbursement> reimbursements =
                    (Boolean) req.getSession().getAttribute("manager")
                            ? dao.getReimbursements()
                            : dao.getReimbursementsByEmpId((Integer) req.getSession().getAttribute("id"));

            for (Reimbursement reimbursement : reimbursements) {
                String employee = employeeDAO.getEmployeeByID(reimbursement.emp_id).name;
                out.write(getFormRow(reimbursement, (Integer) req.getSession().getAttribute("id"), hidden, employee));
            }


            out.write("</tbody></table></div></body>");
            //out.println("Done.");    }
        }
    }

    private String getFormRow(Reimbursement param, int man_id, String hidden, String employee){
        String resolved = param.status.equals("PENDING") ? "" : "class=\"resolved\"";
        return String.format("""
                <tr>
                            <td>%s</td>
                            <td %s>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                            <td><img src="%s" alt="Uploaded image" width=100 height=100></td>
                            <td %s><a %s href="approve?id=%d&man=%d&status=APPROVED">Approve</a></td>
                            <td %s><a %s href="approve?id=%d&man=%d&status=DENIED">Deny</a></td>
                          </tr>
                """, DecimalFormat.getCurrencyInstance().format(param.amount), hidden, employee, param.description, param.status, param.src, hidden, resolved, param.id, man_id, hidden, resolved, param.id, man_id);
    }


}
