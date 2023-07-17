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
	   	Collection<Product> caseCollection = null;
	   	Collection<Product> cpuCollection = null;
	   	Collection<Product> motherboardCollection = null;
	   	Collection<Product> ramCollection = null;
	   	Collection<Product> gpuCollection = null;
	   	Collection<Product> storageCollection = null;
	   	Collection<Product> psuCollection = null;
	   	Collection<Product> coolingCollection = null;
	   	Collection<Product> monitorCollection = null;
	   	Collection<Product> osCollection = null;
	   	Collection<Product> extraCollection = null;
	   	
	   	try {
	   		// Prendiamo tutti i prodotti appartenenti alla categoria desiderata
	   		caseCollection = productDAO.doRetrieveByFilter("Case");
	   		cpuCollection = productDAO.doRetrieveByFilter("CPU");
	   		motherboardCollection = productDAO.doRetrieveByFilter("Schede Madri");
	   		ramCollection = productDAO.doRetrieveByFilter("RAM");
	   		gpuCollection = productDAO.doRetrieveByFilter("GPU");
	   		storageCollection = productDAO.doRetrieveByFilter("Storage");
	   		psuCollection = productDAO.doRetrieveByFilter("PSU");
	   		coolingCollection = productDAO.doRetrieveByFilter("Cooling");
	   		monitorCollection = productDAO.doRetrieveByFilter("Monitor");
	   		osCollection = productDAO.doRetrieveByFilter("Sistemi Operativi");
	   		extraCollection = productDAO.doRetrieveByFilter("Accessori");
	   		
		} catch (SQLException e) {
			System.out.println("Errore ottenimento prodotti");
		}
	   	
   		PictureDAO pictureDAO = new PictureDAO(ds);
		Collection<Picture> casePictures = new LinkedList<Picture>();
	   	
	   	for(Product product : caseCollection) {
	   		try {
	   			// Salviamo le foto dei prodotti ottenuti
	   			casePictures.add(pictureDAO.doRetrieveByKey(product.getCode()));
			} catch (SQLException e) {
				System.out.println("Errore ottenimento foto prodotti");
			}
	   	}
		
		// Inviamo products e pictures
	   	request.setAttribute("case", caseCollection);
	   	request.setAttribute("cpu", cpuCollection);
	   	request.setAttribute("motherboard", motherboardCollection);
	   	request.setAttribute("ram", ramCollection);
	   	request.setAttribute("gpu", gpuCollection);
	   	request.setAttribute("storage", storageCollection);
	   	request.setAttribute("psu", psuCollection);
	   	request.setAttribute("cooling", coolingCollection);
	   	request.setAttribute("monitor", monitorCollection);
	   	request.setAttribute("os", osCollection);
	   	request.setAttribute("extra", extraCollection);
	   	
	   	request.setAttribute("casePictures", casePictures);
	   	
	   	RequestDispatcher dispatcher = request.getRequestDispatcher("common/configuration.jsp");
	   	dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
