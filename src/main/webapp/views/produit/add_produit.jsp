<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content">

        <form action="create_produit" method="post">

            <div class="form-group">
                <label for="nomProduit">Nom du produit</label>
                <input id="nomProduit" class="form-control" name="nomProduit" type="text">
            </div>

            <div class="form-group">
                <label for="idCategorie">Catégorie de produit</label>
                <select class="form-control" name="idCategorie" id="idCategorie">
                    <c:forEach items="${categories}" var="categorie">
                        <p>fff</p>
                        <option value="${categorie.idCategorie}">${categorie.nom}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="idMesure">Unité de mesure</label>
                <select class="form-control" name="idMesure" id="idMesure">
                    <c:forEach items="${mesures}" var="mesure">
                        <option value="${mesure.idMesure}">${mesure.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary mt-2"><i class="fas fa-save"></i>Enregistrer</button>
        </form>

    </div>
</div>
