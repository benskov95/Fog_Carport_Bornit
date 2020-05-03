<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>


    <title>Carport Plan</title>



<h1 style="font-family: Roboto,sans-serif">Carport tegning for ordre nr. 123</h1>
<p class="lead">Her ses din carports dimensioner fra fugleperspektiv.</p>
<a href="FrontController?target=redirect&destination=myorder" class="btn btn-primary" role="button" aria-pressed="true">Tilbage</a>

<svg xmlns="http://www.w3.org/2000/svg"
     xmlns:xlink="http://www.w3.org/1999/xlink"
     width="500" height="500"  viewBox="0 0 255 255" display="block" style="margin: auto">
<%--    <rect x="0" y="0" height="300" width="400"--%>
<%--          style="stroke:#000000; fill: white"></rect>--%>
    <rect x="65" y="70" height="90" width="120"
          style="stroke:#000000; fill: white"></rect>

    <line x1="80" y1="160" x2="80" y2="70" style="stroke: #000000; "></line>
    <line x1="95" y1="160" x2="95" y2="70" style="stroke: #000000; "></line>
    <line x1="110" y1="160" x2="110" y2="70" style="stroke: #000000; "></line>
    <line x1="125" y1="160" x2="125" y2="70" style="stroke: #000000; "></line>
    <line x1="140" y1="160" x2="140" y2="70" style="stroke: #000000; "></line>
    <line x1="155" y1="160" x2="155" y2="70" style="stroke: #000000; "></line>
    <line x1="170" y1="160" x2="170" y2="70" style="stroke: #000000; "></line>

    <line x1="65" y1="160" x2="140" y2="70" style="stroke: #000000; "></line>
    <line x1="140" y1="160" x2="65" y2="70" style="stroke: #000000; "></line>

</svg>

<%@include file="../Includes/footer.inc"%>
