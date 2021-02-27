<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter une nouvelle catégorie</h1>
        <form action="create_categorie" method="post">
            <div class="form-group">
                <label for="categorie">Nom de la catégorie</label>
                <input type="text" class="form-control" id="categorie" name="nomCategorie" placeholder="ex: Produit laitier">
            </div>
            <button type="submit" class="btn btn-primary mt-2"><i class="fas fa-save"></i>Enregistrer</button>
        </form>

        <c:if test="${error != null}">
            <p class="error">${error}</p>
        </c:if>

    </div>
</div>
