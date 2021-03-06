<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div id="single-panier" class="content">

        <div class="wrapper">
            <h2 style="display: flex; justify-content: space-between">
                <span>${panier.magasin.nom} <span class="single-panier-description">- ${panier.nom}</span></span>
                <a style="color: #ffca2c" href="update_panier?idPanier=${panier.idPanier}"><i class="far fa-edit"></i></a>
            </h2>

            <div class="legend">
                <p>Produits dans le panier</p>
                <p>Quantité</p>
            </div>

            <ul>
                <c:forEach items="${produits}" var="produit">
                    <li class="legend">
                        <span>${produit.nom}</span>
                        <form action="update_produit_in_panier?idPanierProduit=${produit.idPanierProduit}&idPanier=${panier.idPanier}" method="post">
                            <input class="form-control" style="width: 50px" type="text" value="<fmt:formatNumber type = 'number' value = '${produit.quantite}' />" name="quantite">
                            <span> ${produit.mesure.nom}</span>
                            <div>
                                <button class="btn btn-warning btn-sm"><i class="fas fa-save"></i></button>
                                <a class="btn btn-danger btn-sm mt-1 mb-1"
                                   href="delete_produit_in_panier?idPanierProduit=${produit.idPanierProduit}&idPanier=${panier.idPanier}">
                                    <i class="fas fa-trash"></i>
                                </a>
                            </div>
                        </form>
                    </li>
                </c:forEach>
            </ul>

            <c:if test="${error != null}">
                <p class="error">${error}</p>
            </c:if>
        </div>

        <div class="wrapper all-produits">
            <h2>Tous les produits</h2>

            <ul>
                <c:forEach items="${categories}" var="categorie">
                    <p style="font-size: 18px; font-weight: bold;margin-top: 20px">${categorie.nom}</p>
                    <c:forEach items="${allProduits}" var="produit">
                        <c:if test="${categorie.idCategorie == produit.categorie.idCategorie}">
                            <li>
                                <span>${produit.nom}</span>
                                <a class="btn btn-success btn-sm mt-1 mb-1" href="add_produit_in_panier?idPanier=${panier.idPanier}&idProduit=${produit.idProduit}">
                                    <i class="fas fa-plus"></i>
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </ul>

            <c:if test="${warning != null}">
                <p class="error">${warning}</p>
            </c:if>
        </div>

    </div>
</div>

<%@include file="../../templates/footer.jsp"%>
