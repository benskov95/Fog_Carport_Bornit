<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../Includes/header.inc" %>

<title>Checkout</title>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12
             center-block" style="text-align: center" >
                <h1>
                    Tak for din bestilling!
                </h1>
                <br>
                <br>
                <h2>
                    Dit ordrenr er: ${requestScope.orderId}.
                </h2>
                <br>
                <br>
                <h3>
                    Du kan følge din ordre, ved at klikke på "Se ordre".
                    <br>
                    <br>
                    <a href="FrontController?target=redirect&destination=index" class="btn btn-primary" role="button" aria-pressed="true">Tilbage til forsiden</a>
                </h3>
            </div>
        </div>
    </div>
</section>


<%@include file="../Includes/footer.inc" %>