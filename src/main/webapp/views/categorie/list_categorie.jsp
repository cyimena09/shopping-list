<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content" >
        <h2>Liste des catégories existantes</h2>
        <table class="table" style="width: 450px">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nom</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="categorie" varStatus="count">
                <tr>
                    <th scope="row">${count.count}</th>
                    <td>${categorie.nom}</td>
                    <td>
                        <a class="btn btn-warning btn-sm" href="update_categorie?idCategorie=${categorie.idCategorie}">Modifier</a>
                        <a class="btn btn-danger btn-sm" href="delete_categorie?idCategorie=${categorie.idCategorie}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
