<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

<title>Mine Ordrer</title>

<h1 class="display-4" style="font-family: Roboto,sans-serif">Hej Igor</h1>
<br>
<p class="lead" style="font-family: Roboto,sans-serif">Her kan du se status mm. på din ordre.</p>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Ordre nr.</th>
        <th scope="col">Carport bredde</th>
        <th scope="col">Carport længde</th>
        <th scope="col">Skur bredde</th>
        <th scope="col">Skur længde</th>
        <th scope="col">Dato</th>
        <th scope="col">Pris</th>
        <th scope="col">Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>500 cm</td>
        <td>600 cm</td>
        <td>200 cm</td>
        <td>150 cm</td>
        <td>24/4/2020</td>
        <td>$1.000.000</td>
        <td>Godkendt</td>
        <td><a href="FrontController?target=redirect&destination=checkout" class="btn btn-primary btn-sm" role="button" aria-pressed="true">Bestil</a>
            <a href="FrontController?target=redirect&destination=myorders" class="btn btn-danger btn-sm" role="button" aria-pressed="true">Afslå</a></td>
    </tr>
    </tbody>
</table>

    <p>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Carport detaljer
        </a>
        <a href="FrontController?target=redirect&destination=index" class="btn btn-danger" role="button" aria-pressed="true">Log ud</a>
    </p>
    <div class="collapse" id="collapseExample">
        <div class="card card-body" style="width: 8rem; text-align: center">
            <a href="FrontController?target=redirect&destination=bompage">Stykliste</a>
            <a href="FrontController?target=redirect&destination=carportplan">Tegning</a>
        </div>
    </div>






<%@include file="../Includes/footer.inc"%>