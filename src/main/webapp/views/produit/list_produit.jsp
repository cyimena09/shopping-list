<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">

        <h2>Liste des produits</h2>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nom</th>
                <th scope="col">Unité</th>
                <th scope="col">Catégorie</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${produits}" var="produit" varStatus="count">
                <tr>
                    <th scope="row">${count.count}</th>
                    <td>${produit.nom}</td>
                    <td>${produit.mesure.nom}</td>
                    <td>${produit.categorie.nom}</td>
                    <th>
                        <a class="btn btn-warning btn-sm mr-2" href="update_produit?idProduit=${produit.idProduit}">Modifier</a>
                        <a class="btn btn-danger btn-sm" href="delete_produit?idProduit=${produit.idProduit}">Supprimer</a>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
