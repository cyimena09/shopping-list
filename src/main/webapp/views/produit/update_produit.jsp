<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Mettre à jour le produit</h2>


        <form action="update_produit?idProduit=${produit.idProduit}" method="post">

            <div class="form-group">
                <label for="nomProduit">Nom du produit</label>
                <input id="nomProduit" class="form-control" name="nomProduit" type="text" value="${produit.nom}">
            </div>

            <div class="form-group">
                <label for="idMesure">Sélectionnez une Catégorie</label>
                <select class="form-control" name="idCategorie" id="idCategorie">
                    <option value="${produit.categorie.idCategorie}" selected>${produit.categorie.nom}</option>
                    <c:forEach items="${categories}" var="categorie">
                        <c:if test="${produit.categorie.idCategorie != categorie.idCategorie}">
                            <option value="${categorie.idCategorie}">${categorie.nom}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="idMesure">Sélectionnez une mesure</label>
                <select class="form-control" name="idMesure" id="idMesure">
                    <option value="${produit.mesure.idMesure}" selected>${produit.mesure.nom}</option>
                    <c:forEach items="${mesures}" var="mesure">
                        <c:if test="${produit.mesure.idMesure != mesure.idMesure}">
                            <option value="${mesure.idMesure}">${mesure.nom}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <button class="btn btn-primary mt-2" type="submit">Enregister</button>
        </form>
    </div>
</div>
<%@include file="../../templates/footer.jsp"%>
