<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content panier">
        <h1 class="page-title">Paniers de course</h1>

        <div  style="margin-bottom: 20px">
            <a class="btn btn-success" href="create_panier">Créer un nouveau panier</a>
        </div>

        <div class="magasins">
            <c:forEach items="${paniers}" var="panier">
                <div class="magasin">
                    <div class="head">
                        <h2 style="display: flex; justify-content: space-between">
                            <span>${panier.magasin.nom}</span>
                            <a style="color: red" href="delete_panier?idPanier=${panier.idPanier}"><i class="far fa-times-circle"></i></a>
                        </h2>
                        <p>${panier.nom}</p>
                    </div>

                    <div class="label">
                        <p>Produit</p>
                        <div style="display: flex">
                            <p>Quantité</p>
                            <p class="action"></p>
                        </div>
                    </div>

                    <ul>
                        <c:forEach items="${panier.produitList}" var="produit">
                            <li>
                                <span>${produit.nom}</span>

                                <div style="display: flex;">
                                    <span><fmt:formatNumber value = "${produit.quantite}" type = "number"/> ${produit.mesure.nom}</span>
                                    <div class="action" style="justify-content: flex-end;">
                                        <a class="btn btn-danger btn-sm mt-1 mb-1"
                                           href="delete_produit_in_panier?idPanierProduit=${produit.idPanierProduit}&idPanier=${panier.idPanier}&view=paniers">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                    <a class="btn btn-success add-article" href="single_panier?idPanier=${panier.idPanier}">Ajouter des produits</a>

                </div>

            </c:forEach>

        </div>
    </div>
</div>
