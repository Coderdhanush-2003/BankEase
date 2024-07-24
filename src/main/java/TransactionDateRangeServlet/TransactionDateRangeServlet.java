package TransactionDateRangeServlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TransactionDateRangeServlet")
public class TransactionDateRangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_app";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Dhanush12345";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Obtain HttpSession
        String accountNo = (String) session.getAttribute("accountNo"); // Retrieve accountNo from session

        System.out.println("Session retrieved accountNo: " + accountNo); // Debugging line

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        System.out.println("Received startDate: " + startDateStr); // Debugging line
        System.out.println("Received endDate: " + endDateStr); // Debugging line

        List<Transaction> transactions = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Attempting database connection...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "SELECT transaction_type, amount, transaction_date FROM transactions WHERE account_no = ?";

            // Add date range filter if both dates are provided
            if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
                query += " AND transaction_date BETWEEN ? AND ?";
            }

            query += " ORDER BY transaction_date DESC";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, accountNo);

            // Set date parameters if date range is provided
            if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
                pstmt.setTimestamp(2, Timestamp.valueOf(startDateStr + " 00:00:00"));
                pstmt.setTimestamp(3, Timestamp.valueOf(endDateStr + " 23:59:59"));
            }

            System.out.println("Executing query: " + query);
            System.out.println("With accountNo: " + accountNo); // Debugging line
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getBigDecimal("amount"));
                transaction.setDate(rs.getTimestamp("transaction_date"));
                transactions.add(transaction);

                // Print each transaction data
                System.out.println("Transaction Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Date: " + transaction.getDate());
                System.out.println("-----------------------------");
            }

            System.out.println("Transactions retrieved successfully: " + transactions.size());

            request.setAttribute("transactions", transactions);
            request.setAttribute("startDate", startDateStr);
            request.setAttribute("endDate", endDateStr);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Transactions set in request attribute. Forwarding to JSP.");
        RequestDispatcher rd = request.getRequestDispatcher("Customer_View_Transactions_By_Date.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static class Transaction {
        private String type;
        private BigDecimal amount;
        private Timestamp date;

        public Transaction() {}

        public Transaction(String type, BigDecimal amount, Timestamp date) {
            this.type = type;
            this.amount = amount;
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public Timestamp getDate() {
            return date;
        }

        public void setDate(Timestamp date) {
            this.date = date;
        }
    }
}
