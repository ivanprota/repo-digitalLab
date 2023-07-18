/*
 * Questa servlet controlla se l'utente/admin ha gia effettuato
 * l'accesso in precedenza.
 * Se il controllo ha successo, viene reindirezzato all'area personale.
 * Se il controllo non ha successo, viene reindirezzato alla pagina di login
 */

package it.unisa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.Administrator;
import it.unisa.model.Customer;

@WebServlet("/UserRedirectServlet")
public class UserRedirectServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		Administrator admin = (Administrator) session.getAttribute("admin");
		
		if (customer != null)
		{
			response.sendRedirect(request.getContextPath() + "/common/user-area.jsp");	
		}
		else if (admin != null)
		{
			response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");		
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
