package cscorner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String n = request.getParameter("txtname");
        String p = request.getParameter("txtpwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            PreparedStatement ps = connection.prepareStatement("SELECT username, admin_id, email, created_at, full_name FROM admins WHERE username=? AND password=?");
            ps.setString(1, n);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("adminUsername", rs.getString("username"));
                session.setAttribute("adminName", rs.getString("full_name"));
                session.setAttribute("adminId", rs.getString("admin_id"));
                session.setAttribute("adminEmail", rs.getString("email"));
                session.setAttribute("adminCreatedAt", rs.getString("created_at"));
                response.sendRedirect("adminDashboard");
            } else {
                out.println("<font color='red' size='18'> Login Failed!!<br>");
                out.println("<a href='Customer.jsp'>Try AGAIN!!</a>");
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
