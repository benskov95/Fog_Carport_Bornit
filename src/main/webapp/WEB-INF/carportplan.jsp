<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>


    <title>Carport Plan</title>



<h1 style="font-family: Roboto,sans-serif">Carport tegning for ordre nr. ${sessionScope.order.order_id}</h1>
<p class="lead">Her ses din carports dimensioner ovenfra.</p>

<%--${sessionScope.svgOuterDrawing} skal lige få den til at virke. Er ikke sikker på det skal være en separat tegning.--%>
${sessionScope.svgdrawing}

<a href="FrontController?target=redirect&destination=myorder" class="btn btn-primary" role="button" aria-pressed="true">Tilbage</a>

<%@include file="../Includes/footer.inc"%>
