<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

<title>Stykliste</title>

<h1 style="font-family: Roboto">Stykliste for ordre nr. 123</h1>
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
    <tr>
        <td>32</td>
        <td>25x200 mm. trykimp. bræt</td>
        <td>360 cm</td>
        <td>4</td>
        <td>stk</td>
        <td>Understernbrædder til for & bag ende</td>
    </tr>
    <tr>
        <td>65</td>
        <td>38x73 mm. lægte ubh.</td>
        <td>420 cm</td>
        <td>1</td>
        <td>stk</td>
        <td>Til z på bagside af dør</td>

    </tr>
    <tr>
        <td>38</td>
        <td>45x195 mm. spærtræ ubh.</td>
        <td>600 cm</td>
        <td>15</td>
        <td>stk</td>
        <td>Spær, monteres på rem</td>
    </tr>
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
    <tr>
        <td>91</td>
        <td>Plastmo bundskruer 200 stk.</td>
        <td></td>
        <td>3</td>
        <td>pakke</td>
        <td>Skruer til tagplader</td>
    </tr>
    <tr>
        <td>92</td>
        <td>Hulbånd 1x20 mm. 10 mtr.</td>
        <td></td>
        <td>2</td>
        <td>rulle</td>
        <td>Til vindkryds på spær</td>
    </tr>
    </tbody>
</table>

<%@include file="../Includes/footer.inc"%>

