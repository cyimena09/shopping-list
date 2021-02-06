<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter une nouveau panier</h1>
        <form action="add_panier" method="post">
            <div class="form-group">
                <label for="panier">Nom du panier</label>
                <input type="text" class="form-control" id="panier" name="nomPanier" placeholder="ex: liste du samedi">
            </div>
            <button type="submit" class="btn btn-primary mt-2"><i class="fas fa-save"></i>Enregistrer</button>
        </form>
    </div>
</div>