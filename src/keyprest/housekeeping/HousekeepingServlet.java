
package keyprest.housekeeping;

import java.io.IOException;
import java.sql.PreparedStatement;
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
import keyprest.store.Product;
import keyprest.store.ProductUtils;
import keyprest.user.UserUtils;

@WebServlet(name = "HousekeepingServlet", urlPatterns = {"/housekeeping"})
public class HousekeepingServlet extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/housekeeping/index.jsp");
		
		String SessionKey = (String) session.getAttribute("sessionkey");
		String Action = request.getParameter("action");
		String ProductID = request.getParameter("id");
		String Page = request.getParameter("p");
		
		
		if(SessionKey != null)
		{
			if(UserUtils.isAdmin(SessionKey)) {
				
				int _StartID = 0;
				int _EndID = 0;
				int Product_ID = 0;
				
				try {Product_ID = Integer.parseInt(ProductID); } catch(NumberFormatException e) {}		
				
				setPage(request, session, _StartID, _EndID);
				setProduct(request, session, Product_ID);
				
				if(Action != null)
				{
					switch(Action) {
						default:
							req = request.getRequestDispatcher("/skeletons/pages/housekeeping/index.jsp");
							break;
						case "edit":														
							req = request.getRequestDispatcher("/skeletons/pages/housekeeping/editproduct.jsp");
							break;
						case "add":
							req = request.getRequestDispatcher("/skeletons/pages/housekeeping/createproduct.jsp");
							break;
						case "import":
							req = request.getRequestDispatcher("/skeletons/pages/housekeeping/importkeys.jsp");
							break;
						case "uploadimg":
							req = request.getRequestDispatcher("/skeletons/pages/housekeeping/uploadimage.jsp");
							break;
					}
				} 
			} else { response.sendRedirect("user"); }
		}				

		req.include(request, response);
		
	}
	
	private void setProduct(HttpServletRequest request, HttpSession session, int Product_ID)
	{	
		if(Product_ID <= 0) { Product_ID = 1; } 
		
		Product Current_Product;
		
		try {
			Current_Product = ProductUtils.productByID(Product_ID);
			session.setAttribute("product", Current_Product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setPage(HttpServletRequest request, HttpSession session, int _StartID, int _EndID) {
		int totalProducts = ProductUtils.countProducts();
		
		int totalPages = totalProducts / 10;
		int _Page = 0;
		
		String Page = request.getParameter("p");
		
		if(Page != null) {
			try {_Page = Integer.parseInt(Page); } catch(NumberFormatException e) {}						
		}

		if(_Page <= totalPages)
		{			
			_EndID = _StartID + 10;
			
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
	}
}
