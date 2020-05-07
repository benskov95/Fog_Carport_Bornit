<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>


    <title>Carport Plan</title>



<h1 style="font-family: Roboto,sans-serif">Carport tegning for ordre nr. 123</h1>
<p class="lead">Her ses din carports dimensioner fra fugleperspektiv.</p>

${sessionScope.outedrawing}
${sessionScope.svgdrawing}

<a href="FrontController?target=redirect&destination=myorder" class="btn btn-primary" role="button" aria-pressed="true">Tilbage</a>

<%@include file="../Includes/footer.inc"%>
