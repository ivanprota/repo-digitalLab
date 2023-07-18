/*
 * Questa servlet si occupa di inserire un nuovo
 * indirizzo di spedizione o un nuovo metodo di pagamento
 * all'interno del db
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

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
import it.unisa.model.Customer;
import it.unisa.model.PaymentMethod;
import it.unisa.model.ShippingAddress;

@WebServlet("/AddCustomerDataServlet")
public class AddCustomerDataServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String isShippingAddress = request.getParameter("shippingAddress");
		String isPaymentMethod = request.getParameter("paymentMethod");
		
		String error = "";
		String message = "";
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		if (isShippingAddress != null && isShippingAddress.equals("true"))
		{
			ShippingAddress shippingAddress = new ShippingAddress();
			shippingAddress.setStreet(request.getParameter("street"));
			shippingAddress.setStreetNumber(Integer.parseInt(request.getParameter("streetNumber")));
			shippingAddress.setCity(request.getParameter("city"));
			shippingAddress.setProvince(request.getParameter("province"));
			shippingAddress.setZip(request.getParameter("zip"));
			shippingAddress.setCustomer(customer);
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ShippingAddressDAO dao = new ShippingAddressDAO(ds);
			try
			{
				dao.doSave(shippingAddress);
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
			
			Collection<?> shippingAddresses = (Collection<?>) session.getAttribute("shippingAddresses");
			Iterator<?> it = shippingAddresses.iterator();
			Collection<ShippingAddress> collection = new LinkedList<ShippingAddress>();
			while (it.hasNext())
			{
				ShippingAddress obj = (ShippingAddress) it.next();
				collection.add(obj);
			}
			collection.add(shippingAddress);
			session.setAttribute("shippingAddresses", collection);
			
			message += "Cambiamenti effettuati con successo";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if (isPaymentMethod != null && isPaymentMethod.equals("true"))
		{
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setOwner(request.getParameter("owner"));
			paymentMethod.setPan(request.getParameter("pan"));
			paymentMethod.setCvv(request.getParameter("cvv"));
			String dateString = request.getParameter("expirationDate");
			Date date = Date.valueOf(dateString);
			paymentMethod.setExpirationDate(date);
			paymentMethod.setCustomer(customer);
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			PaymentMethodDAO dao = new PaymentMethodDAO(ds);
			try
			{
				dao.doSave(paymentMethod);
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
			
			Collection<?> paymentMethods = (Collection<?>) session.getAttribute("paymentMethods");
			Iterator<?> it = paymentMethods.iterator();
			Collection<PaymentMethod> collection = new LinkedList<PaymentMethod>();
			while (it.hasNext())
			{
				PaymentMethod obj = (PaymentMethod) it.next();
				collection.add(obj);
			}
			collection.add(paymentMethod);
			session.setAttribute("paymentMethods", collection);
			
			message += "Cambiamenti effettuati con successo";
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
