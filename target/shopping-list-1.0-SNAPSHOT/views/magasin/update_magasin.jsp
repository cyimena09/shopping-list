<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Mettre à jour le magasin</h2>

        <form action="update_magasin?idMagasin=${magasin.idMagasin}" method="post">

            <label for="nomMesure">Nom du magasin</label>
            <input id="nomMesure" name="nomMesure" type="text" value="${magasin.nom}">

            <button type="submit">Enregister</button>
        </form>
    </div>
</div>

<%@include file="../../templates/footer.jsp"%>