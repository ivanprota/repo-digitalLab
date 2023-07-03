package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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
    	
    	try
    	{	
    		if (filter == null)
    		{
    			products = dao.doRetrieveAll(null);
    		}   		
    		else if (filter.equals("price"))
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
    		else if (filter.equals("brand"))
    		{
    			String[] brands = request.getParameterValues("brands");
    			products = dao.doRetrieveByFiler(brands);
    		}
    		else if (filter.equals("category"))
    		{
    			String category = request.getParameter("categoryName");
    			products = dao.doRetrieveByFilter(category);
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
    	
        /*try 
        {
        	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

            ProductDAO productDAO = new ProductDAO(ds);

            Collection<Product> products = productDAO.doRetrieveAll(null);
            request.setAttribute("products", products);

            request.getRequestDispatcher("/common/catalogue.jsp").forward(request, response);
            return;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ServletException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }*/
    }
}