package CustomerDepositServlet;

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

@WebServlet("/CustomerDepositServlet")
public class CustomerDepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String amountStr = request.getParameter("amount");
        double amount = Double.parseDouble(amountStr);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Dhanush12345");

            // Start a transaction
            conn.setAutoCommit(false);

            // Retrieve the current balance and customer_id
            String query = "SELECT initial_balance, customer_id FROM customers WHERE account_no = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, accountNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("initial_balance");
                int customerId = rs.getInt("customer_id");
                double newBalance = currentBalance + amount;

                // Update the balance in the customers table
                query = "UPDATE customers SET initial_balance = ? WHERE account_no = ?";
                ps = conn.prepareStatement(query);
                ps.setDouble(1, newBalance);
                ps.setString(2, accountNo);
                ps.executeUpdate();

                // Retrieve the updated balance from the database
                query = "SELECT initial_balance FROM customers WHERE account_no = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, accountNo);
                rs = ps.executeQuery();

                if (rs.next()) {
                    double updatedBalance = rs.getDouble("initial_balance");

                    // Update session attribute with new balance
                    session.setAttribute("balance", updatedBalance);

                    // Save the transaction in the transactions table
                    query = "INSERT INTO transactions (account_no, transaction_type, amount, transaction_date) VALUES (?, 'Deposit', ?, NOW())";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, accountNo);
                    ps.setDouble(2, amount);
                    ps.executeUpdate();

                    // Commit the transaction
                    conn.commit();

                    // Set attributes for DepositMessage.jsp
                    request.setAttribute("accountNo", accountNo);
                    request.setAttribute("amount", amount);
                    request.setAttribute("newBalance", updatedBalance);

                    // Forward to DepositMessage.jsp
                    request.getRequestDispatcher("/Depositmessage.jsp").forward(request, response);
                } else {
                    // Account not found
                    out.println("<html><body>");
                    out.println("<h2>Error: Account not found!</h2>");
                    out.println("</body></html>");
                }
            } else {
                // Account not found
                out.println("<html><body>");
                out.println("<h2>Error: Account not found!</h2>");
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
