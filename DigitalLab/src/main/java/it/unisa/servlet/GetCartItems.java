package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.db.ContainsDAO;
import it.unisa.model.Contains;
import it.unisa.model.Product;

import javax.sql.DataSource;

@WebServlet("/GetCartItems")
public class GetCartItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni l'username dell'utente dal quale recuperare il carrello (puoi passarlo come parametro nella richiesta)
        String username = request.getParameter("username");

        try {
            ContainsDAO containsDAO = new ContainsDAO(dataSource);

            // Recupera tutte le righe "contains" associate all'utente
            Collection<Contains> containsList = containsDAO.doRetrieveAll(username);

            // Creare una collezione per i prodotti nel carrello
            Collection<Product> cartItems = new ArrayList<>();

            // Recuperare i prodotti correlati alle righe "contains" dal database
            for (Contains contains : containsList) {
                Product product = contains.getProduct();
                cartItems.add(product);
            }

            // Imposta gli attributi nella richiesta per passarli alla pagina JSP
            request.setAttribute("cartItems", cartItems);

            // Inoltra la richiesta alla pagina JSP del carrello
            request.getRequestDispatcher(request.getContextPath() + "/css/cart.jsp").forward(request, response);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}