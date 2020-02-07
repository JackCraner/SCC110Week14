
public class Baddies

{
    
	private double xPosition;			
	private double yPosition;			
	private double width;				
	private double height;				
	private String colour;				

										
	public Baddies(double x, double y, double w, double h, String col)
	{
		xPosition = x;
		yPosition = y;
		width = w;
		height = h;
		colour = col;
	}	

    public double getXPosition()
	{
		return xPosition;
	}
	public double getYPosition()
	{
		return yPosition;
	}	
    public void setXPosition(double x)
	{
		this.xPosition = x;
	}	
    public void setYPosition(double y)
	{
		this.yPosition = y;
	}	
    public double getWidth()
	{
		return width;
	}	
    public void setWidth(double w)
	{
		width = w;
	}		
    public double getHeight()
	{
		return height;
	}	
    public void setHeight(double h)
	{
		height = h;
	}
    public String getColour()
	{
		return colour;
	}
    public void setColour(String c)
	{
		colour = c;
	}

	
}


