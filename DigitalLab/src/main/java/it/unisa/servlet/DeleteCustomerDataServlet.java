/*
 * Questa servlet si occupa di eliminare dal db
 * uno specifico indirizzo di spedizione o 
 * uno specifico metodo di pagamento in base ai valori
 * passati nella query string
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.db.PaymentMethodDAO;
import it.unisa.db.ShippingAddressDAO;
import it.unisa.model.PaymentMethod;
import it.unisa.model.ShippingAddress;

@WebServlet("/DeleteCustomerDataServlet")
public class DeleteCustomerDataServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String shippingAddressIdString = request.getParameter("shippingAddressId");
		String paymentMethodPan = request.getParameter("paymentMethodPan");
		
		String error = "";
		String message = "";
		
		if (shippingAddressIdString != null && !(shippingAddressIdString.equals("")))
		{
			int shippingAddressId = Integer.parseInt(shippingAddressIdString);
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ShippingAddressDAO dao = new ShippingAddressDAO(ds);
			try
			{
				dao.doDelete(shippingAddressId);
			}
			catch(SQLException e)
			{
				System.out.println(e);
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;	
			}
			
			// Rimuovo l'indirizzo di spedizione dalla sessione
			HttpSession session = request.getSession();
			Collection<?> shippingAddresses = (Collection<?>) session.getAttribute("shippingAddresses");
			Iterator<?> it = shippingAddresses.iterator();
			while (it.hasNext())
			{
				ShippingAddress shippingAddress = (ShippingAddress) it.next();
				if (shippingAddress.getId() == shippingAddressId)
					shippingAddresses.remove(shippingAddress);
			}
			
			message += "Salvamenti effettuati con successo";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
			dispatcher.forward(request, response);
			return;	
		}
		else if (paymentMethodPan != null && !(paymentMethodPan.equals("")))
		{
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			PaymentMethodDAO dao = new PaymentMethodDAO(ds);
			try
			{
				dao.doDelete(paymentMethodPan);
			}
			catch(SQLException e)
			{
				System.out.println(e);
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;	
			}
			
			// Rimuovo il metodo di pagamento dalla sessione
			HttpSession session = request.getSession();
			Collection<?> paymentMethods = (Collection<?>) session.getAttribute("paymentMethods");
			Iterator<?> it = paymentMethods.iterator();
			while (it.hasNext())
			{
				PaymentMethod paymentMethod = (PaymentMethod) it.next();
				if (paymentMethod.getPan().equals(paymentMethodPan))
					paymentMethods.remove(paymentMethod);
			}
			
			message += "Salvamenti effettuati con successo";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
			dispatcher.forward(request, response);
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
