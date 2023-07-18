package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.db.ContainsDAO;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.Customer;
import it.unisa.model.ShoppingCart;


@WebServlet("/EmptyCart")
public class EmptyCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		Customer customer = (Customer) session.getAttribute("customer");
		ShoppingCartDAO dao = new ShoppingCartDAO(ds);
		ContainsDAO containsDAO = new ContainsDAO(ds);
		ShoppingCart shoppingCart = null;
		
		try
		{
			shoppingCart = dao.doRetrieveByKey(customer.getUsername());
			dao.doEmptyByKey(shoppingCart.getCustomer().getUsername());
			containsDAO.doDeleteAllByKey(shoppingCart.getCustomer().getUsername());
		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		
		response.sendRedirect(request.getContextPath() + "/common/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
