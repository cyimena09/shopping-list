<%@page pageEncoding="UTF-8" %>
<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div id="single-panier" class="content">

        <div class="wrapper">
            <h2>${panier.magasin.nom} <span class="single-panier-description">- ${panier.nom}</span></h2>
            <div class="legend">
                <p>Produits dans le panier</p>
                <p>Quantité</p>
            </div>
            <ul>
                <c:forEach items="${produits}" var="produit">

                        <li class="legend"><span>${produit.nom}</span> <span>${produit.quantite} ${produit.mesure.nom}</span></li>

                </c:forEach>
            </ul>
        </div>

        <div class="wrapper all-produits">
            <h2>Tous les produits</h2>p
            <ul>
            <c:forEach items="${allProduits}" var="produit">
                    <li>
                            ${produit.nom}
                                <a class="btn btn-success btn-sm mt-1 mb-1" href="add_produit?idPanier=${panier.idPanier}&idProduit=${produit.idProduit}">                            <i class="fas fa-plus"></i>
                        </a>
                    </li>


            </c:forEach>
            </ul>
        </div>

    </div>
</div>

<%@include file="../../templates/footer.jsp"%>
