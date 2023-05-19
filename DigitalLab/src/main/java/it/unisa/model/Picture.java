package it.unisa.model;

public class Picture 
{
	// Instance variables
	private Product product;
	private byte[] image;
	
	
	// Constructors
	
	public Picture()
	{
		product = null;
		image = null;
	}
	
	
	// Get methods
	
	public Product getProduct()
	{
		return product;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	
	// Set methods
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
	
	public void setImage(byte[] newImage)
	{
		image = newImage;
	}
	
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +product.toString()+
			   ", " +image;
	}
}
