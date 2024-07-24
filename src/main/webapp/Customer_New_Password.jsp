<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Set New Password</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>

    <!-- Soft UI Dashboard CSS -->
    <link id="pagestyle" href="assets/css/soft-ui-dashboard.css?v=1.0.7" rel="stylesheet" />
    
    <style>
        /* Additional styling for error message */
        .error-message {
            color: red;
            display: none; /* Initially hide error message */
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h2>Set Your New Password</h2>
    <form action="userNewPasswordServlet" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="newPassword">New Password</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            <p id="passwordMatchError" class="error-message">Passwords do not match. Please try again.</p>
        </div>
        <input type="hidden" name="accountNo" value="${requestScope.accountNo}">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- JavaScript for password matching validation -->
<script>
    function validateForm() {
        var newPassword = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (newPassword !== confirmPassword) {
            document.getElementById("passwordMatchError").style.display = "block";
            return false; // Prevent form submission
        } else {
            document.getElementById("passwordMatchError").style.display = "none";
            return true; // Allow form submission
        }
    }
</script>

<!-- Soft UI Dashboard scripts -->
<script src="assets/js/core/popper.min.js"></script>
<script src="assets/js/core/bootstrap.min.js"></script>
<script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
<script src="assets/js/plugins/chartjs.min.js"></script>
<script src="assets/js/soft-ui-dashboard.min.js?v=1.0.7"></script>

</body>
</html>
