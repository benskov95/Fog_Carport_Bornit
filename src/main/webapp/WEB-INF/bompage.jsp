<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

<title>Stykliste</title>

<h1 style="font-family: Roboto">Stykliste for ordre nr. ${sessionScope.order.order_id}</h1>
<p class="lead">Her kan du se styklisten til din carport.</p>

<a href="FrontController?target=redirect&destination=myorder" class="btn btn-primary" role="button" aria-pressed="true">Tilbage</a>
<br>
<br>


<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Materiale ID</th>
        <th scope="col">Materiale</th>
        <th scope="col">Længde</th>
        <th scope="col">Antal</th>
        <th scope="col">Enhed</th>
        <th scope="col">Beskrivelse</th>
    </tr>
    </thead>

    <thead>
    <tr>
        <th scope="col">Træ & Tagplader</th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>

    <tbody>

    <c:forEach var="categoryOneMaterial" items="${sessionScope.woodAndRoofPlates}">
    <tr>
        <td>${categoryOneMaterial.materialId}</td>
        <td>${categoryOneMaterial.materialName}</td>
        <td>${categoryOneMaterial.size}</td>
        <td>${categoryOneMaterial.quantity}</td>
        <td>${categoryOneMaterial.unit}</td>
        <td>${categoryOneMaterial.carportPartDescription}</td>
    </tr>
    </c:forEach>

    </tbody>

    <thead>
    <tr>
        <th scope="col">Beslag & Skruer</th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>

    <tbody>

    <c:forEach var="categoryTwoMaterial" items="${sessionScope.bracketsAndScrews}">
    <tr>
        <td>${categoryTwoMaterial.materialId}</td>
        <td>${categoryTwoMaterial.materialName}</td>
        <td>${categoryTwoMaterial.size}</td>
        <td>${categoryTwoMaterial.quantity}</td>
        <td>${categoryTwoMaterial.unit}</td>
        <td>${categoryTwoMaterial.carportPartDescription}</td>
    </tr>
    </c:forEach>

    <tr>

    </tbody>

</table>

<%@include file="../Includes/footer.inc"%>

