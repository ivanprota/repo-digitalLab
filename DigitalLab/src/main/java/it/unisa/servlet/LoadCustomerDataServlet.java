/*
 * Questa servlet carica dal db gli indirizzi di spedizione e
 * i metodi di pagamento dell'utente.
 * Li memorizza all'interno della sessione
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.db.ComposesDAO;
import it.unisa.db.OrderDAO;
import it.unisa.db.PaymentMethodDAO;
import it.unisa.db.ShippingAddressDAO;
import it.unisa.model.Customer;
import it.unisa.model.Order;
import it.unisa.model.PaymentMethod;
import it.unisa.model.Product;
import it.unisa.model.ShippingAddress;

@WebServlet("/LoadCustomerDataServlet")
public class LoadCustomerDataServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		if (customer == null)
		{
			response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
			return;				
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		/* Carico gli indirizzi di spedizione */
		customer = (Customer) session.getAttribute("customer");
		ShippingAddressDAO shippingAddressDAO = new ShippingAddressDAO(ds);
		Collection<ShippingAddress> shippingAddresses = null;
		try
		{
			shippingAddresses = shippingAddressDAO.doRetrieveByCustomerUsername(customer.getUsername());
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.setAttribute("shippingAddresses", shippingAddresses);
		}
		
		//Carico i metodi di pagamento
		PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO(ds);
		Collection<PaymentMethod> paymentMethods = null;
		try
		{
			paymentMethods = paymentMethodDAO.doRetrieveByCustomerUsername(customer.getUsername());
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.setAttribute("paymentMethods", paymentMethods);
		}
		
		// Carico gli ordini
		OrderDAO orderDAO = new OrderDAO(ds);
		Collection<Order> orders = null;
		try
		{
			orders = orderDAO.doRetrieveByCustomerUsername(customer.getUsername());
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.setAttribute("orders", orders);
		}
		
		// Carico la relazione composes per associare ad ogni ordine la lista dei prodotti
		ComposesDAO composesDAO = new ComposesDAO(ds);
		Collection<Collection<Product>> orderProducts = new LinkedList<Collection<Product>>();
		try
		{
			if (orders != null)
			{
				Iterator<?> it = orders.iterator();
				while (it.hasNext())
				{
					Order order = (Order) it.next();
					int orderCode = order.getCode();
					Collection<Product> collection = composesDAO.doRetrieveProductByOrderCode(orderCode);
					orderProducts.add(collection);
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			session.setAttribute("orderProducts", orderProducts);
		}
		
		response.sendRedirect(request.getContextPath() + "/common/user-area.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
