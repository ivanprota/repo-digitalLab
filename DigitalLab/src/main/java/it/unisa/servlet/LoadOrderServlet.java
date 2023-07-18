/*
 * Questa servlet viene chiamata all'interno dell'area
 * amministrativa.
 * Memorizza una lista di ordini all'interno della request
 * in base ai valori passati nella form
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.OrderDAO;
import it.unisa.model.Order;

@WebServlet("/LoadOrderServlet")
public class LoadOrderServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");
		
		if (!(username.equals("")) && !(startDateString.equals("")) && !(endDateString.equals("")))
		{
			java.util.Date utilStartDate = null;
			java.util.Date utilEndDate = null;
			
			try 
			{
				utilStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
				utilEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
				
			} 
			catch (ParseException e) 
			{
				System.err.println(e);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
				dispatcher.forward(request, response);
			}
			
			Date startDate = new Date(utilStartDate.getTime());
			Date endDate = new Date(utilEndDate.getTime());
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			OrderDAO dao = new OrderDAO(ds);
			Collection<Order> ordersCollection = null;
			Collection<Order> orders = new LinkedList<Order>();
			
			try
			{
				ordersCollection = dao.doRetrieveByCustomerUsername(username);
			}
			catch(SQLException e)
			{
				System.err.println(e);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
				dispatcher.forward(request, response);
			}
			
			Iterator<Order> it = ordersCollection.iterator();
			while(it.hasNext())
			{
				Order order = (Order) it.next();
				Date orderDate = order.getPaymentDate();
				if ((orderDate.compareTo(startDate) >= 0) && (orderDate.compareTo(endDate) <= 0))
					orders.add(order);
			}
			
			request.setAttribute("orders", orders);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
			dispatcher.forward(request, response);
		}
		else if (username.equals("") && !(startDateString.equals("")) && !(endDateString.equals("")))
		{
			java.util.Date utilStartDate = null;
			java.util.Date utilEndDate = null;
			
			try 
			{
				utilStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
				utilEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
			} 
			catch (ParseException e) 
			{
				System.err.println(e);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
				dispatcher.forward(request, response);
			}
			
			Date startDate = new Date(utilStartDate.getTime());
			Date endDate = new Date(utilEndDate.getTime());
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			OrderDAO dao = new OrderDAO(ds);
			Collection<Order> orders = null;
			
			try
			{
				orders = dao.doRetrieveByDateRange(startDate, endDate);
			}
			catch (SQLException e)
			{
				System.err.println(e);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
				dispatcher.forward(request, response);				
			}
			
			request.setAttribute("orders", orders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
			dispatcher.forward(request, response);
		}
		else if (!(username.equals("")) && startDateString.equals("") && endDateString.equals(""))
		{
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			OrderDAO dao = new OrderDAO(ds);
			Collection<Order> orders = null;
			
			try
			{
				orders = dao.doRetrieveByCustomerUsername(username);
			}
			catch(SQLException e)
			{
				System.err.println(e);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
				dispatcher.forward(request, response);
			}
			
			request.setAttribute("orders", orders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-area.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
