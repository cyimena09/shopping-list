<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Mettre à jour le magasin</h2>

        <form action="update_magasin?idMagasin=${magasin.idMagasin}" method="post">

            <div class="form-group">
                <label for="nomMagasin">Nom du magasin</label>
                <input id="nomMagasin" class="form-control" name="nomMagasin" type="text" value="${magasin.nom}">
            </div>

            <button class="btn btn-primary mt-2" type="submit">Enregister</button>

        </form>

    </div>
</div>

<%@include file="../../templates/footer.jsp"%>
