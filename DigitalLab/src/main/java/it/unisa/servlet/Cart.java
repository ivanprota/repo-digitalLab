package it.unisa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;

import it.unisa.db.ContainsDAO;
import it.unisa.db.ProductDAO;
import it.unisa.model.Contains;
import it.unisa.model.Customer;
import it.unisa.model.Product;

@WebServlet("/Cart")
public class Cart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		String action = request.getParameter("action");
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ContainsDAO containsDAO = new ContainsDAO(ds);
		
		if (action.equals("delete"))
		{
			String productCode = request.getParameter("productCode");
			int code = -1;
			
			if (productCode != null && !productCode.equals(""))
				code = Integer.parseInt(productCode);
			
			try
			{
				containsDAO.doDelete(customer.getUsername(), code);
			}
			catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		else if (action.equals("updateQuantity"))
		{
			Collection<Contains> containsCollection = null;
			
			String quantityString = request.getParameter("quantity");
			int quantity = -1;
			
			if (quantityString != null && !quantityString.equals(""))
				quantity = Integer.parseInt(quantityString);
			
			String productCode = request.getParameter("productCode");
			int code = -1;
			
			if (productCode != null && !productCode.equals(""))
				code = Integer.parseInt(productCode);
			
			String formattedPrice = null;
			try
			{
				containsDAO.doUpdateQuantity(customer.getUsername(), code, quantity);
				containsCollection = containsDAO.doRetrieveAllByKey(customer.getUsername());
				
				double total = 0.00;
			    for (Contains contains : containsCollection) 
			    {
			    	total += contains.getProduct().getPrice() * contains.getQuantity();
			    } 
		    	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		    	formattedPrice = decimalFormat.format(total);
			}
			catch (SQLException e)
			{
				System.err.println(e);
			}
			
			JSONObject json = new JSONObject();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			json.put("formattedPrice", formattedPrice);
			out.print(json.toString());
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/common/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
