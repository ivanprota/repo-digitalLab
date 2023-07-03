package it.unisa.model;

public class Picture 
{
	// Instance variables
	private Product product;
	private byte[] image;
	private String imageFileName;
	
	
	// Constructors
	
	public Picture()
	{
		product = null;
		image = null;
		imageFileName = null;
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
	
	public String getImageFileName()
	{
		return imageFileName;
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
	
	public void setImageFileName(String newImageFileName)
	{
		imageFileName = newImageFileName;
	}
	
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +product.toString()+
			   ", " +image+
			   ", " +imageFileName;
	}
}
