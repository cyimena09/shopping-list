<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Mettre à jour la mesure</h2>

        <form action="update_mesure?idMesure=${mesure.idMesure}" method="post">

            <div class="form-group">
                <label for="nomMesure">Nom de la mesure</label>
                <input id="nomMesure" class="form-control" name="nomMesure" type="text" value="${mesure.nom}">
            </div>
            <button class="btn btn-primary mt-2" type="submit">Enregister</button>
            <a class="btn btn-primary mt-2" href="mesures">Retour</a>
        </form>
        <c:if test="${error != null}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</div>

<%@include file="../../templates/footer.jsp"%>
