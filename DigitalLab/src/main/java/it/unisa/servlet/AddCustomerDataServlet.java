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
			String street = request.getParameter("street");
			String streetNumber = request.getParameter("streetNumber");
			String city = request.getParameter("city");
			String province = request.getParameter("province");
			String zip = request.getParameter("zip");
			
			boolean areGoodValues = true;
			areGoodValues = checkParameter(street);
			areGoodValues = checkParameter(streetNumber);
			areGoodValues = checkParameter(city);
			areGoodValues = checkParameter(province);
			areGoodValues = checkParameter(zip);
			
			if (!areGoodValues)
			{
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;					
			}
			
			try
			{
				shippingAddress.setStreetNumber(Integer.parseInt(streetNumber));
			}
			catch (NumberFormatException e)
			{
				System.err.println(e);
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;				
			}
			
			shippingAddress.setStreet(street);
			shippingAddress.setCity(city);
			shippingAddress.setProvince(province);
			shippingAddress.setZip(zip);
			shippingAddress.setCustomer(customer);
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ShippingAddressDAO dao = new ShippingAddressDAO(ds);
			try
			{
				dao.doSave(shippingAddress);
			}
			
			catch(SQLException e)
			{
				System.err.println(e);
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			Collection<?> shippingAddresses = (Collection<?>) session.getAttribute("shippingAddresses");
			Iterator<?> it = shippingAddresses.iterator();
			Collection<ShippingAddress> collection = new LinkedList<>();
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
			String owner = request.getParameter("owner");
			String pan = request.getParameter("pan");
			String cvv = request.getParameter("cvv");
			String dateString = request.getParameter("expirationDate");
			
			boolean areGoodValues = true;
			areGoodValues = checkParameter(owner);
			areGoodValues = checkParameter(pan);
			areGoodValues = checkParameter(dateString);
			
			if (!areGoodValues)
			{
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;	
			}
			
			paymentMethod.setOwner(owner);
			paymentMethod.setPan(pan);
			paymentMethod.setCvv(cvv);
			paymentMethod.setCustomer(customer);
			
			Date date;
			try
			{
				date = Date.valueOf(dateString);
			}
			catch (IllegalArgumentException e)
			{
				System.err.println(e);
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;			
			}
			
			paymentMethod.setExpirationDate(date);
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			PaymentMethodDAO dao = new PaymentMethodDAO(ds);
			try
			{
				dao.doSave(paymentMethod);
			}
			catch(SQLException e)
			{
				System.err.println(e);
				error += "Impossibile salvare i cambiamenti";
				request.setAttribute("error", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/user-area.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			Collection<?> paymentMethods = (Collection<?>) session.getAttribute("paymentMethods");
			Iterator<?> it = paymentMethods.iterator();
			Collection<PaymentMethod> collection = new LinkedList<>();
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	private boolean checkParameter(String value)
	{
		if (value != null && !value.trim().equals(""))
			return true;
		
		return false;
	}
}
