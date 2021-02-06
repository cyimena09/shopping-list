<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter un nouveau magasin</h1>
        <form action="add_magasin" method="post">
            <div class="form-group">
                <label for="magasin">Nom du magasin</label>
                <input type="text" class="form-control" id="magasin" name="nomMagasin" placeholder="ex: Carrefour">
            </div>
            <button type="submit" class="btn btn-primary mt-2"><i class="fas fa-save"></i>Enregistrer</button>
        </form>
    </div>
</div>
