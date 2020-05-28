package keyprest.user;

public class Orders {
	
	private int order_id;
	private int product_id;
	private int user_id;
	private float final_price;
	private int key_id;
	
	public Orders(int order_id, int product_id, int user_id, float final_price, int key_id)
	{
		this.order_id = order_id;
		this.product_id = product_id;
		this.user_id = user_id;
		this.final_price = final_price;
		this.key_id = key_id;
	}
	
	public int getOrderID()
	{
		return this.order_id;
	}
	
	public int getProductID()
	{
		return this.product_id;
	}
	
	public int getUserID()
	{
		return this.user_id;
	}
	
	public float getFinalPrice()
	{
		return this.final_price;
	}
	
	public int getKeyID()
	{
		return this.key_id;
	}
	
}
