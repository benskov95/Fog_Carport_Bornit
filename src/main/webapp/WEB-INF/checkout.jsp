<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

    <title>Checkout</title>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12 center-block" style="text-align: center" >
                <h1>
                    Køb fuldført!
                </h1>
                <br>
                <br>
                <h2>
                    Vores lagermedarbejdere går nu i gang
                    <br>
                    med at pakke materialerne til din
                    <br>
                    carport.
                </h2>
                <br>
                <br>
                <h3>
                    Forventet leveringstid: 12-14 dage.
                    <br>
                    <br>
                    <a href="FrontController?target=redirect&destination=myorders" class="btn btn-primary" role="button" aria-pressed="true">Tilbage til ordreside</a>
                </h3>
            </div>
        </div>
    </div>
</section>

<%@include file="../Includes/footer.inc"%>