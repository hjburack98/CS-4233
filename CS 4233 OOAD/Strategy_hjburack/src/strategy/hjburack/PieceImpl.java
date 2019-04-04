package strategy.hjburack;

import strategy.Piece;

public class PieceImpl implements Piece
{
	PieceColor color;
	PieceType type;
	
	public PieceImpl(PieceColor color, PieceType type)
	{
		this.color = color;
		this.type = type;
	}
	
	@Override
	public PieceColor getPieceColor()
	{
		return this.color;
	}
	
	@Override
	public PieceType getPieceType()
	{
		return this.type;
	}
}
