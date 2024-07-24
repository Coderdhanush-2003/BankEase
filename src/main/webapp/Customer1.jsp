<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
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
<body>

  <div class="container h-100 d-flex justify-content-center align-items-center" style="padding-top: 115px;">
      <div class="card card-custom w-75">
         <div class="card-header text-dark text-center">
            <% String message = (String) request.getAttribute("message"); %>
             <% if (message != null) { %>
              <div class="alert alert-info">
                  <%= message %>
                  </div>
             <% } %>
             <div class="row">
               <div class="col text-center">
                  <a href="Customer.jsp" class="btn btn-primary">Return to Login Page</a>
               </div>
            </div>
         </div>

 <script src="assets/js/core/popper.min.js"></script>
   <script src="assets/js/core/bootstrap.min.js"></script>
   <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
   <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
     <script src="assets/js/soft-ui-dashboard.min.js?v=1.0.7"></script>
   <script>
   

 

</body>
</html>