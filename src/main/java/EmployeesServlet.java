import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            if (!HibernateHelper.verifySession(req)) {
                resp.sendRedirect("login");
                return;
            }
            if(!HibernateHelper.verifyManager(req)){
                resp.sendRedirect("home");
                return;
            }

            out.write("""
                    <head>
                        <meta charset="UTF-8">
                        <link rel="stylesheet" href="style.css">
                        <title>Employee List</title>
                    </head>
                    """);
            out.write("<body>");
            req.getRequestDispatcher("navbar.jsp").include(req, resp);
            out.write("<div class=\"table-wrapper\">");
            out.write("<h1>Employee List</h1>");
            out.write("<table border=\"1\">");
            out.write( """
                        <col style="width:18%">
                        <col style="width:18%">
                    	<col style="width:24%">
                    	<col style="width:15%">
                    	<col style="width:15%">
                    	<col style="width:10%">

                    """);
            out.write("""
                    <thead>
                              <tr>
                                <th>Username</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Gender</th>
                                <th>Country</th>
                                <th>IsManager</th>
                              </tr>
                            </thead>
                            <tbody>
                    """);

            EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAO();
            List<Employee> employees = employeeDAO.getAllEmployees();
            for (Employee employee : employees) {
                out.write(getFormRow(employee));
            }


            out.write("</tbody></table></div></body>");
        }
    }

    private String getFormRow(Employee param){
        return String.format("""
                <tr>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                          </tr>
                """, param.username, param.name, param.email, param.gender, param.country, param.manager ? "Manager" : "Employee");
    }


}
