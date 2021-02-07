package be.ifosup.servlet.categorie;

import be.ifosup.dao.DAOFactory;
import be.ifosup.categorie.CategorieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletListCategorie", urlPatterns = "/categories")
public class ServletListCategorie extends HttpServlet {
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("categories", categorieDAO.getCategories());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/categorie/categories.jsp").forward(request, response);
    }

}
