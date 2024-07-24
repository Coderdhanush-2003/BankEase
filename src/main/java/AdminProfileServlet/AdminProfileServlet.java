package AdminProfileServlet;

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

@WebServlet("/AdminProfileServlet")
public class AdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Check if admin is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminUsername") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String adminUsername = (String) session.getAttribute("adminUsername");
        System.out.println("Admin Username from session: " + adminUsername); // Print adminUsername to console

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM admins WHERE username=?");
            ps.setString(1, adminUsername);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Retrieve admin details
                int adminId = rs.getInt("admin_id");
                String adminFullName = rs.getString("full_name");
                String adminEmail = rs.getString("email");
                String adminCreatedAt = rs.getString("created_at"); // assuming created_at is a timestamp or datetime

                System.out.println("Admin ID: " + adminId);
                System.out.println("Full Name: " + adminFullName);
                System.out.println("Email: " + adminEmail);
                System.out.println("Created At: " + adminCreatedAt);

                // Set attributes in request
                request.setAttribute("adminId", adminId);
                request.setAttribute("adminUsername", adminUsername);
                request.setAttribute("adminFullName", adminFullName);
                request.setAttribute("adminEmail", adminEmail);
                request.setAttribute("adminCreatedAt", adminCreatedAt);

                // Forward to adminprofile.jsp
                request.getRequestDispatcher("Admin_Profile.jsp").forward(request, response);
            } else {
                System.out.println("No admin profile found for username: " + adminUsername);
                out.println("<p>Admin profile not found</p>");
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error fetching admin profile: " + e.getMessage() + "</p>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
