package be.ifosup.shopping.servlet.magasin;

import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.magasin.Magasin;
import be.ifosup.shopping.magasin.MagasinDAO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletCreateMagasin", urlPatterns = "/create_magasin")
public class ServletCreateMagasin extends HttpServlet {
    private MagasinDAO magasinDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.magasinDAO = daoFactory.getMagasinDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Recuperation de la mesure dans le formulaire.
        String nomMagasin = request.getParameter("nomMagasin");

        if (StringUtils.isBlank(nomMagasin)) {
            String error = encode("Le champs magasin ne peut pas être vide.","UTF-8");
            response.sendRedirect("magasins?error=" + error);
        } else {
            try {
                // Ajout du magasin dans la db.
                magasinDAO.createMagasin(new Magasin(null, nomMagasin));
                request.setAttribute("magasins", magasinDAO.getMagasins());
                response.sendRedirect("magasins");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
