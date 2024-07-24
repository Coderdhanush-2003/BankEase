package RegisterServlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banking_app";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Dhanush12345";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch parameters from registration form
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        BigDecimal initialBalance = new BigDecimal(request.getParameter("initialBalance"));
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        // Generate random account number and password
        String accountNo = generateAccountNumber();
        String password = generatePassword();

        // Database operations
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            // Insert into customers table
            String insertCustomerSql = "INSERT INTO customers (full_name, address, mobile_no, email, "
                    + "account_type, initial_balance, dob, id_proof, account_no, password, created_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertCustomerSql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, address);
            pstmt.setString(3, mobileNo);
            pstmt.setString(4, email);
            pstmt.setString(5, accountType);
            pstmt.setBigDecimal(6, initialBalance);
            pstmt.setString(7, dob);
            pstmt.setString(8, idProof);
            pstmt.setString(9, accountNo);
            pstmt.setString(10, password);
            pstmt.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
            pstmt.close();

            // Insert into accounts table
            String insertAccountSql = "INSERT INTO accounts (customer_id, account_no, balance, created_at) "
                    + "VALUES (LAST_INSERT_ID(), ?, ?, ?)";
            pstmt = conn.prepareStatement(insertAccountSql);
            pstmt.setString(1, accountNo);
            pstmt.setBigDecimal(2, initialBalance);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
            pstmt.close();

            // Insert initial balance into transactions table as a deposit
            String insertTransactionSql = "INSERT INTO transactions (account_no, transaction_type, amount, transaction_date, description, transaction_reference) "
                    + "VALUES (?, 'Deposit', ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertTransactionSql);
            pstmt.setString(1, accountNo);
            pstmt.setBigDecimal(2, initialBalance);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(4, "Initial deposit");
            pstmt.setString(5, generateTransactionReference());
            pstmt.executeUpdate();
            pstmt.close();

            conn.close();

            // URL encode the parameters
            String encodedAccountNo = URLEncoder.encode(accountNo, StandardCharsets.UTF_8.toString());
            String encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8.toString());
            String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.toString());

            // Redirect to registration successful page with encoded parameters
            response.sendRedirect("registration-success.jsp?accountNo=" + encodedAccountNo + "&password=" + encodedPassword + "&email=" + encodedEmail);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database errors or other exceptions
            request.setAttribute("errorMessage", "Database error: " + e.getMessage()); // Example error message
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Method to generate a random 6-character alphanumeric account number
    private String generateAccountNumber() {
        Random random = new Random();
        int accountNum = random.nextInt(900000) + 100000; // Generates a 6-digit number
        return "ACC" + accountNum;
    }

    // Method to generate a random 10-character password
    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // Method to generate a random transaction reference
    private String generateTransactionReference() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder reference = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            reference.append(characters.charAt(random.nextInt(characters.length())));
        }
        return reference.toString();
    }
}
