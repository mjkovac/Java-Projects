package assignment1;

public class Triangle implements Shape
{
	private int side1;
	private int side2;
	private int side3;
	
	public Triangle(int arg, int arg2, int arg3)
	{
		side1 = arg;
		side2 = arg2;
		side3 = arg3;
	}

	@Override
	public double getPerimeter() 
	{
		return side1 + side2 + side3;
	}

	@Override
	public double getArea() 
	{
		double pOverTwo = getPerimeter() / 2.0;
		return ( (side1+side2) <= side3 ) ? -1 : Math.sqrt((pOverTwo*(pOverTwo-side1)*(pOverTwo-side2)*(pOverTwo-side3)));
	}
	
	@Override
	public String toString()
	{
		String output = "";
		output += "Triangle {len=" + side1 + "," + side2 + "," + side3 + "}  has a perimeter of: " + getPerimeter();
		
		return output;
	}
	@Override
	public int hashCode()
	{
		return 0;
	}
	@Override
	public boolean equals(Object obj)
	{
		return false;
	}
}
