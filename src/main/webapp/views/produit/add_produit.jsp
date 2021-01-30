<%@page pageEncoding="UTF-8" %>

<%@include file="../../templates/header.jsp"%>

<%@include file="../../templates/navigation.jsp"%>


<form action="add_produit" method="post">

    <label for="nomProduit">Nom du produit</label>
    <input id="nomProduit" name="nomProduit" type="text">

    <label for="idMesure">Sélectionnez une mesure</label>
    <select class="form-control" name="idMesure" id="idMesure">
        <c:forEach items="${mesures}" var="mesure">
            <option value="${mesure.idMesure}">${mesure.nom}</option>
        </c:forEach>
    </select>

    <button type="submit">Enregister</button>
</form>

<%@include file="../../templates/footer.jsp"%>