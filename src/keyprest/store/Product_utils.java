package keyprest.store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import keyprest.database.connectionManager;
import keyprest.store.Product;

public class Product_utils {

	/*
	 * Cerca un prodotto tramite l'id e ne restituisce i dati. 
	 */
	public static Product productByID(int product_ID) throws SQLException
	{
		String QUERY = "SELECT * FROM products WHERE product_id = ?";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setInt(1, product_ID);
		
		ResultSet r = preparedStatement.executeQuery();
		
		/* Controlla che vi sia un risultato, altrimenti ritorna nullo. */ 
		if(!r.next()) { return null; }
		
		return new Product(
				r.getInt("product_id"), 
				r.getString("name"),
				r.getInt("service"),
				r.getString("description"),
				r.getFloat("price"),
				r.getInt("discount"),
				r.getString("region"),
				r.getBoolean("is_dlc")
		);
	}
	
	/*
	 * Aggiunge un prodotto.
	 */
	public static boolean addNewProduct(Product product) throws SQLException
	{
		String QUERY = "INSERT INTO products" +
				"(name, service, description, price, discount, region, is_dlc) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		if(product.getName().isEmpty() || product.getDescription().isEmpty() || product.getPrice() <= 0) { return false; }
		
		preparedStatement.setString(1, product.getName());
		preparedStatement.setInt(2, product.getService());
		preparedStatement.setString(3, product.getDescription());
		preparedStatement.setFloat(4, product.getPrice());
		preparedStatement.setInt(5, product.getDiscount());
		preparedStatement.setString(6, product.getRegion());
		preparedStatement.setBoolean(7, product.IsDLC());
	
		if(preparedStatement.executeUpdate() > 0) { return true; } else { return false; } 
	}
	
	/* 
	 * Aggiorna un prodotto preesistente. A transazione avvenuta, restituisce vero. 
	 */
	public static boolean updateProduct(int product_ID, Product newProduct) throws SQLException 
	{
		//TODO: non funziona, errore con i parameti
		String QUERY = "UPDATE products" 
				+ "SET name = '?', service = '?', description = '?', price = '?',  discount = '?', region = '?', is_dlc = '?'"
				+ "WHERE product_id = ?";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, newProduct.getName());
		preparedStatement.setInt(2, newProduct.getService());
		preparedStatement.setString(3, newProduct.getDescription());
		preparedStatement.setFloat(4, newProduct.getPrice());
		preparedStatement.setInt(5, newProduct.getDiscount());
		preparedStatement.setString(6, newProduct.getRegion());
		preparedStatement.setBoolean(7, newProduct.IsDLC());
		preparedStatement.setInt(8, product_ID);
		
		if(preparedStatement.executeUpdate() > 0) { return true; } else { return false; } 
	}

	/*
	 * Aggiunge una chiave di attivazione al prodotto specificato.
	 */
	public static boolean importKey(int product_ID, String Key) throws SQLException
	{
		
		String QUERY = "INSERT INTO `keys` (`product_id`, `key`, `sold`) " +
				"VALUES\n" + 
				"	(?, ?, 0)";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setInt(1, product_ID);
		preparedStatement.setString(2, Key);
		
		if(preparedStatement.executeUpdate() > 0) { return true; } else { return false; } 
	}
	
	/* Crea una lista di prodotti a partire dall'id del primo e scorrendo fino a EndID.
	 *
	 */
	public static ArrayList<Product> retriveProducts(int StartID, int EndID) throws SQLException
	{
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		String QUERY = "SELECT * FROM products WHERE product_id BETWEEN ? AND ?";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setInt(1, StartID);
		preparedStatement.setInt(2, EndID);
		
		ResultSet r = preparedStatement.executeQuery();
		
		while(r.next())
		{
			products.add(new Product(
				r.getInt("product_id"), 
				r.getString("name"),
				r.getInt("service"),
				r.getString("description"),
				r.getFloat("price"),
				r.getInt("discount"),
				r.getString("region"),
				r.getBoolean("is_dlc")
			));
		}
		
		return products;
	}
}

