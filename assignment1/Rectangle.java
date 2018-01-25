package assignment1;

public class Rectangle implements Shape 
{
	private int parallel1;
	private int parallel2;
	
	public Rectangle(int arg, int arg2)
	{
		parallel1 = arg * 2;
		parallel2 = arg2 * 2;
	}

	@Override
	public double getPerimeter() {
		return parallel1 + parallel2;
	}

	@Override
	public double getArea() {
		return (parallel1/2) * (parallel2/2);
	}
	@Override
	public String toString()
	{	
		String output = "";
		output += "Rectangle {len=" + parallel1/2 + " and wid=" + parallel2/2 + "}  has a perimeter of: " + getPerimeter();
		
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
