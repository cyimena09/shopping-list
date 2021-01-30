<%@page pageEncoding="UTF-8" %>

<%@include file="../../templates/header.jsp"%>

<%@include file="../../templates/navigation.jsp"%>

<a href="add_mesure">Ajouter un panier</a>

<h2>Liste des courses</h2>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">magasin</th>
        <th scope="col">article</th>
        <th scope="col">qty</th>
        <th scope="col">modifier</th>
        <th scope="col">supprimer</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mesures}" var="mesure">
        <tr>
            <th scope="row">1</th>
            <td>${panier.nom}</td>
            <td>${panier.nom}</td>
            <td>${panier.nom}</td>
            <th><a class="btn btn-warning" href="update_mesure?idMesure=${panier.idPanier}">Modifier</a></th>
            <th><a class="btn btn-danger" href="delete_mesure?idMesure=${panier.idPanier}">Supprimer</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../../templates/footer.jsp"%>