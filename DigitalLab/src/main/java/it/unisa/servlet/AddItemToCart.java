package it.unisa.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.model.ShoppingCart;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.Customer;
import it.unisa.model.Contains;
import it.unisa.db.ContainsDAO;
import it.unisa.db.CustomerDAO;
import it.unisa.model.Product;
import it.unisa.db.ProductDAO;
import javax.sql.DataSource;

@WebServlet("/addItemToCart")
public class AddItemToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO(dataSource);
        ContainsDAO containsDAO = new ContainsDAO(dataSource);
        ProductDAO productDAO = new ProductDAO(dataSource);
    	
        // Recupera l'ID del prodotto selezionato dalla richiesta
    	int productCode = -1;
    	try
    	{
    		productCode = Integer.parseInt(request.getParameter("productCode"));
    	}
		catch (NumberFormatException e)
		{
			System.err.println(e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/common/product.jsp");
			dispatcher.forward(request, response);
			return;				
		}

        // Recupera l'utente corrente dal sistema di autenticazione
        String customerUsername = request.getParameter("customerUsername");
        if (customerUsername == null || customerUsername.trim().equals(""))
        {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/common/product.jsp");
			dispatcher.forward(request, response);
			return;	      	
        }

        // Recupera il carrello dell'utente dal database utilizzando il nome utente
        ShoppingCart shoppingCart = null;
        try {
            shoppingCart = shoppingCartDAO.doRetrieveByKey(customerUsername);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        try {
        	shoppingCartDAO.doSizeUpdateByKey(customerUsername);
        	
        } catch (SQLException e) {
        	System.err.println(e);
        }
        
        // Crea la relazione tra il carrello e il prodotto
        Contains contains = new Contains();
        contains.setShoppingCart(shoppingCart);
        
        // Recupera il prodotto dal database utilizzando il codice del prodotto
        Product product = null;
		try {
			product = productDAO.doRetrieveByKey(productCode);
		} catch (SQLException e) {
			System.err.println(e);
		}
        contains.setProduct(product);

        // Salva la relazione tra carrello e prodotto nel database
        try {
        	contains.setQuantity(1);
            containsDAO.doSave(contains);
        } catch (SQLException e) {
            // Gestisci eventuali errori
        	System.err.println(e);
            try
            {    	
            	containsDAO.doUpdateQuantity(customerUsername, productCode);
            }
            catch (SQLException e2)
            {
            	System.err.println(e2);
            }
        }
    }
}