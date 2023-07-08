/*
 * Questa servlet gestice l'autenticazione da parte
 * dell'utente/admin.
 * Carica i dati dal db e ne verifica la correttezza
 * rispetto ai dati inseriti.
 * Infine memorizza all'interno della sessione
 * tutte le informazioni necessarie.
 */

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

import it.unisa.db.AdministratorDAO;
import it.unisa.db.CustomerDAO;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.Administrator;
import it.unisa.model.Customer;
import it.unisa.model.ShoppingCart;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		/* Controllo se l'utente/admin è già autenticato */
		HttpSession session = request.getSession();
		Administrator admin = (Administrator) session.getAttribute("admin");
		Customer customer = (Customer) session.getAttribute("customer");
		
		if (admin != null)
		{
			response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
			return;
		}
		else if (customer != null)
		{
			response.sendRedirect(request.getContextPath() + "/common/user-area.jsp");
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String error = "";
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		try
		{
			AdministratorDAO adminDAO = new AdministratorDAO(ds);
			admin = adminDAO.doRetrieveByKey(username);
			if (admin.getUsername().equals(username))
			{
				if (admin.getPassword().equals(password))
				{
					session.setAttribute("admin", admin);
					response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
					return;
				}
				else
				{
					if (error.equals(""))
						error += "Username o password errati";
					request.setAttribute("error", error);
					request.getRequestDispatcher("/login-signup/login.jsp").forward(request, response);
					return;
				}
			}
			else 
			{
				if (error.equals(""))
					error += "Username o password errati";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/login-signup/login.jsp").forward(request, response);
				return;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			if (error.equals(""))
				error += "Username o password errati";
			request.setAttribute("error", error);
		}
		
		try
		{
			CustomerDAO customerDAO = new CustomerDAO(ds);
			customer = customerDAO.doRetrieveByKey(username);
			if (customer.getUsername().equals(username))
			{
				if (customer.getPassword().equals(password))
				{			
					session.setAttribute("customer", customer);
					response.sendRedirect(request.getContextPath() + "/common/user-area.jsp");
					return;
				}
				else 
				{
					if (error.equals(""))
						error += "Username o password errati";
					request.setAttribute("error", error);
					request.getRequestDispatcher("/login-signup/login.jsp").forward(request, response);
					return;
				}
			}
			else 
			{
				if (error.equals(""))
					error += "Username o password errati";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/login-signup/login.jsp").forward(request, response);
				return;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			if (error.equals(""))
				error += "Username o password errati";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/login-signup/login.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
