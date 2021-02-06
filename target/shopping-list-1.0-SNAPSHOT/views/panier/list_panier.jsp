<%@page pageEncoding="UTF-8" %>

<div class="page">
    <div class="content panier">
        <h1 class="page-title">Paniers de course</h1>
        <div  style="margin-bottom: 15px"><a href="add_panier">Créer un nouveau panier</a></div>
        <div class="magasins">

            <c:forEach items="${magasins}" var="magasin">

                <div class="magasin">
                    <div class="head">
                        <h2>${magasin.nomMagasin}</h2>
                    </div>
                    <div class="label">
                        <p>Produit</p>
                        <div style="display: flex">
                            <p>Quantité</p>
                            <p class="action"></p>
                        </div>
    
                    </div>
                    <ul>
                        <li>
                            <span>Aricot</span>

                            <div style="display: flex">
                                <span style="border-right: 1px solid green">15 kg</span>
                                <div class="action">
                                    <button class="btn btn-warning"><i class="fas fa-edit"></i></button>
                                    <i class="fas fa-trash"></i>
                                </div>
                            </div>

                        </li>
                        <li>Pomme de terre</li>
                        <li>Poivron</li>
                        <li>Chocolat</li>
                    </ul>


                    <a class="btn btn-success add-article" href="single_panier">Ajouter un article</a>

                </div>

            </c:forEach>

        </div>
    </div>
</div>
