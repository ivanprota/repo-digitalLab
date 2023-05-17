package it.unisa.model;

public class Review 
{
	// Variabili di istanza
	private Product product;
	private String description;
	private int assessment;
	
	
	// Costruttori
	
	public Review()
	{
		product = null;
		description = null;
		assessment = -1;
	}
	
	
	// Metodi di accesso
	
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
	
	
	// Metodi di modifica
	
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
