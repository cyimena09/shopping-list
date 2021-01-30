<%@page pageEncoding="UTF-8" %>

<%@include file="../../templates/header.jsp"%>

<%@include file="../../templates/navigation.jsp"%>

<h2>Mettre à jour le nom du panier</h2>

<form action="update_panier?idPanier=${panier.idPanier}" method="post">

    <label for="nomMesure">Nom du produit</label>
    <input id="nomMesure" name="nomPanier" type="text" value="${panier.nom}">

    <button type="submit">Enregister</button>
</form>

<%@include file="../../templates/footer.jsp"%>