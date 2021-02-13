<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Mettre à jour la catégorie</h2>

        <form action="update_categorie?idCategorie=${categorie.idCategorie}" method="post">
            <div class="form-group">
                <label for="nomCategorie">Nom de la Catégorie</label>
                <input id="nomCategorie" name="nomCategorie" type="text" value="${categorie.nom}">
            </div>
            <button class="btn btn-primary mt-2" type="submit">Enregister</button>
            <a class="btn btn-primary mt-2" href="categories">Retour</a>
        </form>
    </div>
</div>

<%@include file="../../templates/footer.jsp"%>

