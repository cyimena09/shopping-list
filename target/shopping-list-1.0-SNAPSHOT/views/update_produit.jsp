<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>

<h2>Mettre à jour le produit</h2>

<form action="update_produit?idProduit=${produit.idProduit}" method="post">

    <label for="nomProduit">Nom du produit</label>
    <input id="nomProduit" name="nomProduit" type="text" value="${produit.nom}">

<%--    <label for="idMesure">Sélectionnez une Catégorie</label>--%>
<%--    <select class="form-control" name="idCategorie" id="idCategorie">--%>
<%--        <option value="${produit.idCategorie}" selected>${produit.nomCategorie}</option>--%>
<%--        <c:forEach items="${categories}" var="categorie">--%>
<%--            <c:if test="${produit.idCategorie != categorie.idCategorie}">--%>
<%--                <option value="${categorie.idCategorie}">${categorie.nom}</option>--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>


    <label for="idMesure">Sélectionnez une mesure</label>
    <select class="form-control" name="idMesure" id="idMesure">
        <option value="${produit.idMesure}" selected>${produit.nomMesure}</option>
        <c:forEach items="${mesures}" var="mesure">
            <c:if test="${produit.idMesure != mesure.idMesure}">
                <option value="${mesure.idMesure}">${mesure.nom}</option>
            </c:if>
        </c:forEach>
    </select>
    <button type="submit">Enregister</button>
</form>

<%@include file="../templates/footer.jsp"%>