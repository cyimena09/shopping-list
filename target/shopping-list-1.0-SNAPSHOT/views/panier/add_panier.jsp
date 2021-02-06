<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h1 class="page-title">Créer un nouveau panier</h1>
        <form action="add_panier" method="post">

            <div class="form-group">
                <label for="nomPanier">Nom de votre panier</label>
                <input id="nomPanier" type="text" name="nomPanier">
            </div>

            <div class="form-group">
                <label for="idMagasin">Sélectionner le magasin</label>
                <select id="idMagasin" class="form-control" name="idMagasin">
                    <c:forEach items="${magasins}" var="magasin">
                        <option value="${magasin.idMagasin}">${magasin.nom}</option>
                    </c:forEach>
                </select>
            </div>

<%--            <div class="form-group">--%>
<%--                <label for="idProduit">Ajouter un produit</label>--%>
<%--                <select id="idProduit" class="form-control" name="idProduit" >--%>
<%--                    <c:forEach items="${produits}" var="produit">--%>
<%--                        <option value="${produit.idProduit}">${produit.nom}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>

<%--            <div class="form-group">--%>
<%--                <label for="quantite">Qte</label>--%>
<%--                <input id="quantite" type="text" name="quantite">--%>
<%--            </div>--%>

            <button type="submit" class="btn btn-primary mt-2"><i class="fas fa-save"></i>Enregistrer</button>
        </form>
    </div>
</div>

<%@include file="../../templates/footer.jsp"%>