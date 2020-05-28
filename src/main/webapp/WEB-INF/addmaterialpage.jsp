<%@ page import="CarportUtil.Initializer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc" %>

    <title>Tilføj nyt materiale</title>

<%
    if (request.getServletContext().getAttribute("sizeList") == null) {
        request.getServletContext().setAttribute("sizeList", Initializer.initSizeList(true));
    }
    if (request.getServletContext().getAttribute("otherSizesList") == null) {
        request.getServletContext().setAttribute("otherSizesList", Initializer.initSizeList(false));
    }
    if (request.getServletContext().getAttribute("unitList") == null) {
        request.getServletContext().setAttribute("unitList", Initializer.initUnitList());
    }
%>

${requestScope.error}
<p style="color: red; font-size: 20px">${requestScope.addMaterialError}</p>

<h3 class="h3" style="color: green">
    ${requestScope.successfullyAdded}
</h3>

<h5 class="h5" style="color: green">
    ${requestScope.displayLengths}
</h5>

<form action="FrontController" method="post">
    <input type="hidden" name="target" value="addMaterialToDatabase">
    <div class="form-group">

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Materialenavn">
                    <strong>Materialenavn</strong>
                </label>
                <input class="form-control" name="newMaterialName" size="20" >
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Enhed">
                    <strong>Enhed</strong>
                </label>
                <select class="form-control" name="newMaterialUnit" id="newMaterialUnit">
                    <option selected="selected" value="5">Vælg enhed</option>
                    <c:forEach var="unit" items="${applicationScope.unitList}">
                        <option value="${unit.unitId}">${unit.unitName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Pris pr. enhed">
                    <strong>Pris pr. enhed</strong>
                </label>
                <input class="form-control" name="newMaterialPrice" size="20" >
            </div>
        </div>

        <p class="lead">Hvis materialet er træ, tagplader eller lignende...</p>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Længde">
                    <strong>Vælg alle længder materialet fås i</strong>
                    <br>
                   Hold musens venstre knap nede og rul ned, for at vælge hele vejen ned, <br>
                    eller hold CTRL-knappen nede og venstreklik på enkelte længder for at<br>vælge specifikke længder.
                </label>
                <select class="form-control" name="newMaterialSizes" multiple="multiple" id="newMaterialSizes">
                    <option selected="selected" value="0">Ingen længder</option>
                    <c:forEach var="size" items="${applicationScope.sizeList}">
                        <option value="${size.sizeId}:${size.size}">${size.size} cm</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <p class="lead">Hvis materialet er beslag, skruer eller lignende...</p>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Antal pr. pakke">
                    <strong>Vælg alle antal materialet fås i </strong>
                </label>
                <select class="form-control" name="newMaterialAmounts" multiple="multiple" id="newMaterialAmounts">
                    <option selected="selected" value="0">Ingen</option>
                    <c:forEach var="size" items="${applicationScope.otherSizesList}">
                        <option value="${size.sizeId}:${size.size}">${size.size} stk</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <input class="btn btn-primary" type="submit" value="Tilføj til database">
<a href="FrontController?target=redirect&destination=adminpage" class="btn btn-primary" role="button" aria-pressed="true">Tilbage</a>

    </div>
</form>

<%@include file="../Includes/footer.inc"%>