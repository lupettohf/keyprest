package keyprest.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import keyprest.database.connectionManager;
import keyprest.store.CartItem;

public class OrdersUtils {
	
	public static ArrayList<Order> fetchUserOrders(int user_id)
	{
		String QUERY = "SELECT * FROM orders WHERE user = ?";
		
		try {
			
			if(user_id > 0) {
				PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(QUERY);
		
				preparedStatement.setInt(1, user_id);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				ArrayList<Order> _orders = new ArrayList<Order>();
		
				while(rs.next())
				{
					_orders.add(new Order(
							rs.getInt("order_id"),
							rs.getInt("product_id"),
							rs.getInt("user"),
							rs.getFloat("final_price"),
							rs.getInt("key_id")
					));					
				}
		
				return _orders;
			}
		
		} catch (SQLException e) {return new ArrayList<Order>();}
		
		return new ArrayList<Order>();
	}
	
	public static String getKey(int key_id)
	{
		String QUERY = "SELECT * FROM `keys` WHERE key_id = ?";
		
		try {
			
			if(key_id > 0) {
				PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(QUERY);
				
				preparedStatement.setInt(1, key_id);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					return rs.getString("key");
				}
				
				
			}
		} catch (SQLException e) {return "false";}
		
		return "false";
	}
	
}
