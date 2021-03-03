package be.ifosup.shopping.servlet.produit;

import be.ifosup.shopping.categorie.Categorie;
import be.ifosup.shopping.categorie.CategorieDAO;
import be.ifosup.shopping.mesure.Mesure;
import be.ifosup.shopping.mesure.MesureDAO;
import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.produit.Produit;
import be.ifosup.shopping.produit.ProduitDAO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletCreateProduit", urlPatterns = "/create_produit")
public class ServletCreateProduit extends HttpServlet {
    private ProduitDAO produitDAO;
    private MesureDAO mesureDAO;
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
        this.mesureDAO = daoFactory.getMesureDAO();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Initialisation des variables
        String nomProduit = request.getParameter("nomProduit");
        Integer idMesure = null;
        Integer idCategorie = null;

        // Vérification du nom du produit
        if (StringUtils.isBlank(nomProduit)) {
            String error = encode("Le produit ne peut pas être vide.", "UTF-8");
            response.sendRedirect("produits?error=" + error);
            return;
        } else {

            try {
                // Tentative de récupération des valeurs du formulaire.
                idMesure = Integer.parseInt(request.getParameter("idMesure"));
                idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
            } catch(NumberFormatException e) {
                e.printStackTrace();
                String error = "Impossible d'enregistrer le produit";
                response.sendRedirect("produits?error=" + error);
                return; // S'il y a une erreur, on ne va pas plus loin.
            }
        }
        // Ajout dans la mesure.
        Mesure mesure = new Mesure();
        mesure.setIdMesure(idMesure);
        // Ajout dans la catégorie.
        Categorie categorie = new Categorie();
        categorie.setIdCategorie(idCategorie);
        // Ajout dans produit.
        Produit produit = new Produit();
        produit.setNom(nomProduit);
        produit.setMesure(mesure);
        produit.setCategorie(categorie);

        try {
            // Enregistrement dans la Db et redirection.
            produitDAO.createProduit(produit);
            request.setAttribute("produits", produitDAO.getProduits());
            request.setAttribute("mesures", mesureDAO.getMesures());
            request.setAttribute("categories", categorieDAO.getCategories());
            response.sendRedirect("produits");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}