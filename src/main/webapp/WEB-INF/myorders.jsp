<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

<title>Mine Ordrer</title>

<h1 class="display-4" style="font-family: Roboto">Hej Igor</h1>
<br>
<p class="lead" style="font-family: Roboto">Her kan du se status mm. på din ordre.</p>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Ordre nr.</th>
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
    <tr>
        <td>1</td>
        <td>500 cm</td>
        <td>600 cm</td>
        <td>200 cm</td>
        <td>150 cm</td>
        <td>24/4/2020</td>
        <td>$1.000.000</td>
        <td>Godkendt</td>
        <td><a href="#" class="btn btn-success btn-sm active" role="button" aria-pressed="true">Bestil</a></td>
    </tr>






<%@include file="../Includes/footer.inc"%>