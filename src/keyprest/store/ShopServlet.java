package keyprest.store;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.database.connectionManager;

@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet{

	private static final long serialVersionUID = -3789687023451767232L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		int totalProducts = ProductUtils.countProducts();
		
		int totalPages = totalProducts / 8;
		int _Page = 0;
		
		int _StartID = 0;
		int _EndID = 0;
		
		String Page = request.getParameter("p");
		
		if(Page != null) {
			try {_Page = Integer.parseInt(Page); } catch(NumberFormatException e) {}						
		}

		if(_Page <= totalPages)
		{			
			_EndID = _StartID + 8;
			
			if(_EndID > totalProducts) { _EndID = totalProducts; }
		}
		
		session.setAttribute("totalpages", totalPages);		
		session.setAttribute("curpage", _Page);
		try {
			session.setAttribute("products", ProductUtils.retriveProducts(_StartID, _EndID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block rimuovere
			e.printStackTrace();
		}
			
		req = request.getRequestDispatcher("skeletons/pages/shop.jsp");
			

		req.include(request, response);
	}
}