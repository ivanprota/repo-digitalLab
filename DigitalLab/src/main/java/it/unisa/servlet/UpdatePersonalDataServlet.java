/*
 * Questa servlet viene invocata dall' area personale
 * dell'utente/admin.
 * Aggiorna i dati personali
 */

package it.unisa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;

import it.unisa.db.AdministratorDAO;
import it.unisa.db.CustomerDAO;
import it.unisa.model.Administrator;
import it.unisa.model.Customer;

@WebServlet("/UpdatePersonalDataServlet")
public class UpdatePersonalDataServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		Administrator admin = (Administrator) session.getAttribute("admin");
		
		if (admin == null && customer == null)
		{
			response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
			return;			
		}
		
		if (admin != null)
		{
			admin = (Administrator) session.getAttribute("admin");
			String name = request.getParameter("adminName");
			String surname = request.getParameter("adminSurname");
			String username = request.getParameter("adminUsername");
			String password = request.getParameter("adminPassword");
			
			if (username == null || username.trim().equals(""))
			{
				int numberForError = 10 / 0;
			}
			
			if (password == null || password.trim().equals(""))
			{
				int numberForError = 10 / 0;
			}
			
			String usernameRegex =  "^\\w+$";
			Pattern pattern = Pattern.compile(usernameRegex);
			Matcher matcher = pattern.matcher(username);
			if (!matcher.find())
			{
				int numberForError = 10 / 0;
			}
			
			String key = admin.getUsername();
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			AdministratorDAO dao = new AdministratorDAO(ds);
			try
			{
				if (!(admin.getName().equals(name)))
				{
					dao.doUpdate(key, "administrator_name", name);
					admin.setName(name);
				}
				if (!(admin.getSurname().equals(surname)))
				{
					dao.doUpdate(key, "administrator_surname", surname);
					admin.setSurname(surname);
				}
				if (!(admin.getUsername().equals(username)))
				{
					dao.doUpdate(key, "administrator_username", username);
					admin.setUsername(username);
				}
				if (!(admin.getPassword().equals(password)))
				{
					dao.doUpdate(key, "administrator_password", password);
					admin.setPassword(password);
				}
			}
			catch (SQLException e)
			{
				System.err.println(e);
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
				return;
			}
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("adminName", admin.getName());
			json.put("adminSurname", admin.getSurname());
			json.put("adminUsername", admin.getUsername());
			json.put("adminPassword", admin.getPassword());
			out.print(json.toString());
	
		}
		else
		{
			customer = (Customer) session.getAttribute("customer");
			String name = request.getParameter("customerName");
			String surname = request.getParameter("customerSurname");
			String phone = request.getParameter("customerPhone");
			String email = request.getParameter("customerEmail");
			String username = request.getParameter("customerUsername");
			String password = request.getParameter("customerPassword");
			
			if (username == null || username.trim().equals(""))
			{
				int numberForError = 10 / 0;
			}
			
			if (password == null || password.trim().equals(""))
			{
				int numberForError = 10 / 0;
			}
			
			password = toHash(password);
			
			Pattern pattern = null;
			Matcher matcher = null;
			
			if (phone != null && !phone.trim().equals(""))
			{
				String phoneRegex = "^\\d{10}$";
				pattern = Pattern.compile(phoneRegex);
				matcher = pattern.matcher(phone);
				if (!matcher.find())
				{
					int numberForError = 10 / 0;
				}
			}
			
			String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
			pattern = Pattern.compile(emailRegex);
			matcher = pattern.matcher(email);
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
			
			String key = customer.getUsername();
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			CustomerDAO dao = new CustomerDAO(ds);
			try
			{
				if (!(customer.getName().equals(name)))
				{
					dao.doUpdate(key, "customer_name", name);
					customer.setName(name);
				}
				if (!(customer.getSurname().equals(surname)))
				{
					dao.doUpdate(key, "customer_surname", surname);
					customer.setSurname(surname);
				}
				if (!(customer.getEmail().equals(email)))
				{
					dao.doUpdate(key, "customer_email", email);
					customer.setEmail(email);
				}
				if (!(customer.getUsername().equals(username)))
				{
					dao.doUpdate(key, "customer_username", username);
					customer.setUsername(username);
				}
				if (!(customer.getPassword().equals(password)))
				{
					dao.doUpdate(key, "customer_password", password);
					customer.setPassword(password);
				}
				
				if (customer.getPhone() != null)
				{
					if (!(customer.getPhone().equals(phone)))
					{
						dao.doUpdate(key, "customer_phone", phone);
						customer.setPhone(phone);
					}	
				}
				else if (customer.getPhone() == null && !(phone.equals("")))
				{
					dao.doUpdate(key, "customer_phone", phone);
					customer.setPhone(phone);
				}
			}
			catch (SQLException e)
			{
				System.err.println(e);
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
				return;
			}
			
			JSONObject json = new JSONObject();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			json.put("name", customer.getName());
			json.put("surname", customer.getSurname());
			json.put("phone", customer.getPhone());
			json.put("email", customer.getEmail());
			json.put("username", customer.getUsername());
			json.put("password", customer.getPassword());
			out.print(json.toString());
		}	
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
