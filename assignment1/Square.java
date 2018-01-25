package assignment1;

public class Square implements Shape
{
	private int length;
	public Square(int arg)
	{
		length = arg;
	}
	@Override
	public double getPerimeter() {
		return length * 4;
	}
	@Override
	public double getArea() {
		return Math.pow(length, 2);
	}
	@Override
	public String toString()
	{
		String output = "";
		output += "Square {len=" + length + "}  has a perimeter of: " + getPerimeter();
		
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
