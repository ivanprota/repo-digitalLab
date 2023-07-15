package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.ProductDAO;
import it.unisa.db.ReviewDAO;
import it.unisa.model.Product;

@WebServlet("/Catalogue")
public class Catalogue extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    {
    	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
    	Collection<Product> products = null;
    	ProductDAO dao = new ProductDAO(ds);
    	String filter = request.getParameter("filter");
    	String searchBar = request.getParameter("searchBar");
    	
    	try
    	{	
    		if (filter == null && searchBar == null)
    		{
    			products = dao.doRetrieveAll(null);
    		}   		
    		else if (filter != null && filter.equals("price"))
	    	{
	    		String priceRange1String = request.getParameter("priceRange1");
	    		String priceRange2String = request.getParameter("priceRange2");
	    		
	    		if (priceRange2String == null)
	    		{
	    			if (priceRange1String.equals("200-"))
	    				products = dao.doRetrieveByFilter(null, "200");
	    			else if (priceRange1String.equals("800+"))
	    				products = dao.doRetrieveByFilter("800", null);
	    		}
	    		else if (priceRange1String != null && priceRange2String != null)
	    			products = dao.doRetrieveByFilter(priceRange1String, priceRange2String);
	    	}
    		else if (filter != null && filter.equals("brand"))
    		{
    			String[] brands = request.getParameterValues("brands");
    			products = dao.doRetrieveByFiler(brands);
    		}
    		else if (filter != null && filter.equals("category"))
    		{
    			String category = request.getParameter("categoryName");
    			products = dao.doRetrieveByFilter(category);
    		}
    		else if (filter != null && filter.equals("assessment"))
    		{
    			String starsString = request.getParameter("stars");
    			int stars = -1;
    			if (starsString != null)
    				stars = Integer.parseInt(starsString);
    			
    			products = dao.doRetrieveByFilter(stars);
    		}
    		else if (searchBar != null)
    		{
    			String category = request.getParameter("category");
    			String search = request.getParameter("search");
    			
    			if (category.equals("Tutte le categorie"))
    			{
    				if (search.isEmpty())
    				{
    					products = dao.doRetrieveAll(null);
    				}
    				else
    				{
    					String searchInput = search.toLowerCase();
    					Collection<Product> productsCollection = new LinkedList<Product>();
    					products = dao.doRetrieveAll(null);
    					Iterator<Product> it = products.iterator();
    					while (it.hasNext())
    					{
    						Product product = (Product) it.next();
    						String brandModelCategory = ("" + product.getBrand() +" "+ product.getModel() +" "+ product.getCategory()).toLowerCase();
    						
    						if (brandModelCategory.contains(searchInput))
    						{
    							productsCollection.add(product);
    						}
    					}
    					
    					products = productsCollection;
    				}
    			}
    			else
    			{
    				if (search.isEmpty())
    				{
    					products = dao.doRetrieveByFilter(category);
    				}
    				else
    				{
    					String searchInput = search.toLowerCase();
    					Collection<Product> productsCollection = new LinkedList<Product>();
    					products = dao.doRetrieveByFilter(category);
    					Iterator<Product> it = products.iterator();
    					while (it.hasNext())
    					{
    						Product product = (Product) it.next();
    						String brandModelCategory = ("" + product.getBrand() +" "+ product.getModel() +" "+ product.getCategory()).toLowerCase();
    						if (brandModelCategory.contains(searchInput))
    							productsCollection.add(product);
    					}
    					
    					products = productsCollection;
    				}
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("common/catalogue.jsp");
        try 
        {
			dispatcher.forward(request, response);
		} 
        catch (ServletException e) 
        {
        	System.out.println(e);
		} 
        catch (IOException e) 
        {
        	System.out.println(e);
		}
        return;
    }
}