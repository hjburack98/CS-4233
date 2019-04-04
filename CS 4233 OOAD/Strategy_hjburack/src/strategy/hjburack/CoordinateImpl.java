package strategy.hjburack;

import strategy.*;
import strategy.required.*;


public class CoordinateImpl implements Coordinate
{
	private final int x;
	private final int y;
	
	public CoordinateImpl(int x, int y)
	{
		this.x = x; 
		this.y = y;
	}
	
	public CoordinateImpl(Coordinate c)
	{
		this(c.getX(), c.getY());
	}
	
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

}
