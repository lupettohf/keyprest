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
import keyprest.store.ProductUtils;
import keyprest.user.UserUtils;

@WebServlet(name = "EditProductServlet", urlPatterns = {"/editproduct"})
public class EditProductServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Product_ID = Integer.parseInt(request.getParameter("product_id"));
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
			if(SessionKey.isEmpty() || !(UserUtils.isAdmin(SessionKey))) { 
				if(ProductUtils.updateProduct(Product_ID, new Product(
						0, 
						Product_name, 
						Product_service,
						Product_description, 
						Product_price, 
						Product_discount, 
						Product_region, 
						Product_isDLC
					))) 
				{session.setAttribute("success", "Prodotto modificato con successo"); } else { session.setAttribute("error", "Si é verificato un errore nella modifica del prodotto.");}		
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch bloc
			e1.printStackTrace();
		} 
		response.sendRedirect("housekeeping");
	}
}
