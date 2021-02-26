<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Modifier le nom du panier</h2>
        <form action="update_panier?idPanier=${panier.idPanier}" method="post">
            <div class="form-group">
                <label for="nomPanier">Nom du panier</label>
                <input id="nomPanier" class="form-control" name="nomPanier" type="text" value="${panier.nom}">
            </div>

            <div class="form-group">
                <label for="idMagasin">Sélectionnez le magasin</label>
                <select class="form-control" name="idMagasin" id="idMagasin">
                    <option value="${panier.magasin.idMagasin}" selected>${panier.magasin.nom}</option>
                    <c:forEach items="${magasins}" var="magasin">
                        <c:if test="${panier.magasin.idMagasin != magasin.idMagasin}">
                            <option value="${magasin.idMagasin}">${magasin.nom}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <button class="btn btn-primary mt-2" type="submit">Enregister</button>
            <a class="btn btn-primary mt-2" href="paniers">Retour</a>
        </form>



        <c:if test="${error != null}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</div>

<%@include file="../../templates/footer.jsp"%>