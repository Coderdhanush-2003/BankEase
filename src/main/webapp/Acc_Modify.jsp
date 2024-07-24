<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="assets/img/favicon.png">
  <title>
   Account Modify
  </title>
  <!-- Fonts and icons -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
  <!-- Nucleo Icons -->
  <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link id="pagestyle" href="assets/css/soft-ui-dashboard.css?v=1.0.7" rel="stylesheet" />
</head>

<script>
function showEditForm() {
    var inputs = document.querySelectorAll("#accountDetailsForm input, #accountDetailsForm select");
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].disabled = false;
    }
}

function closeEditForm() {
    document.getElementById("accountDetailsForm").style.display = "none";
    document.getElementById("editButtons").style.display = "none";
    document.getElementById("fetchForm").style.display = "block";
}

function displayDetails() {
    document.getElementById("accountDetailsForm").style.display = "block";
    document.getElementById("editButtons").style.display = "block";
    document.getElementById("fetchForm").style.display = "none";
}
</script>
</head>
<body  style = "background-color : #E7F0DC;">
<div class="container position-sticky z-index-sticky top-0">
    <div class="row">
      <div class="col-12">
    <!-- Navbar -->
        <nav class="navbar navbar-expand-lg blur blur-rounded top-0 z-index-3 shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
          <div class="container-fluid pe-0">
            <a class="navbar-brand font-weight-bolder ms-lg-0 ms-3 " href="../pages/dashboard.html">
              BankEase
            </a>
            <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse" data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon mt-2">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
              </span>
            </button>
            <div class="collapse navbar-collapse" id="navigation">
              <ul class="navbar-nav mx-auto ms-xl-auto me-xl-7">
                <li class="nav-item">
                  <a class="nav-link me-2" href="adminDashboard">
                   Dashboard
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link me-2" href="NewRegistration.jsp">
                    <i class="fas fa-light fa-address-card opacity-6 text-dark me-1"></i>
                    New Registration
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link me-2" href="Acc_Modify.jsp">
                    <i class="fas fa-light fa-pen opacity-6 text-dark me-1"></i>
                    Modify
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link me-2" href="Acc_Delete.jsp">
                    <i class="fas fa-trash opacity-6 text-dark me-1"></i>
                    Delete Account
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link me-2" href="Admin_Profile.jsp">
                    <i class="fas fa-user opacity-6 text-dark me-1"></i>
                    Profile
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link me-2" href="AdminLogout">
                    <i class="fas fa-sign-out-alt opacity-6 text-dark me-1"></i>
                    Logout
                  </a>
                </li>
              
                
              </ul>
            </div>
          </div>
        </nav>
        <!-- End Navbar -->
</div>
</div>
</div>
     
        <!-- End Navbar -->
    <div class="container mt-4" style= "Padding-top : 115px;">
        <h2>Modify Account Details</h2>
        <div id="fetchForm">
            <form action="ModifyAccountServlet" method="get">
                <div class="mb-3">
                    <label for="accountNo" class="form-label">Account Number:</label>
                    <input type="text" class="form-control" id="accountNo" name="accountNo">
                </div>
                <button type="submit" class="btn btn-primary">Fetch Details</button>
            </form>
        </div>

        <%-- Display success or error messages --%>
        <%
            String successMessage = (String) request.getAttribute("successMessage");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (successMessage != null) {
        %>
            <div class="alert alert-success mt-3" role="alert">
                <%= successMessage %>
            </div>
        <% } else if (errorMessage != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
                <%= errorMessage %>
            </div>
        <% } %>

        <%-- Display account details if they exist in the request --%>
        <%
            String accountNo = (String) request.getAttribute("accountNo");
            if (accountNo != null) {
                String fullName = (String) request.getAttribute("fullName");
                String address = (String) request.getAttribute("address");
                String mobileNo = (String) request.getAttribute("mobileNo");
                String email = (String) request.getAttribute("email");
                String accountType = (String) request.getAttribute("accountType");
                String createdat = (String) request.getAttribute("createdat");
                String dob = (String) request.getAttribute("dob");
                String idProof = (String) request.getAttribute("idProof");
        %>
            <script>displayDetails();</script>
            <div id="editButtons" class="mt-3">
                <button type="button" class="btn btn-warning" onclick="showEditForm()">Edit</button>
                <button type="button" class="btn btn-secondary" onclick="closeEditForm()">Close</button>
            </div>

            <form id="accountDetailsForm" action="ModifyAccountServlet" method="post">
                <!-- Hidden input for account number -->
                <input type="hidden" id="accountNo" name="accountNo" value="<%= accountNo %>">
                
                <!-- Input fields for modification -->
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name:</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="<%= fullName %>" disabled>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" value="<%= address %>" disabled>
                </div>
                <div class="mb-3">
                    <label for="mobileNo" class="form-label">Mobile Number:</label>
                    <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="<%= mobileNo %>" disabled>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= email %>" disabled>
                </div>
                <div class="mb-3">
                    <label for="accountType" class="form-label">Account Type:</label>
                    <select class="form-control" id="accountType" name="accountType" disabled>
                        <option value="Saving" <%= "Saving".equals(accountType) ? "selected" : "" %>>Saving</option>
                        <option value="Current" <%= "Current".equals(accountType) ? "selected" : "" %>>Current</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="createdat" class="form-label">Account Active From:</label>
                    <input type="text" class="form-control" id="createdat" name="createdat" value="<%= createdat %>" disabled>
                </div>
                <div class="mb-3">
                    <label for="dob" class="form-label">Date of Birth:</label>
                    <input type="text" class="form-control" id="dob" name="dob" value="<%= dob %>" disabled>
                </div>
                <div class="mb-3">
                    <label for="idProof" class="form-label">ID Proof:</label>
                    <input type="text" class="form-control" id="idProof" name="idProof" value="<%= idProof %>" disabled>
                </div>
                <!-- Other fields as needed -->

                <button type="submit" class="btn btn-primary">Update Details</button>
            </form>
        <% } %>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+yUAARPMBW2ugQ4eP6Rnt7O9RPHHDr/JboW9eR8Rx4zwW2ulVt0qaA7MrQ0K6H" crossorigin="anonymous"></script>
 <!--   Core JS Files   -->
  <script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap.min.js"></script>
  <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
  <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
  <script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
      var options = {
        damping: '0.5'
      }
      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
  </script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="../assets/js/soft-ui-dashboard.min.js?v=1.0.7"></script>
</body>

</html>
