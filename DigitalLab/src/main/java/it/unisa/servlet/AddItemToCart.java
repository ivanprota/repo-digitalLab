package it.unisa.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.model.ShoppingCart;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.Customer;
import it.unisa.model.Contains;
import it.unisa.db.ContainsDAO;
import it.unisa.model.Product;
import it.unisa.db.ProductDAO;
import javax.sql.DataSource;

@WebServlet("/addItemToCart")
public class AddItemToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShoppingCartDAO shoppingCartDAO;
    @SuppressWarnings("unused")
	private ContainsDAO containsDAO;
    @SuppressWarnings("unused")
	private ProductDAO productDAO;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        shoppingCartDAO = new ShoppingCartDAO(dataSource);
        containsDAO = new ContainsDAO(dataSource);
        productDAO = new ProductDAO(dataSource);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera l'ID del prodotto selezionato dalla richiesta
        int productCode = Integer.parseInt(request.getParameter("productCode"));

        // Recupera l'utente corrente dal sistema di autenticazione
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        String customerUsername = customer.getUsername();

        // Recupera il carrello dell'utente dal database utilizzando il nome utente
        ShoppingCart shoppingCart = null;
        try {
            shoppingCart = shoppingCartDAO.doRetrieveByKey(customerUsername);
        } catch (SQLException e) {
            // Gestisci eventuali errori
            e.printStackTrace();
        }

        if (shoppingCart == null) {
            // Il carrello dell'utente non esiste nel database, puoi creare uno nuovo se necessario
            shoppingCart = new ShoppingCart();
            shoppingCart.setCustomer(customer);
            shoppingCart.setSize(0);
        }

     // Incrementa la dimensione del carrello
        int newSize = shoppingCart.getSize() + 1;
        shoppingCart.setSize(newSize);

        // Salva il carrello aggiornato nel database
        try {
            shoppingCartDAO.doSave(shoppingCart);
        } catch (SQLException e) {
            // Gestisci eventuali errori
            e.printStackTrace();
        }

        // Crea la relazione tra il carrello e il prodotto
        Contains contains = new Contains();
        contains.setShoppingCart(shoppingCart);
        // Recupera il prodotto dal database utilizzando il codice del prodotto
        ProductDAO productDAO = new ProductDAO(dataSource);
        Product product = null;
		try {
			product = productDAO.doRetrieveByKey(productCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        contains.setProduct(product);

        // Salva la relazione tra carrello e prodotto nel database
        try {
        	ContainsDAO containsDAO = new ContainsDAO(dataSource);
            containsDAO.doSave(contains);
        } catch (SQLException e) {
            // Gestisci eventuali errori
            e.printStackTrace();
        }

        // Salva l'oggetto ShoppingCart nella sessione
        request.getSession().setAttribute("shoppingCart", shoppingCart);

        // Reindirizza l'utente alla pagina del catalogo o al carrello
        response.sendRedirect("catalogue.jsp");
    }
}