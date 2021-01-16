<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>

<a href="add_produit">Ajouter un produit</a>


<h2>Liste des produits</h2>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nom</th>
        <th scope="col">Quantité</th>
        <th scope="col">Catégorie</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${produits}" var="produit">
        <tr>
            <th scope="row">1</th>
            <td>${produit.nom}</td>
            <td></td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../templates/footer.jsp"%>