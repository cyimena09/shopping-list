<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter une nouvelle catégorie</h1>

        <form>
            <div class="form-group">
                <label for="magasin">Nom du magasin</label>
                <input type="text" class="form-control" id="magasin" placeholder="ex: Carrefour">
            </div>

            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>

        <h2>Liste des catégories enregistré</h2>

    </div>

</div>

<%@include file="../../templates/footer.jsp"%>