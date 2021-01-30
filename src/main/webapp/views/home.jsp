<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Shopping List</title>
    <!-- Boostrap 5 CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ"
          crossorigin="anonymous">
    <link rel="stylesheet" href="css/navigation.scss"/>
    <link rel="stylesheet" href="css/styles.scss"/>
    <link rel="stylesheet" href="css/home.scss"/>
    <link rel="stylesheet" href="css/footer.scss"/>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>

    <%--NAVIGATION--%>
    <header>
        <div id=wrapper-brand class="wrapper">
            <div class="brand">
                <h1>Shopping List</h1>
            </div>
        </div>

        <div id="wrapper-nav" class="wrapper">
            <nav>
                <ul>
                    <li><a href="home">Accueil</a></li>
                    <li><a href="paniers">Vos paniers</a></li>
                    <li><a href="add_magasin">Ajouter un magasin</a></li>
                    <li><a href="add_categorie">Ajouter une catégorie</a></li>
                    <li><a href="add_mesure">Ajouter une mesure</a></li>
                </ul>
            </nav>
        </div>
    </header>


    <%-- HOME--%>
    <div class="home page">
        <div class="content">
            <div class="presentation">

                <div class="wrapper">
                    <h1>Shopping List - Faire vos courses n'aura jamais été aussi facile !</h1>
                    <p style="font-weight: bold">Vous en avez marre :</p>
                    <ul class="fa-ul">
                        <li><span class="fa-li"><i class="fas fa-caret-right"></i></span>De perdre du temps dans un magasin parce que vous ne savez plus ce que vous devez acheter ?</li>
                        <li><span class="fa-li"><i class="fas fa-caret-right"></i></span>De ranger vos courses et de vous rendre compte que vous avez oublié un produit ?</li>
                        <li><span class="fa-li"><i class="fas fa-caret-right"></i></span>De devoir réécrire encore et encore une liste de course car vous avez perdu l'ancien ?</li>
                    </ul>
                    <p style="font-weight: bold">Alors Shopping List est fait pour vous !</p>
                    <p>Shopping List vous aide à vous souvenir des produits que vous devez acheter.</p>
                </div>
                <div class="start">
                    <a href="">Commencer une liste</a>
                </div>
            </div>

            <div class="illustration">
                <img src="assets/grocery-1232944_1280.jpg" alt="">
            </div>
        </div>
    </div>

    <%--FOOTER--%>
    <footer>
        <div id="test" class="wrapper">

            <div class="entreprise">
                <h5>Shopping List</h5>
                <ul>
                    <li><a href="">Accueil</a></li>
                    <li><a href="">Vos paniers</a></li>
                    <li><a href="">Ajouter un magasin</a></li>
                    <li><a href="">Ajouter une catégorie</a></li>
                    <li><a href="">Ajouter une mesure</a></li>
                </ul>
            </div>

            <div class="developped-by">
                <h5>Projet réalisé par</h5>
                <ul>
                    <li>Emile Cyimena</li>
                    <li>Benoît Vankoningslo</li>
                    <li>Xavier</li>
                </ul>
            </div>
        </div>

        <div class="all-right-reserved">
            <p>© 2021. Tous droits réservés.</p>
        </div>
    </footer>


</body>
</html>

