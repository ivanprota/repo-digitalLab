package it.unisa.model;

public class Picture 
{
	// Variabili di istanza
	private Product product;
	private byte[] image;
	
	
	// Costruttori
	
	public Picture()
	{
		product = null;
		image = null;
	}
	
	
	// Metodi di accesso
	
	public Product getProduct()
	{
		return product;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	
	// Metodi di modifica
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
	
	public void setImage(byte[] newImage)
	{
		image = newImage;
	}
}
