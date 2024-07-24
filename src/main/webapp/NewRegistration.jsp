<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="assets/img/favicon.png">
  <title>
   Admin Login
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
    function validateForm() {
        const fullName = document.getElementById('fullName').value;
        const address = document.getElementById('address').value;
        const mobileNo = document.getElementById('mobileNo').value;
        const email = document.getElementById('email').value;
        const accountType = document.getElementById('accountType').value;
        const initialBalance = document.getElementById('initialBalance').value;
        const dob = document.getElementById('dob').value;
        const idProof = document.getElementById('idProof').value;

        if (!fullName.match(/^[a-zA-Z ]+$/)) {
            alert("Full Name can only contain letters and spaces.");
            return false;
        }

        if (!address) {
            alert("Address is required.");
            return false;
        }

        if (!mobileNo.match(/^[0-9]{10}$/)) {
            alert("Mobile No must be exactly 10 digits.");
            return false;
        }

        if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
            alert("Invalid email format.");
            return false;
        }

        if (accountType === "") {
            alert("Please select an Account Type.");
            return false;
        }

        if (initialBalance < 1000) {
            alert("Initial Balance must be at least 1000.");
            return false;
        }

        if (!dob) {
            alert("Date of Birth is required.");
            return false;
        } else {
            const dobDate = new Date(dob);
            const today = new Date();
            const age = today.getFullYear() - dobDate.getFullYear();
            const monthDifference = today.getMonth() - dobDate.getMonth();
            if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < dobDate.getDate())) {
                age--;
            }
            if (age < 18) {
                alert("You must be at least 18 years old.");
                return false;
            }
        }

        if (!idProof) {
            alert("ID Proof is required.");
            return false;
        }

        return true;
    }
</script>

<body style = "background-color : #E7F0DC;">
   <div class="container position-sticky z-index-sticky top-0">
    <div class="row">
      <div class="col-12">
    <!-- Navbar -->
        <nav class="navbar navbar-expand-lg blur blur-rounded top-0 z-index-3 shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
          <div class="container-fluid pe-0">
            <a class="navbar-brand font-weight-bolder ms-lg-0 ms-3 " href="NewRegistration.jsp">
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
    <!-- Registration Form Start -->
    <div class="container" style="padding-bottom:25px; padding-top:155px;">
        <div class="card">
            <div class="card-header">
                <h2 class="text-center">New Account Registration Form</h2>
            </div>
            <div class="card-body ">
                <form action="RegisterServlet" method="post" onsubmit="return validateForm()">
                    <div class="mb-3 row">
                        <label for="fullName" class="col-sm-4 col-form-label text-end">Full Name</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="fullName" name="fullName" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="address" class="col-sm-4 col-form-label text-end">Address</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="address" name="address" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="mobileNo" class="col-sm-4 col-form-label text-end">Mobile No</label>
                        <div class="col-sm-5">
                            <input type="tel" class="form-control" id="mobileNo" name="mobileNo" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="email" class="col-sm-4 col-form-label text-end">Email Address</label>
                        <div class="col-sm-5">
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="accountType" class="col-sm-4 col-form-label text-end">Type of Account</label>
                        <div class="col-sm-5">
                            <select class="form-select" id="accountType" name="accountType" required>
                                <option value="" selected disabled>Select Account Type</option>
                                <option value="Saving">Saving Account</option>
                                <option value="Current">Current Account</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="initialBalance" class="col-sm-4 col-form-label text-end">Initial Balance (min 1000)</label>
                        <div class="col-sm-5">
                            <input type="number" class="form-control" id="initialBalance" name="initialBalance" min="1000" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="dob" class="col-sm-4 col-form-label text-end">Date of Birth</label>
                        <div class="col-sm-5">
                            <input type="date" class="form-control" id="dob" name="dob" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="idProof" class="col-sm-4 col-form-label text-end">ID Proof</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="idProof" name="idProof" required>
                        </div>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Registration Form End -->

    <!-- Bootstrap Bundle with Popper.js and Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
