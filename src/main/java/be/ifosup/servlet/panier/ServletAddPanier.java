package be.ifosup.servlet.panier;

import be.ifosup.panier.Panier;
import be.ifosup.dao.DAOFactory;
import be.ifosup.magasin.Magasin;
import be.ifosup.magasin.MagasinDAO;
import be.ifosup.produit.ProduitDAO;
import be.ifosup.panier.PanierDAO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletAddPanier", urlPatterns = "/add_panier")
public class ServletAddPanier extends HttpServlet {
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
        Magasin magasin = new Magasin();
        Panier panier = new Panier();

        String nomPanierr = request.getParameter("nomPanier");

        if (StringUtils.isBlank(nomPanierr)) {
            String error = encode("Le champs panier ne peut pas être vide.", "UTF-8");
            response.sendRedirect("paniers?error=" + error);
        } else {
            try {
                // Recuperation et conversion en Integer de l'id du magasin et du produit.
                String nomPanier = request.getParameter("nomPanier");
                Integer idMagasin = Integer.parseInt(request.getParameter("idMagasin"));
                // On set les valeurs du magasin
                magasin.setIdMagasin(idMagasin);
                // On set les valeurs du panier
                panier.setNom(nomPanier);
                panier.setMagasin(magasin);
                // Ajout du panier dans la db.
                panierDAO.createPanier(panier);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            // On appelle le servletListPanier qui va se charger de récupérer les paniers
            response.sendRedirect("paniers");
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

        request.getRequestDispatcher("views/panier/add_panier.jsp").forward(request, response);
    }

}
