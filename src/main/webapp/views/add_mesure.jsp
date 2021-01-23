<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>

<h2>Ajouter une mesure</h2>
<form action="add_mesure" method="post">

    <label for="nomMesure">Nom de la mesure</label>
    <input id="nomMesure" name="nomMesure" type="text">

    <button type="submit">Enregister</button>
</form>

<%@include file="../templates/footer.jsp"%>