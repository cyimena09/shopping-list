package be.ifosup.shopping.servlet.panier;

import be.ifosup.shopping.panier.Panier;
import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.magasin.Magasin;
import be.ifosup.shopping.magasin.MagasinDAO;
import be.ifosup.shopping.produit.ProduitDAO;
import be.ifosup.shopping.panier.PanierDAO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletCreatePanier", urlPatterns = "/create_panier")
public class ServletCreatePanier extends HttpServlet {
    private PanierDAO panierDAO;
    private MagasinDAO magasinDAO;
    private ProduitDAO produitDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
        this.magasinDAO = daoFactory.getMagasinDAO();
        this.produitDAO = daoFactory.getProduitDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8
        request.setCharacterEncoding("UTF-8");

        // Recuperation et conversion des champs du formulaire.
        String nomPanier = request.getParameter("nomPanier");
        String strIdMagasin = request.getParameter("idMagasin");

        if (StringUtils.isAnyBlank(nomPanier, strIdMagasin)) {
            String error = encode("Veuillez remplir tous les champs.", "UTF-8");
            response.sendRedirect("create_panier?error=" + error);
        } else {
            try {
                Integer idMagasin = Integer.parseInt(request.getParameter("idMagasin"));
                Magasin magasin = new Magasin();
                Panier panier = new Panier();
                // On set les valeurs du magasin
                magasin.setIdMagasin(idMagasin);
                // On set les valeurs du panier
                panier.setNom(nomPanier);
                panier.setMagasin(magasin);
                // Ajout du panier dans la db.
                panierDAO.createPanier(panier);
                // On appelle le servletListPanier qui va se charger de récupérer les paniers
                response.sendRedirect("paniers");
            } catch (Exception throwable) {
                throwable.printStackTrace();
                String error = encode("Une erreur est survenue, impossible de créer le panier.", "UTF-8");
                response.sendRedirect("create_panier?error=" + error);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = request.getParameter("error");

        try {
            request.setAttribute("magasins", magasinDAO.getMagasins());
            request.setAttribute("produits", produitDAO.getProduits());
            request.setAttribute("error", error);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/panier/create_panier.jsp").forward(request, response);
    }

}
