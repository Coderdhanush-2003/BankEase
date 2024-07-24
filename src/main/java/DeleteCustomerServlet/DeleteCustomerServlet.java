package DeleteCustomerServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("accountNo");

        if (accountNo == null || accountNo.isEmpty()) {
            // Handle if account number is not available in session
            response.sendRedirect("error.jsp");
            return;
        }

        Connection conn = null;
        PreparedStatement deleteTransactionsStmt = null;
        PreparedStatement deleteCustomerStmt = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // Begin transaction
            conn.setAutoCommit(false);

            // Delete all transactions related to the customer
            String deleteTransactionsQuery = "DELETE FROM transactions WHERE account_no = ?";
            deleteTransactionsStmt = conn.prepareStatement(deleteTransactionsQuery);
            deleteTransactionsStmt.setString(1, accountNo);
            int deletedTransactions = deleteTransactionsStmt.executeUpdate();

            // Delete customer record
            String deleteCustomerQuery = "DELETE FROM customers WHERE account_no = ?";
            deleteCustomerStmt = conn.prepareStatement(deleteCustomerQuery);
            deleteCustomerStmt.setString(1, accountNo);
            int deletedCustomer = deleteCustomerStmt.executeUpdate();

            if (deletedCustomer != 1) {
                throw new SQLException("Failed to delete customer account");
            }

            // Commit transaction
            conn.commit();

            // Invalidate session (log out the user)
            session.invalidate();

            // Redirect to a page confirming account deletion
            response.sendRedirect("accountDeleted.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction if any exception occurs
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            // Redirect to error page or handle error gracefully
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (deleteTransactionsStmt != null) {
                    deleteTransactionsStmt.close();
                }
                if (deleteCustomerStmt != null) {
                    deleteCustomerStmt.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Restore auto-commit mode
                    conn.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }
}
