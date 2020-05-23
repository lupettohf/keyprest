package keyprest.store;

public class Key {
	
	private int key_id;
	private int product_id;
	private String key;
	private boolean sold;
	
	public Key(int keyId, int productId, String key, boolean sold) {
		this.key_id = keyId;
		this.product_id = productId;
		this.key = key;
		this.sold = sold;
	}
	
	public int getKeyID()
	{
		return this.getKeyID();
	}
	
	public int getProductID()
	{
		return this.product_id;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public boolean IsSold()
	{
		return this.sold;
	}
	
	public void setSold(boolean sold)
	{
		this.sold = sold;
	}
}
