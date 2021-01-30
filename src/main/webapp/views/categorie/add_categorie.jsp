<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter une nouvelle catégorie</h1>
        <form action="add_categorie" method="post">
            <div class="form-group">
                <label for="mesure">Nom de la mesure</label>
                <input type="text" class="form-control" id="mesure" name="nomCategorie" placeholder="ex: Produit laitier">
            </div>
            <button type="submit" class="btn btn-primary mt-2">Enregistrer</button>
        </form>
    </div>
</div>