<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdrawal Successful</title>
    <!-- Include your CSS stylesheets or Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../assets/img/favicon.png">
 
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
 
  <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
  
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link href="assets/css/nucleo-svg.css" rel="stylesheet" />

  <link id="pagestyle" href="assets/css/soft-ui-dashboard.css?v=1.0.7" rel="stylesheet" />
          
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card text-center">
                <div class="card-body ">
                   <h2 class="card-title"> Withdrawal Successful</h2>
                   <p>Your withdrawal of <%= request.getParameter("amount") %> was successful.</p>
                    <p>Current balance : <%= request.getParameter("balance") %>.</p>
                    <a href="Customer_Dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
                </div>
            </div>
        </div>
    </div>
</div>
    
    <!-- Include your JavaScript scripts or Bootstrap JS -->
    <script src="path/to/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script>
        // Add custom JavaScript if needed
    </script>
</body>
</html>
