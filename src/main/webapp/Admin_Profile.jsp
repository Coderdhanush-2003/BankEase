<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="sessionCheck.jsp" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%
    String Admin_ID = (String) session.getAttribute("adminId");
    String Username = (String) session.getAttribute("adminUsername");
    String Full_Name = (String) session.getAttribute("adminName");
    String Email = (String) session.getAttribute("adminEmail");
    String Created_At = (String) session.getAttribute("adminCreatedAt");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="assets/img/favicon.png">
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
<body style="background-color: #E7F0DC;">
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



   <div class="container h-100 d-flex justify-content-center align-items-center" style="padding-top: 115px;">
      <div class="card card-custom w-75">
         <div class="card-header text-dark text-center">
            <h2>Admin Profile</h2>
         </div>
         <div class="card-body">
            <div class="row mb-3">
               <div class="col-md-4 text-right">
                  <strong>Admin ID:</strong>
               </div>
               <div class="col-md-8">
                  <p><%= Admin_ID %></p>
               </div>
            </div>
            <div class="row mb-3">
               <div class="col-md-4 text-right">
                  <strong>Username:</strong>
               </div>
               <div class="col-md-8">
                  <p><%= Username %></p>
               </div>
            </div>
            <div class="row mb-3">
               <div class="col-md-4 text-right">
                  <strong>Full Name:</strong>
               </div>
               <div class="col-md-8">
                  <p><%= Full_Name %></p>
               </div>
            </div>
            <div class="row mb-3">
               <div class="col-md-4 text-right">
                  <strong>Email:</strong>
               </div>
               <div class="col-md-8">
                  <p><%= Email %></p>
               </div>
            </div>
            <div class="row mb-3">
               <div class="col-md-4 text-right">
                  <strong>Created At:</strong>
               </div>
               <div class="col-md-8">
                  <p><%= Created_At %></p>
               </div>
            </div>
            <div class="row">
               <div class="col text-center">
                  <a href="adminDashboard" class="btn btn-primary">Return to Dashboard</a>
               </div>
            </div>
         </div>
      </div>
   </div>
   

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
   <script src="assets/js/soft-ui-dashboard.min.js?v=1.0.7"></script>
</body>
</html>
