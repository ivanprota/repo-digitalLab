/*
 * Questa servlet si occupa della registrazione
 * dell'utente
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.CustomerDAO;
import it.unisa.model.Customer;

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
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setSurname(surname);
		customer.setEmail(email);
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setPhone(null);
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CustomerDAO dao = new CustomerDAO(ds);
		try
		{
			dao.doSave(customer);
		}
		catch (SQLException e)
		{
			System.out.println(e);
			response.sendRedirect(request.getContextPath() + "/login-signup/signup.jsp");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/common/user-area.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
