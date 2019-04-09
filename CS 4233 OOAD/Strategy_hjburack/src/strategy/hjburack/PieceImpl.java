package strategy.hjburack;

import strategy.Piece;

public class PieceImpl implements Piece
{
	PieceColor color;
	PieceType type;
	int rank;
	
	public PieceImpl(PieceColor color, PieceType type)
	{
		this.color = color;
		this.type = type;
		rank = this.setRank();
	}
	
	
	public static PieceImpl convertPiece(Piece aPiece)
	{
		if(aPiece == null)
		{
			return null;
		}
		return new PieceImpl(aPiece.getPieceColor(), aPiece.getPieceType());
	}

	@Override
	/**
	 * determines if two objects are equal
	 * @param the object being compared
	 * @return true if the object being compared is the same as this object
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Piece))
			return false;
		Piece other = (Piece) obj;
		if (color != other.getPieceColor())
			return false;
		if (type != other.getPieceType())
			return false;
		return true;
	}

	/**
	 * sets the rank number of the piece determined by its type
	 * @return the value of the rank for the proper PieceType
	 */
	private int setRank()
	{
		if(type == PieceType.MARSHAL)
		{
			return 12;
		}
		else if(type == PieceType.COLONEL)
		{
			return 10;
		}
		else if(type == PieceType.CAPTAIN)
		{
			return 8;
		}
		else if(type == PieceType.LIEUTENANT)
		{
			return 7;
		}
		else if(type == PieceType.SERGEANT)
		{
			return 6;
		}
		else 
		{
			return 1;
		}
		
	}
	
	public int getRank()
	{
		return rank;
	}
	
	/**
	 * get the piece color
	 * @return the color of the piece
	 */
	@Override
	public PieceColor getPieceColor()
	{
		return color;
	}
	
	/**
	 * get the piece type
	 * @return the type of the piece
	 */
	@Override
	public PieceType getPieceType()
	{
		return type;
	}

	/**
	 * determine if the object can beat an enemy piece
	 * @param opponent piece
	 * @return true if this piece can beat the opponent 
	 */
	public boolean beatEnemy(PieceImpl opponent)
	{
		if(this.color != opponent.color)
		{
			if(this.rank > opponent.rank)
			{
				return true;
			}
		}
		return false;
	}
}
