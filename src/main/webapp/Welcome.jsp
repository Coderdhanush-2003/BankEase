<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Baskervville+SC&display=swap" rel="stylesheet">
<style>
    #baskervville-sc-regular {
         font-family: "Baskervville SC", serif;
         font-weight: 400;
         font-style: normal;
         font-size: 4.0rem;
         text-align: left;
         padding-left: 20px;
    }
    .nav-item {
        font-weight: bold;
        font-size: 1.0rem;
        color: #ffffff;
        background-color: #405D72;
        padding: 6px 25px;
        border-radius: 30px;
        text-decoration: none;
        margin-right: 20px;
    }
    .nav-item:hover {
        background-color: #ffffff;
        color: #050505 !important;
    }
    .nav-item-2 {
        font-weight: bold;
        font-size: 1.0rem;
        color: #000000 !important;
        background-color: #ffffff;
        padding: 6px 25px;
        border-radius: 30px;
        text-decoration: none;
        margin-right: 20px;
    }
    .nav-item-2:hover {
        background-color: #405D72;
        color: #ffffff !important;
    }
</style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" id="baskervville-sc-regular" href="#">BankEase</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
            <ul class="navbar-nav px-4">
                <li class="nav-item-2">
                    <a class="nav-link" href="#">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">New Registration</a>
                </li>
                <li class="nav-item-2">
                    <a class="nav-link" href="#">Modify</a>
                </li>
                <li class="nav-item-2">
                    <a class="nav-link" href="#">Delete</a>
                </li>
                <li class="nav-item-2">
                    <a class="nav-link" href="#">Admin Profile</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Registration Form Start -->
    <div class="container" style="padding-bottom: 25px;">
        <div class="card">
            <div class="card-header">
                <h2 class="text-center">New Account Registration Form</h2>
            </div>
            <div class="card-body">
                <form action="RegisterServlet" method="post">
                    <div class="mb-3 row">
                        <label for="fullName" class="col-sm-4 col-form-label text-end">Full Name</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="fullName" name="fullName" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="address" class="col-sm-4 col-form-label text-end">Address</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="address" name="address" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="mobileNo" class="col-sm-4 col-form-label text-end">Mobile No</label>
                        <div class="col-sm-8">
                            <input type="tel" class="form-control" id="mobileNo" name="mobileNo" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="email" class="col-sm-4 col-form-label text-end">Email Address</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="accountType" class="col-sm-4 col-form-label text-end">Type of Account</label>
                        <div class="col-sm-8">
                            <select class="form-select" id="accountType" name="accountType" required>
                                <option value="" selected disabled>Select Account Type</option>
                                <option value="Saving">Saving Account</option>
                                <option value="Current">Current Account</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="initialBalance" class="col-sm-4 col-form-label text-end">Initial Balance (min 1000)</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="initialBalance" name="initialBalance" min="1000" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="dob" class="col-sm-4 col-form-label text-end">Date of Birth</label>
                        <div class="col-sm-8">
                            <input type="date" class="form-control" id="dob" name="dob" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="idProof" class="col-sm-4 col-form-label text-end">ID Proof</label>
                        <div class="col-sm-8">
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
</body>
</html>
