<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content" >
        <h2>Liste des mesures existantes</h2>
        <table class="table" style="width: 450px">
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
                    <td>
                        <a class="btn btn-warning btn-sm mr-2" href="update_mesure?idMesure=${mesure.idMesure}">Modifier</a>
                        <a class="btn btn-danger btn-sm" href="delete_mesure?idMesure=${mesure.idMesure}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
