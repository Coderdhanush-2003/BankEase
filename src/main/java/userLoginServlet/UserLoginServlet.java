package userLoginServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        String accountNo = request.getParameter("txtname");
        String password = request.getParameter("txtpwd");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // Prepare SQL statement
            ps = connection.prepareStatement("SELECT * FROM customers WHERE account_no=? AND password=?");
            ps.setString(1, accountNo);
            ps.setString(2, password);

            // Execute query
            rs = ps.executeQuery();

            if (rs.next()) {
                boolean passwordChanged = rs.getBoolean("password_changed");
                System.out.println("passwordChanged: " + passwordChanged);

                if (!passwordChanged) {
                    System.out.println("Redirecting to Customer_New_Password.jsp");
                    request.setAttribute("accountNo", accountNo);
                    
                    RequestDispatcher rd = request.getRequestDispatcher("Customer_New_Password.jsp");
                    rd.forward(request, response);

                } else {
                    // Create session and set attributes
                    HttpSession session = request.getSession();
                    session.setAttribute("customerId", rs.getInt("customer_id"));
                    session.setAttribute("accountNo", accountNo);
                    session.setAttribute("customerName", rs.getString("full_name"));
                    session.setAttribute("email", rs.getString("email"));
                    session.setAttribute("balance", rs.getDouble("initial_balance"));
                    session.setAttribute("address", rs.getString("address"));
                    session.setAttribute("mobileNo", rs.getString("mobile_no"));
                    session.setAttribute("accountType", rs.getString("account_type"));
                    session.setAttribute("dob", rs.getDate("dob"));
                    session.setAttribute("idProof", rs.getString("id_proof"));
                    session.setAttribute("createdAt", rs.getTimestamp("created_at"));
                    
                    // Redirect to customer dashboard
                    RequestDispatcher rd = request.getRequestDispatcher("Customer_Dashboard.jsp");
                    rd.forward(request, response);
                }
            } else {
                // Login failed, provide feedback to user
                PrintWriter out = response.getWriter();
                out.println("<font color='red' size='18'> Login Failed!!<br>");
                out.println("<a href='Customer.jsp'>Try AGAIN!!</a>");
                out.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in finally block
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
