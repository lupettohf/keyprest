package keyprest.housekeeping;

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
import keyprest.store.Product;
import keyprest.store.Product_utils;
import keyprest.user.User_utils;

@WebServlet(name = "CreateProduct_handler", urlPatterns = {"/createproduct"})
public class CreateProduct_handler extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		try {
			connectionManager.createConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		String SessionKey = (String) session.getAttribute("sessionkey");
		if(SessionKey == null)
		{
			response.sendRedirect("login");
		} else
			try {
				if(User_utils.isAdmin(SessionKey)) {
					response.sendRedirect("housekeeping");
				} else {
					response.sendRedirect("user");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String Product_name = request.getParameter("product_name");
		String Product_description = request.getParameter("product_description");
		int Product_service = Integer.parseInt(request.getParameter("product_service"));
		float Product_price = Float.parseFloat(request.getParameter("product_price"));
		String Product_region = request.getParameter("product_region");
		int Product_discount = Integer.parseInt(request.getParameter("product_discount"));
		boolean Product_isDLC = Boolean.parseBoolean(request.getParameter("product_isdlc"));
	
	
		HttpSession session = request.getSession();
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		//Verifica che l'utente sia loggato e sia admin.
		try {
			if(SessionKey == null || !User_utils.isAdmin(SessionKey)) { return; }
		} catch (SQLException e) {}
	
		try {
			if(Product_utils.addNewProduct(new Product(
					0, 
					Product_name, 
					Product_service,
					Product_description, 
					Product_price, 
					Product_discount, 
					Product_region, 
					Product_isDLC
					))) 
			{ session.setAttribute("success", "Prodotto creato con successo!"); } else { session.setAttribute("error", "Si é verificato un errore nell'aggiunta del prodotto.");}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		response.sendRedirect("housekeeping");
	}

}