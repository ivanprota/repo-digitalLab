package it.unisa.model;

public class Review 
{
	// Instance variables
	private Product product;
	private String description;
	private int assessment;
	
	
	// Constructors
	
	public Review()
	{
		product = null;
		description = null;
		assessment = -1;
	}
	
	
	// Get methods
	
	public Product getProduct()
	{
		return product;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getAssessment()
	{
		return assessment;
	}
	
	
	// Set methods
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
	
	public void setDescription(String newDescription)
	{
		description = newDescription;
	}
	
	public void setAssessment(int newAssessment)
	{
		assessment = newAssessment;
	}
}
