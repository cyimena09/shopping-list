<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content panier">
        <h1 class="page-title">Paniers de course</h1>

        <div  style="margin-bottom: 15px">
            <a href="add_panier">Créer un nouveau panier</a>
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
                                    <span>${produit.quantite} ${produit.mesure.nom}</span>
                                    <div class="action" style="justify-content: flex-end;">
                                        <a style="margin: 0; padding: 0;" href=""><i class="fas fa-trash" style="color: red; padding: 0; margin: 0"></i></a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                    <a class="btn btn-success add-article" href="single_panier?idPanier=${panier.idPanier}">Modifier le panier</a>

                </div>

            </c:forEach>

        </div>
    </div>
</div>
