package it.unisa.model;

public class Product 
{
	// Instance variables
	private int code;
	private int quantity;
	private String description;
	private double price;
	private String brand;
	private String model;
	private String category;
	
	
	// Constructors
	
	public Product()
	{
		code = -1;
		quantity = -1;
		description = null;
		price = -1;
		brand = null;
		model = null;
		category = null;
	}
	
	
	// Get methods
	
	public int getCode()
	{
		return code;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getBrand()
	{
		return brand;
	}
	
	public String getModel()
	{
		return model;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	
	// Set methods
	
	public void setCode(int newCode)
	{
		code = newCode;
	}
	
	public void setQuantity(int newQuantity)
	{
		quantity = newQuantity;
	}
	
	public void setDescription(String newDescription)
	{
		description = newDescription;
	}
	
	public void setPrice(double newPrice)
	{
		price = newPrice;
	}
	
	public void setBrand(String newBrand)
	{
		brand = newBrand;
	}
	
	public void setModel(String newModel)
	{
		model = newModel;
	}
	
	public void setCategory(String newCategory)
	{
		category = newCategory;
	}
	
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +code+
			   ", " +quantity+
			   ", " +description+
			   ", " +price+
			   ", " +brand+
			   ", " +model+
			   ", " +category;
	}
}
