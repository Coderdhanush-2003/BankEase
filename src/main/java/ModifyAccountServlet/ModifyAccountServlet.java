package ModifyAccountServlet;

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

@WebServlet("/ModifyAccountServlet")
public class ModifyAccountServlet extends HttpServlet {
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
                request.setAttribute("createdat", rs.getString("created_at"));
                request.setAttribute("dob", rs.getString("dob"));
                request.setAttribute("idProof", rs.getString("id_proof"));
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
        request.getRequestDispatcher("Acc_Modify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        String createdat = request.getParameter("createdat"); // Should match database column 'created_at'
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establishing connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // SQL query to update account details
            String sql = "UPDATE customers SET full_name=?, address=?, mobile_no=?, email=?, account_type=?, created_at=?, dob=?, id_proof=? WHERE account_no=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullName);
            stmt.setString(2, address);
            stmt.setString(3, mobileNo);
            stmt.setString(4, email);
            stmt.setString(5, accountType);
            stmt.setString(6, createdat);
            stmt.setString(7, dob);
            stmt.setString(8, idProof);
            stmt.setString(9, accountNo);

            // Execute the update
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                // Update successful
                request.setAttribute("successMessage", "Account details for account number " + accountNo + " updated successfully.");
            } else {
                // Update failed
                request.setAttribute("errorMessage", "Failed to update account details.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: Failed to update account details.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Forwarding the request back to the JSP page
        request.getRequestDispatcher("Acc_Modify.jsp").forward(request, response);
    }
}
