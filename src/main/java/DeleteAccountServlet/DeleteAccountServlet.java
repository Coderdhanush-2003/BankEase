package DeleteAccountServlet;

import java.io.IOException;
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

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // SQL query to fetch account details
            String sql = "SELECT * FROM customers WHERE account_no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Setting account details in the request
                request.setAttribute("accountNo", rs.getString("account_no"));
                request.setAttribute("fullName", rs.getString("full_name"));
                request.setAttribute("address", rs.getString("address"));
                request.setAttribute("mobileNo", rs.getString("mobile_no"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("accountType", rs.getString("account_type"));
            } else {
                // No account found with the given account number
                request.setAttribute("errorMessage", "No account found with the given account number.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: Could not retrieve account details.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Forwarding the request back to the JSP page
        request.getRequestDispatcher("Acc_Delete.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establishing connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // SQL query to delete the account
            String sql = "DELETE FROM customers WHERE account_no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNo);

            // Execute the delete
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                // Delete successful
                request.setAttribute("successMessage", "Account with account number " + accountNo + " deleted successfully.");
            } else {
                // Delete failed
                request.setAttribute("errorMessage", "Failed to delete account.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: Failed to delete account.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Forwarding the request back to the JSP page
        request.getRequestDispatcher("Acc_Delete.jsp").forward(request, response);
    }
}
