<%@ page import="CarportUtil.Initializer" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="Includes/header.inc" %>

<%
    if (request.getServletContext().getAttribute("CWList") == null) {
        request.getServletContext().setAttribute("CWList", Initializer.initCWLIST());
    }
    if (request.getServletContext().getAttribute("CLList") == null) {
        request.getServletContext().setAttribute("CLList", Initializer.initCLList());
    }
    if (request.getServletContext().getAttribute("SWList") == null) {
        request.getServletContext().setAttribute("SWList", Initializer.initSWList());
    }
    if (request.getServletContext().getAttribute("SLList") == null) {
        request.getServletContext().setAttribute("SLList", Initializer.initSLList());
    }
%>

<title>Forside</title>

<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">

        <p class="lead" style="color: red">${requestScope.loginError}</p>
        <p class="lead">
        <p style="font-weight: bold; font-size: 20px">QUICKBYG TILBUD</p>
        <p>
            Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen
            og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke
            ønsker.
            <br>
            <br>
            Tilbud og skitsetegning fremsendes med post hurtigst muligt.
            Ved bestilling medfølger standardbyggevejledning.
            <br>
            <br>
            Rekvirér tilbud - start med at vælge type: </p>

        <div style="alignment: center">
            <a href="FrontController?target=redirect&destination=flatorder"><img src="Images/fladttag.gif" class="img-fluid"
                                                            style="float: left"></a>
            <a href="#"><img src="Images/tagmedrejsning.gif" class="img-fluid" style="float: right"></a>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>

<%@include file="Includes/footer.inc" %>

