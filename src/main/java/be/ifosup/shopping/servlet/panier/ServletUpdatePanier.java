package be.ifosup.shopping.servlet.panier;

import be.ifosup.shopping.magasin.MagasinDAO;
import be.ifosup.shopping.panier.PanierDAO;
import be.ifosup.shopping.daoFactory.DAOFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletUpdatePanier", urlPatterns = "/update_panier")
public class ServletUpdatePanier extends HttpServlet {
    private PanierDAO panierDAO;
    private MagasinDAO magasinDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
        this.magasinDAO = daoFactory.getMagasinDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        Integer idPanier = null;
        String nomPanier = request.getParameter("nomPanier");
        // On récupère l'id du panier. Si l'id n'est pas un integer on renvoie une erreur.
        try {
            // Récupération des paramètres depuis l'url et la méthode post.
            idPanier = Integer.parseInt(request.getParameter("idPanier"));
        } catch (NumberFormatException e) {
            String error = encode("Impossible de récupérer le panier. Veuillez entrer un identifiant valide.", "UTF-8");
            e.printStackTrace();
            response.sendRedirect("single_panier?idPanier=" + idPanier + "&error=" + error);
        }
        // On vérifie le champ du formulaire
        if (StringUtils.isBlank(nomPanier)) {
            String error = encode("Veuillez entrer un nom valide.", "UTF-8");
            response.sendRedirect("update_panier?idPanier=" + idPanier + "&error=" + error);
        } else {
            // Si le formulaire est bon on enregistre
            try {
                panierDAO.updatePanierNom(idPanier, nomPanier);
                response.sendRedirect("single_panier?idPanier=" + idPanier);
            } catch (SQLException throwable) {
                String error = encode("Impossible de mettre à jour le panier.", "UTF-8");
                throwable.printStackTrace();
                response.sendRedirect("update_panier?idPanier=" + idPanier + "&error=" + error);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strIdPanier = request.getParameter("idPanier");
        String error = request.getParameter("error");

        try {
            // Ajout de la mesure dans l'attribut.
            request.setAttribute("panier", panierDAO.getPanierById(Integer.parseInt(strIdPanier)));
            request.setAttribute("magasins", magasinDAO.getMagasins());
            request.setAttribute("error", error);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("views/panier/update_panier.jsp").forward(request, response);
    }

}
