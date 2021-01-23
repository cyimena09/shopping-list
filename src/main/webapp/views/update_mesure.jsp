<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>

<h2>Mettre à jour la mesure</h2>

<form action="update_mesure?idMesure=${mesure.idMesure}" method="post">

    <label for="nomMesure">Nom du produit</label>
    <input id="nomMesure" name="nomMesure" type="text" value="${mesure.nom}">

    <button type="submit">Enregister</button>
</form>

<%@include file="../templates/footer.jsp"%>