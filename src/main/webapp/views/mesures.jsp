<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>






<a href="add_mesure">Ajouter une mesure</a>

<h2>Liste des mesures</h2>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nom</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mesures}" var="mesure">
        <tr>
            <th scope="row">1</th>
            <td>${mesure.nom}</td>
            <th><a class="btn btn-warning" href="update_mesure?idMesure=${mesure.idMesure}">Modifier</a></th>
            <th><a class="btn btn-danger" href="delete_mesure?idMesure=${mesure.idMesure}">Supprimer</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../templates/footer.jsp"%>