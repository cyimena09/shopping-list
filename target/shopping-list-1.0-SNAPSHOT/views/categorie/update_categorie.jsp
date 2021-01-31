<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Mettre à jour la catégorie</h2>

        <form action="update_categorie?idCategorie=${categorie.idCategorie}" method="post">

            <label for="nomMesure">Nom du produit</label>
            <input id="nomMesure" name="nomMesure" type="text" value="${categorie.nom}">

            <button type="submit">Enregister</button>
        </form>
    </div>
</div>

<%@include file="../../templates/footer.jsp"%>
