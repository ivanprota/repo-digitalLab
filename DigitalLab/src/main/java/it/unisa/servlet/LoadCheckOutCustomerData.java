package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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

@WebServlet("/LoadCheckOutCustomerData")
public class LoadCheckOutCustomerData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		if (customer == null) {
			response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
			return;				
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		// Carico gli indirizzi di spedizione
		ShippingAddressDAO shippingAddressDAO = new ShippingAddressDAO(ds);
		Collection<ShippingAddress> shippingAddresses = null;
		try {
			shippingAddresses = shippingAddressDAO.doRetrieveByCustomerUsername(customer.getUsername());
		}
		catch (SQLException e) {
			System.err.println(e);
		}
		finally {
			request.setAttribute("shippingAddresses", shippingAddresses);
		}
		
		//Carico i metodi di pagamento
		PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO(ds);
		Collection<PaymentMethod> paymentMethods = null;
		try {
			paymentMethods = paymentMethodDAO.doRetrieveByCustomerUsername(customer.getUsername());
		}
		catch (SQLException e) {
			System.err.println(e);
		}
		finally {
			request.setAttribute("paymentMethods", paymentMethods);
		}
		
		request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
