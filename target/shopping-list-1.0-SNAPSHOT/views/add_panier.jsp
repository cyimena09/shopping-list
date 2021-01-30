<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h1 class="page-title">Ajouter une nouveau panier</h1>

        <form>
            <div class="form-group">
                <label for="mesure">Nom du panier</label>
                <input type="text" class="form-control" id="mesure" placeholder="ex: Kg">
            </div>

            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>

    </div>

</div>

<%@include file="../templates/footer.jsp"%>