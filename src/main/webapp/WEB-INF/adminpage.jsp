<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc" %>


<title>Administrator</title>

<p class="lead" style="color: red">${requestScope.error}</p>

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
        <th scope="col">Samlet materialepris</th>
        <th scope="col">Totalpris</th>
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
            <td>${orders.totalPrice} kr.</td>

            <td>
                <form onsubmit="return confirm('Er du sikker på prisen er korrekt?')" name="update" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="updateorder"><input class="form-control" name="totalprice"
                                                                                  size="20" type="text"
                                                                            value="${orders.totalPrice}">

<%--                    Der skal være et form closing tag her, men det kan der ikke være før--%>
<%--                    der bliver lavet om på godkend knappen også.--%>
            </td>

            <td>${orders.status}</td>


            <td>

                <button type="submit" class="btn btn-primary btn-sm" name="accept" value="${orders.order_id}">Godkend
                </button>

                <form onsubmit="return confirm('Er du sikker på du vil slette ordre nr:' + ${orders.order_id})" name="delete" action="FrontController" method="POST" id="delete">
                    <input type="hidden" name="target" value="deleteorder">
                    <button type="submit" class="btn btn-danger btn-sm" name="delete" value="${orders.order_id}">Slet
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

<%--<script>--%>
<%--function show_alert() {--%>
<%--if(!confirm("Do you really want to do this?")) {--%>
<%--return false;--%>
<%--}--%>
<%--this.form.submit();--%>
<%--}--%>
<%--</script>--%>

<%@include file="../Includes/footer.inc" %>





