package assignment1;

public class Circle implements Shape {
	private int radius;
	private int diameter;
	public Circle(int arg)
	{
		radius = arg;
		diameter = radius * 2;
	}
	@Override
	public double getPerimeter() {
		return Math.PI * diameter;
	}
	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}
	@Override
	public String toString()
	{
		String output = "";
		output += "Circle {r=" + radius + "}  has a perimeter of: " + getPerimeter();
		
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
