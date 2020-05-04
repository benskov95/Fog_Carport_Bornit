<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tha-Y
  Date: 04-05-2020
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc" %>
<title>Title</title>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Ordre nr.</th>
        <th scope="col">Carport type</th>
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


    <c:forEach var="orders" items="${sessionScope.orderlist}">
        <tr>
            <td>${orders.order_id}</td>
            <td>${orders.carport_id}</td>
            <td>${orders.carport_width}</td>
            <td>${orders.carport_length}</td>
            <td>${orders.shed_width}</td>
            <td>${orders.shed_length}</td>
            <td>${orders.date}</td>
            <td>Mangler i tabel</td>
            <td>${orders.phone}</td>

            <td><a href="FrontController?target=redirect&destination=adminpage" class="btn btn-primary btn-sm"
                   role="button" aria-pressed="true">Godkend</a>
                <a href="FrontController?target=redirect&destination=adminpage" class="btn btn-danger btn-sm"
                   role="button" aria-pressed="true">Afslå</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<p>
    <a href="FrontController?target=redirect&destination=index" class="btn btn-danger" role="button"
       aria-pressed="true">Log ud</a>
</p>


<%@include file="../Includes/footer.inc" %>





