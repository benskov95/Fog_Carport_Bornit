<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</head>

<link href='https://fonts.googleapis.com/css?family=Bebas Neue' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Roboto Condensed' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>

<header>
    <div class="container responsive-header">
        <nav class="navbar navbar-expand-sm" style="background-color: #0C2069; padding: 0px">
            <a class="navbar-brand" style="padding: 0px" href="#">
                <img src="Images/logo-fog.png" alt="logo">
            </a>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="FrontController?target=redirect&destination=index"
                       style="font-family: Bebas Neue; font-size: 32px; color: white">Quickbyg</a>
                </li>
                <li class="nav-item">
                    <button type="button" class="btn" data-toggle="modal" data-target="#myModal"
                            style="font-family: Bebas Neue; font-size: 32px; color: white; background-color: #0C2069">Se ordre</button>
                    <div id="myModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <br>
                                </div>
                                <div class="modal-body">
                                    <h3 style="font-family: Roboto Condensed; font-size: 32px">LOG IND</h3>
                                    <br>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="target" value="login">
                                        <div class="form-group">
                                            <label for="phoneNumber">Telefon nr.</label>
                                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                                            <label for="orderId">Ordre nr.</label>
                                            <input type="text" class="form-control" id="orderId" name="orderId">
                                        </div>
                                        <button type="submit" class="btn btn-primary">Log ind</button>
                                    </form>
                                    <li class="nav-item">
                                          <button type="button" class="btn btn-sm sticky-top" data-toggle="modal" data-target="#login"
                                                                 style="font-family: Bebas Neue; font-size: 20px; color: white; background-color: #0C2069; position: absolute; right: 0">Forhandler Login</button>
                                                         <div id="login" class="modal fade" role="dialog">
                                                             <div class="modal-dialog">
                                                                 <div class="modal-content">
                                                                     <div class="modal-header">
                                                                         <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                         <br>
                                                                     </div>
                                                                     <div class="modal-body">
                                                                         <h3 style="font-family: Roboto Condensed; font-size: 32px">LOG IND</h3>
                                                                         <br>
                                                                         <form action="FrontController" method="post">
                                                                             <input type="hidden" name="target" value="employeeLogin">
                                                                             <div class="form-group">
                                                                                 <label for="employeeId">Employee ID</label>
                                                                                 <input type="text" class="form-control" id="employeeId" name="employeeId">
                                                                                 <label for="password">Password</label>
                                                                                 <input type="password" class="form-control" id="password" name="password">
                                                                             </div>
                                                                             <button type="submit" class="btn btn-primary">Log ind</button>
                                                                         </form>
                                <div class="modal-footer">
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
</header>

<body>

<div class="container">

    <div class="jumbotron" style="background-color: white">

