<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h1 class="page-title">Créer un nouveau panier</h1>
        <form action="create_panier" method="post">

            <div class="form-group">
                <label for="nomPanier">Nom de votre panier</label>
                <input id="nomPanier" class="form-control" type="text" name="nomPanier">
            </div>

            <div class="form-group">
                <label for="idMagasin">Sélectionner le magasin</label>
                <select id="idMagasin" class="form-control" name="idMagasin">
                    <c:forEach items="${magasins}" var="magasin">
                        <option value="${magasin.idMagasin}">${magasin.nom}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary mt-2"><i class="fas fa-save"></i>Enregistrer</button>
        </form>

        <c:if test="${error != null}">
            <p class="error">${error}</p>
        </c:if>

    </div>
</div>

<%@include file="../../templates/footer.jsp"%>