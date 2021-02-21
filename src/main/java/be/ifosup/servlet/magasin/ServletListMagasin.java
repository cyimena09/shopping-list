package be.ifosup.servlet.magasin;

import be.ifosup.dao.DAOFactory;
import be.ifosup.magasin.MagasinDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletListMagasin", urlPatterns = "/magasins")
public class ServletListMagasin extends HttpServlet {
    private MagasinDAO magasinDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.magasinDAO = daoFactory.getMagasinDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = request.getParameter("error");

        try {
            request.setAttribute("magasins", magasinDAO.getMagasins());
            request.setAttribute("error", error);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/magasin/magasins.jsp").forward(request, response);
    }

}
