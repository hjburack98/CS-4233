package strategy.hjburack;

import strategy.*;
import strategy.required.*;


public class CoordinateImpl implements Coordinate
{
	private int x;
	private int y;
	
	public CoordinateImpl(int x, int y)
	{
		this.x = x; 
		this.y = y;
	}
	
	/*
	public CoordinateImpl(Coordinate c)
	{
		this(c.getX(), c.getY());
	}
	*/
	
	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}
	
	public int setX(int newX)
	{
		this.x = newX;
		return this.x;
	}
	
	public int setY(int newY)
	{
		this.y = newY;
		return this.y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinateImpl other = (CoordinateImpl) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	

}
