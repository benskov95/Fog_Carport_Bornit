<%@ page import="CarportUtil.Initializer" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../Includes/header.inc" %>




<title>Fladt tag</title>
<p class="lead" style="color: red">${requestScope.error}</p>

    <h3 style="font-family: Roboto Condensed; text-transform: uppercase"><img style="float: right; margin-left: 10px;"src="Images/fladttag.gif"
             height="87" width="165" class="img-responsive">Quick-Byg tilbud - carport med fladt tag</h3>
    <div style="font-family: Roboto">
        <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på
            en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.</p>
        <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt.<br>Ved bestilling medfølger
            standardbyggevejledning.</p>
        <p>*&nbsp;Hvis de viste mål ikke opfylder dine ønsker, kan vi kontaktes på tlf.: 45 47 10 01</p>
        <p><strong>Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</strong><br>Felter markeret * SKAL
            udfyldes!</p>

        <form action="FrontController" method="get">
            <input type="hidden" name="target" value="flatorder">
            <div class="form-group">

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                Ønsket carport mål:
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Carport bredde">
                    <strong>Carport bredde</strong>
                </label>
                <select class="form-control" name="carportwidth" id="carportwidth">
                    <option selected="selected" value="">Vælg bredde</option>
                    <c:forEach var="size" items="${applicationScope.CWList}">
                        <option value="${size}">${size} cm</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Carport længde">
                    <strong>Carport længde</strong>
                </label>
                <select class="form-control" name="carportlength" id="carportlength">
                    <option selected="selected" value="">Vælg længde</option>
                    <c:forEach var="size" items="${applicationScope.CLList}">
                        <option value="${size}">${size} cm</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <fieldset disabled>
            <div class="form-group">
                <div class="col-md-12 col-xs-12">
                    <label title="Tag">
                        <strong>Tag</strong>
                    </label>
                    <select class="form-control" name="tag" id="tag">
                        <option selected="selected" value="Plasttrapezplader">Plasttrapezplader</option>
                        <option selected="selected" value="Plasttrapezplader">Plasttrapezplader</option>
                    </select>
                </div>
            </div>
        </fieldset>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <b>
                    <strong>Redskabsrum:</strong>
                </b>
                <br>
                NB! Der skal beregnes 30 cm tagudhæng på hver side af redskabsrummet*
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Redskabsrum bredde">
                    <strong>Redskabsrum bredde</strong>
                </label>
                <select class="form-control" name="shedwidth"
                        id="shedwidth">
                    <option selected="selected" value="0">Ønsker ikke redskabsrum</option>
                    <c:forEach var="size" items="${applicationScope.SWList}">
                        <option value="${size}">${size} cm</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Redskabsrum længde">
                    <strong>Redskabsrum længde</strong>
                </label>
                <select class="form-control" name="shedlength"
                        id="shedlength">
                    <option selected="selected" value="0">Ønsker ikke redskabsrum</option>
                    <c:forEach var="size" items="${applicationScope.SLList}">
                        <option value="${size}">${size} cm</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <hr>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Navn">
                    <strong>Navn</strong>
                </label>
                <input class="form-control" id="name" name="name" size="20" type="text" value="">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Adresse">
                    <strong>Adresse</strong>
                </label>
                <input class="form-control" id="address" name="address" size="20" type="text" value="">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Postnummer og by">
                    <strong>Postnummer og by</strong>
                </label>
                <input class="form-control" id="postalcode" name="postalcode" size="20" type="text" value="">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="Telefon">
                    <strong>Telefon</strong>
                </label>
                <input class="form-control" id="telephone" name="telephone" size="20" type="text" value="">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12 col-xs-12">
                <label title="E-mail adresse">
                    <strong>E-mail adresse</strong>
                </label>
                <input class="form-control" id="email" name="email" size="20" type="text" value="">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-6 col-xs-12">
                <div class="col-md-6 col-xs-12">
                    <input type="submit" value="Bestil tilbud">
                </div>
            </div>
        </div>

        <p>
            *&nbsp;Hvis du f.eks. har valgt en carport med målene 240x360 cm kan redskabsrummet maksimalt måle&nbsp;<b>180x300
            cm</b>.</p>
    </div>
</form>
    </div>

<%@include file="../Includes/footer.inc" %>

