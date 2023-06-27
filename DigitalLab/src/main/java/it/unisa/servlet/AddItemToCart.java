package it.unisa.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.db.ContainsDAO;
import it.unisa.model.Contains;
import it.unisa.db.ProductDAO;
import it.unisa.model.Product;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.ShoppingCart;
import javax.sql.DataSource;

@WebServlet("/AddItemToCart")
public class AddItemToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerUsername = request.getParameter("customerUsername"); // Inserisci il nome utente del cliente
        int productCode = Integer.parseInt(request.getParameter("productCode")); // Inserisci il codice del prodotto

        ShoppingCartDAO cartDAO = new ShoppingCartDAO(ds);
        ShoppingCart cart = null;
		try {
			cart = cartDAO.doRetrieveByKey(customerUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        ProductDAO productDAO = new ProductDAO(ds);
        Product product = null;
		try {
			product = productDAO.doRetrieveByKey(productCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        Contains contains = new Contains();
        contains.setShoppingCart(cart);
        contains.setProduct(product);

        ContainsDAO containsDAO = new ContainsDAO(ds);
        try {
            containsDAO.doSave(contains);
            response.getWriter().write("Prodotto aggiunto al carrello.");
        } catch (SQLException e) {
            response.getWriter().write("Errore durante l'aggiunta del prodotto al carrello.");
        }
    }
}