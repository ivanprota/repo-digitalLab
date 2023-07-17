package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Data Source
import javax.sql.DataSource;
// Model
import it.unisa.model.Product;
import it.unisa.model.Picture;
// DAO
import it.unisa.db.ProductDAO;
import it.unisa.db.PictureDAO;
// Util
import java.util.Collection;
import java.util.LinkedList;

@WebServlet("/Configuration")
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		ProductDAO productDAO = new ProductDAO(ds);
	   	Collection<Product> products = null;
	   	String category = (String) request.getParameter("category");
	   	System.out.println("SERVLET " + category);
	   	
	   	try {
	   		// Prendiamo tutti i prodotti appartenenti alla categoria desiderata
			products = productDAO.doRetrieveByFilter(category);
		} catch (SQLException e) {
			System.out.println("Errore ottenimento prodotti");
		}
	   	
   		PictureDAO pictureDAO = new PictureDAO(ds);
		Collection<Picture> pictures = new LinkedList<Picture>();
	   	
	   	for(Product product : products) {
	   		try {
	   			// Salviamo le foto dei prodotti ottenuti
				pictures.add(pictureDAO.doRetrieveByKey(product.getCode()));
			} catch (SQLException e) {
				System.out.println("Errore ottenimento foto prodotti");
			}
	   	}
		
		// Inviamo products e pictures
	   	request.removeAttribute("category");
	   	request.setAttribute("products", products);
	   	request.setAttribute("pictures", pictures);
	   	RequestDispatcher dispatcher = request.getRequestDispatcher("common/configuration.jsp");
	   	dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
