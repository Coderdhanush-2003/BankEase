package adminDashboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminDashboard")
public class adminDashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AdminDashboardServlet executing...");
        
        String adminUsername = (String) request.getSession().getAttribute("adminUsername");
        System.out.println("Admin username from session: " + adminUsername);

        if (adminUsername != null) {
            try {
                // Establish database connection
                System.out.println("Connecting to database...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root",
                        "Dhanush12345");

                // Query to get admin's full name
                System.out.println("Fetching admin's full name...");
                PreparedStatement psAdmin = connection.prepareStatement("SELECT full_name FROM admins WHERE username=?");
                psAdmin.setString(1, adminUsername);
                ResultSet rsAdmin = psAdmin.executeQuery();

                if (rsAdmin.next()) {
                    String adminName = rsAdmin.getString("full_name");
                    request.setAttribute("adminName", adminName);
                    System.out.println("Admin's full name: " + adminName);
                }

                // Query to get number of accounts
                System.out.println("Fetching number of accounts...");
                PreparedStatement psAccountCount = connection.prepareStatement("SELECT COUNT(account_no) AS account_count FROM accounts");
                ResultSet rsAccountCount = psAccountCount.executeQuery();

                if (rsAccountCount.next()) {
                    int accountCount = rsAccountCount.getInt("account_count");
                    request.setAttribute("accountCount", accountCount);
                    System.out.println("Number of accounts: " + accountCount);
                }

                // Query to get today's transactions count
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = today.format(formatter);
                System.out.println("Fetching today's transactions count...");

                PreparedStatement psTodayTransactions = connection.prepareStatement(
                        "SELECT COUNT(*) AS today_transaction_count FROM transactions WHERE DATE(transaction_date) = ?");
                psTodayTransactions.setString(1, formattedDate);
                ResultSet rsTodayTransactions = psTodayTransactions.executeQuery();

                if (rsTodayTransactions.next()) {
                    int todayTransactionCount = rsTodayTransactions.getInt("today_transaction_count");
                    request.setAttribute("todayTransactionCount", todayTransactionCount);
                    System.out.println("Today's transactions count: " + todayTransactionCount);
                }

                // Query to get total withdrawals
                System.out.println("Fetching total withdrawals...");
                PreparedStatement psTotalWithdrawals = connection
                        .prepareStatement("SELECT SUM(amount) AS total_withdrawals FROM transactions WHERE transaction_type = 'Withdraw'");
                ResultSet rsTotalWithdrawals = psTotalWithdrawals.executeQuery();

                if (rsTotalWithdrawals.next()) {
                    double totalWithdrawals = rsTotalWithdrawals.getDouble("total_withdrawals");
                    request.setAttribute("totalWithdrawals", totalWithdrawals);
                    System.out.println("Total withdrawals: " + totalWithdrawals);
                }

                // Query to get total deposits
                System.out.println("Fetching total deposits...");
                PreparedStatement psTotalDeposits = connection
                        .prepareStatement("SELECT SUM(amount) AS total_deposits FROM transactions WHERE transaction_type = 'Deposit'");
                ResultSet rsTotalDeposits = psTotalDeposits.executeQuery();

                if (rsTotalDeposits.next()) {
                    double totalDeposits = rsTotalDeposits.getDouble("total_deposits");
                    request.setAttribute("totalDeposits", totalDeposits);
                    System.out.println("Total deposits: " + totalDeposits);
                }

                // Close connection
                System.out.println("Closing database connection...");
                connection.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Admin username not found in session.");
        }

        // Forward request to adminDashboard.jsp
        RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.jsp");
        rd.forward(request, response);
    }
}
