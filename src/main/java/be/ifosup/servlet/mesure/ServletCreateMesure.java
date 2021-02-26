package be.ifosup.servlet.mesure;

import be.ifosup.mesure.Mesure;
import be.ifosup.mesure.MesureDAO;
import be.ifosup.dao.DAOFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletCreateMesure", urlPatterns = "/create_mesure")
public class ServletCreateMesure extends HttpServlet {
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Recuperation de la mesure dans le formulaire.
        String nomMesure = request.getParameter("nomMesure");

        if (StringUtils.isBlank(nomMesure)) {
            String error = encode("La mesure ne peut pas être vide.","UTF-8");
            response.sendRedirect("mesures?error=" + error);
        } else {
            try {
                // Ajout de la catégoie dans la db.
                mesureDAO.createMesure(new Mesure(null, nomMesure));
                request.setAttribute("mesures", mesureDAO.getMesures());
                response.sendRedirect("mesures");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

}