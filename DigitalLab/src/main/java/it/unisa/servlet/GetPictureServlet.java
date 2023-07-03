/*
 * Questa servlet carica l'immagine di un prodotto
 * dal db e la restituisce all'interno della response
 */

package it.unisa.servlet;

import java.io.IOException;
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
import javax.sql.DataSource;

import it.unisa.db.PictureDAO;
import it.unisa.model.Picture;

@WebServlet("/GetPictureServlet")
public class GetPictureServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		PictureDAO dao = new PictureDAO(ds);
		Collection<Picture> pictures = new LinkedList<Picture>();
		try
		{
			for (int code=1; code<=22; code++)
				pictures.add(dao.doRetrieveByKey(code));
		}
		catch (SQLException e)
		{
			System.out.println(e);
			response.sendRedirect(request.getContextPath());
			request.getSession().invalidate();
			return;
		}
		
		request.setAttribute("pictures", pictures);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/common/catalogue.jsp");
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
