<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

    <title>Declined Order</title>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12 center-block" style="text-align: center" >
                <h1>
                    Vi er kede af, at du ikke var tilfreds med vores tilbud.
                </h1>
                <br>
                <br>
                <h2>
                    Hvis du skulle få lyst til at
                    <br>
                    lave en ny bestilling, så kan
                    <br>
                    du bare bestille igen.
                    <br>
                    <br>
                    Fortsat god dag.
                </h2>
                <br>
                <br>
                <h3>
                    Ordre med ID: ${requestScope.deletedOrderId} er blevet slettet.
                    <br>
                    <br>
                    <a href="FrontController?target=redirect&destination=index" class="btn btn-primary" role="button" aria-pressed="true">Tilbage til forside</a>
                </h3>
            </div>
        </div>
    </div>
</section>

<%@include file="../Includes/footer.inc"%>