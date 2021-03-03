package be.ifosup.shopping.servlet.mesure;

import be.ifosup.shopping.mesure.Mesure;
import be.ifosup.shopping.mesure.MesureDAO;
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

@WebServlet(name = "ServletUpdateMesure", urlPatterns = "/update_mesure")
public class ServletUpdateMesure extends HttpServlet {
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Récupération depuis l'url et conversion en Integer de l'id de la mesure.
        String strIdMesure = request.getParameter("idMesure");
        Integer idMesure = Integer.parseInt(strIdMesure);
        // Récupération du nom de la mesure depuis le formulaire.
        String nomMesure = request.getParameter("nomMesure");

        if (StringUtils.isBlank(nomMesure)) {
            String error = encode("La mesure ne peut pas être vide.", "UTF-8");
            response.sendRedirect("mesures?error=" + error);
        } else {
            try {
                mesureDAO.updateMesure(idMesure, new Mesure(null, nomMesure));
                request.setAttribute("mesures", mesureDAO.getMesures());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            request.getRequestDispatcher("views/mesure/mesures.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer idMesure = Integer.parseInt(request.getParameter("idMesure"));
            // Ajout de la mesure dans l'attribut.
            request.setAttribute("mesure", mesureDAO.getMesureById(idMesure));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/mesure/update_mesure.jsp").forward(request, response);
    }

}
