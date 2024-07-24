package userNewPasswordServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userNewPasswordServlet")
public class userNewPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String accountNo = request.getParameter("accountNo");

        System.out.println("Received accountNo: " + accountNo);
        System.out.println("Received newPassword: " + newPassword);
        System.out.println("Received confirmPassword: " + confirmPassword);

        if (newPassword.equals(confirmPassword)) {
            System.out.println("Passwords match");

            try {
                // Load MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("JDBC Driver loaded");

                // Establish database connection
                String jdbcUrl = "jdbc:mysql://localhost:3306/banking_app";
                String dbUser = "root";
                String dbPassword = "Dhanush12345";
                try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                    System.out.println("Database connection established");

                    // Prepare SQL statement to update password
                    String sqlUpdatePassword = "UPDATE customers SET password=?, password_changed=true WHERE account_no=?";
                    try (PreparedStatement psUpdatePassword = connection.prepareStatement(sqlUpdatePassword)) {
                        psUpdatePassword.setString(1, newPassword);
                        psUpdatePassword.setString(2, accountNo);

                        System.out.println("Executing update statement");
                        // Execute update
                        int result = psUpdatePassword.executeUpdate();
                        System.out.println("Update result: " + result);

                        if (result > 0) {
                            System.out.println("Password updated successfully");
                            // Password updated successfully
                            request.setAttribute("message", "Password updated successfully. Please login with your new password.");
                            RequestDispatcher rd = request.getRequestDispatcher("Customer1.jsp");
                            rd.forward(request, response);
                        } else {
                            System.out.println("Error updating password, no rows affected");
                            // Error updating password
                            request.setAttribute("message", "Error updating password. Please try again.");
                            RequestDispatcher rd = request.getRequestDispatcher("Customer_New_Password.jsp");
                            rd.forward(request, response);
                        }
                    }
                } catch (SQLException e) {
                    // SQL Exception handling
                    System.out.println("SQL Exception: " + e.getMessage());
                    e.printStackTrace();
                    request.setAttribute("message", "Error updating password: " + e.getMessage());
                    RequestDispatcher rd = request.getRequestDispatcher("Customer_New_Password.jsp");
                    rd.forward(request, response);
                }
            } catch (ClassNotFoundException e) {
                // JDBC Driver not found handling
                System.out.println("ClassNotFoundException: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("message", "Error updating password: JDBC Driver not found");
                RequestDispatcher rd = request.getRequestDispatcher("Customer_New_Password.jsp");
                rd.forward(request, response);
            }
        } else {
            System.out.println("Passwords do not match");
            // Passwords do not match handling
            request.setAttribute("message", "Passwords do not match. Please try again.");
            RequestDispatcher rd = request.getRequestDispatcher("Customer_New_Password.jsp");
            rd.forward(request, response);
        }
    }
}
