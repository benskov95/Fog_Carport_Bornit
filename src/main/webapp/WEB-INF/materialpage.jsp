<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc" %>

    <title>Material Page</title>

${requestScope.error}

<h3 class="h3" style="color: green">
${requestScope.updated}
</h3>

<a href="FrontController?target=redirect&destination=adminpage" class="btn btn-primary" role="button" aria-pressed="true">Tilbage</a>
<br>
<br>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Materiale ID</th>
        <th scope="col">Navn</th>
        <th scope="col">Pris</th>
        <th scope="col">Enhed</th>
        <th scope="col"></th>

    </tr>
    </thead>
    <tbody>

    <c:forEach var="material" items="${sessionScope.allMaterials}">

        <tr>
        <td>${material.materialId}</td>

        <form onsubmit="return confirm('Er du sikker, at du vil ændre denne værdi?')" name="update" action="FrontController" method="POST">
            <input type="hidden" name="target" value="updatematerial">

        <td>
            <input class="form-control" name="materialName" size="20" type="text" value="${material.materialName}">
        </td>
        <td>
            <input class="form-control" name="materialPrice" size="20" type="text" value="${material.price}">
        </td>
        <td>
            ${material.unit}
        </td>

            <td>
            <button type="submit" class="btn btn-warning btn-sm" name="materialId" value="${material.materialId}">Opdatér
            </button>
            </td>
        </form>

        </tr>
    </c:forEach>

    </tbody>
</table>

<%@include file="../Includes/footer.inc"%>
