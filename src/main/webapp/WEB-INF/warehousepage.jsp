<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc" %>

<title>Lager</title>

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
        <th scope="col">Telefon</th>
        <th scope="col">Stykliste</th>
        <th scope="col">Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="orders" items="${sessionScope.orders}">
        <tr>
            <td>${orders.order_id}</td>
            <td>${orders.carport_id}</td>
            <td>${orders.carport_width}</td>
            <td>${orders.carport_length}</td>
            <td>${orders.shed_width}</td>
            <td>${orders.shed_length}</td>
            <td>${orders.date}</td>
            <td>${orders.totalPrice} kr.</td>
            <td>${orders.phone}</td>
            <td>
                <form action="FrontController" method="post"><input type="hidden" name="target" value="bompage">
                    <button type="submit" class="btn btn-primary btn-sm" name="billofmaterials" value="${orders.order_id}">
                        Stykliste
                    </button>
                </form>
            </td>
            <td>
                <form name="ship" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="shiporder">
                    <button type="submit" class="btn btn-primary btn-sm" name="shipped" value="${orders.order_id}">
                        Afsend
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>
    <a href="FrontController?target=logout" class="btn btn-danger" role="button"
       aria-pressed="true">Log ud</a>
</p>


<%@include file="../Includes/footer.inc" %>




