<%@include file="../../templates/header.jsp"%>
<%@include file="../../templates/navigation.jsp"%>

<div class="page">
    <div class="content">
        <h2>Nom du magasin : ${panier.nomMagasin}</h2>
        Produits dans le panier :

<%--        <c:forEach items="${produits}" var="produit">--%>
<%--        <ul>--%>
<%--            <li>${produit.nomProduit}</li>--%>
<%--        </ul>--%>

<%--        </c:forEach>--%>


    <div>
        Tous les produits :   Ajouter au panier
        <c:forEach items="${allProduits}" var="produit">
            <ul>
                <li>${produit.nom}</li>
            </ul>

            <a href="add_produit?idPanier=${panier.idPanier}&idMagasin=${panier.idMagasin}&idProduit=${produit.idProduit}">+</a>
        </c:forEach>
    </div>

    </div>
</div>

<%@include file="../../templates/footer.jsp"%>

<%--<h2>Un single panier est composé d'un panier qui comprend le nom du magasin et de ces produit (avec mesures) etc</h2>--%>
<%--<h2>On peut rajouter des produits dans le panier directement depuis cette fenetre sans besoin de changer</h2>--%>

<%--<h2>A coté du nom du magasin petit icone pour modifier son nom</h2>--%>
<%--<h2>On peut égalemment supprimer le nom du magasin</h2>--%>