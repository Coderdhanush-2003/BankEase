package WithdrawalServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerWithdrawalServlet")
public class CustomerWithdrawalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("accountNo");
        double currentBalance = (double) session.getAttribute("balance");

        // Retrieve withdrawal amount from the form
        double withdrawAmount = Double.parseDouble(request.getParameter("amount"));

        // Validate withdrawal amount
        if (withdrawAmount <= 0 || withdrawAmount > currentBalance) {
            // Redirect back to the withdrawal page with an error message
            response.sendRedirect("Customer_Withdraw.jsp?error=Invalid withdrawal amount");
            return;
        }

        Connection conn = null;
        PreparedStatement updateBalanceStmt = null;
        PreparedStatement insertTransactionStmt = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // Begin transaction
            conn.setAutoCommit(false);

            // Update customer balance
            String updateBalanceQuery = "UPDATE customers SET initial_balance = initial_balance - ? WHERE account_no = ?";
            updateBalanceStmt = conn.prepareStatement(updateBalanceQuery);
            updateBalanceStmt.setDouble(1, withdrawAmount);
            updateBalanceStmt.setString(2, accountNo);
            int updatedRows = updateBalanceStmt.executeUpdate();

            if (updatedRows != 1) {
                throw new SQLException("Failed to update customer balance");
            }

            // Insert transaction record
            String insertTransactionQuery = "INSERT INTO transactions (account_no, transaction_type, amount, transaction_date) VALUES (?, 'Withdraw', ?, ?)";
            insertTransactionStmt = conn.prepareStatement(insertTransactionQuery);
            insertTransactionStmt.setString(1, accountNo);
            insertTransactionStmt.setDouble(2, withdrawAmount);
            insertTransactionStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            int insertedRows = insertTransactionStmt.executeUpdate();

            if (insertedRows != 1) {
                throw new SQLException("Failed to insert transaction record");
            }

            // Commit transaction
            conn.commit();

            // Update session attribute for balance
            session.setAttribute("balance", currentBalance - withdrawAmount);

            // Redirect to success page
            response.sendRedirect("messagewithdraw.jsp?amount=" + withdrawAmount + "&balance=" + (currentBalance - withdrawAmount));

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
            response.sendRedirect("messagewithdraw.jsp");
        } finally {
            // Close resources
            try {
                if (updateBalanceStmt != null) {
                    updateBalanceStmt.close();
                }
                if (insertTransactionStmt != null) {
                    insertTransactionStmt.close();
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
