<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter une nouvelle unité de mesure</h1>
        <form action="add_mesure" method="post">
            <div class="form-group">
                <label for="mesure">Nom de la mesure</label>
                <input type="text" class="form-control" id="mesure" name="nomMesure" placeholder="ex: Kg">
            </div>
            <button type="submit" class="btn btn-primary mt-2">Enregistrer</button>
        </form>
    </div>
</div>
