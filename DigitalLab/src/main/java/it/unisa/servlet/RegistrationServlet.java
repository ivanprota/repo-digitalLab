/*
 * Questa servlet si occupa della registrazione
 * dell'utente
 */

package it.unisa.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.CustomerDAO;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.Customer;
import it.unisa.model.ShoppingCart;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		password = toHash(password);
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setSurname(surname);
		customer.setEmail(email);
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setPhone(null);
		
		String error = "";
		
		String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		try
		{
			if (!matcher.find())
			{
				int numberForError = 10 / 0;
			}
			
			String usernameRegex =  "^\\w+$";
			pattern = Pattern.compile(usernameRegex);
			matcher = pattern.matcher(username);
			if (!matcher.find())
			{
				int numberForError = 10 / 0;
			}
		}
		catch (ArithmeticException e)
		{
			System.err.println(e);
			error += "Registrazione fallita";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login-signup/signup.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CustomerDAO dao = new CustomerDAO(ds);
		try
		{
			dao.doSave(customer);
		}
		catch (SQLException e)
		{
			System.err.println(e);
			response.sendRedirect(request.getContextPath() + "/login-signup/signup.jsp");
			return;
		}
		
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO(ds);
		ShoppingCart shoppingCart = new ShoppingCart();
		
		try
		{
			customer = dao.doRetrieveByKey(username);
			
			shoppingCart.setCustomer(customer);
			shoppingCart.setSize(0);
			shoppingCartDAO.doSave(shoppingCart);
		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
		
		response.sendRedirect(request.getContextPath() + "/common/user-area.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

	private String toHash(String password)
	{
		String hashString = null;
		try
		{
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i=0; i<hash.length; i++)
			{
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3);
			}
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
			System.err.println(e);
		}
		
		return hashString;
	}
}
