package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.ProductDAO;
import it.unisa.model.Product;

@WebServlet("/Catalogue")
public class Catalogue extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/storage");

            ProductDAO productDAO = new ProductDAO(dataSource);

            Collection<Product> products = productDAO.doRetrieveAll(null);
            
            request.setAttribute("products", products);

            request.getRequestDispatcher("/common/catalogue.jsp").forward(request, response);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}