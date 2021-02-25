package be.ifosup.servlet.produit;

import be.ifosup.categorie.Categorie;
import be.ifosup.categorie.CategorieDAO;
import be.ifosup.mesure.Mesure;
import be.ifosup.mesure.MesureDAO;
import be.ifosup.dao.DAOFactory;
import be.ifosup.produit.Produit;
import be.ifosup.produit.ProduitDAO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletUpdateProduit", urlPatterns = "/update_produit")
public class ServletUpdateProduit extends HttpServlet {
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
        // Récupération de l'id du produit depuis l'url.
        Integer idProduit = Integer.parseInt(request.getParameter("idProduit"));
        // Récupération du nom de la mesure depuis le formulaire.
        String nomProduit = request.getParameter("nomProduit");
        Integer idMesure = Integer.parseInt(request.getParameter("idMesure"));
        Integer idCategorie = Integer.parseInt(request.getParameter("idCategorie"));

        if (StringUtils.isBlank(nomProduit)) {
            String error = encode("Le produit ne peut pas être vide.", "UTF-8");
            response.sendRedirect("produits?error=" + error);
        } else {
            try {
                // On ajoute la mesure.
                Mesure mesure = new Mesure();
                mesure.setIdMesure(idMesure);
                // On ajoute la catégorie.
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(idCategorie);
                // On ajoute la mesure.
                Produit produit = new Produit();
                produit.setNom(nomProduit);
                produit.setMesure(mesure);
                produit.setCategorie(categorie);
                // On enregistre le produit.
                produitDAO.updateProduit(idProduit, produit);

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            response.sendRedirect("produits");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int IdProduit = Integer.parseInt(request.getParameter("idProduit"));

        try {
            // Recuperation du produits, des catégories et des mesures.
            request.setAttribute("produit", produitDAO.getProduitById(IdProduit));
            request.setAttribute("mesures", mesureDAO.getMesures());
            request.setAttribute("categories", categorieDAO.getCategories());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/produit/update_produit.jsp").forward(request, response);
    }

}
