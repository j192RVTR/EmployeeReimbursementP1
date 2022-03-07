import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        resp.getWriter().print("<link rel=\"stylesheet\" href=\"style.css\">");

        HttpSession session = req.getSession();
        session.invalidate();

        req.getRequestDispatcher("navbar.jsp").include(req, resp);
        out.println("<br>You are logged out successfully.");
    }
}
