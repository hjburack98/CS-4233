package strategy.required;

import strategy.hjburack.PieceImpl;
import strategy.Piece;

public class PieceFactory
{
	public static Piece makePiece(Piece aPiece)
	{
		return new PieceImpl(aPiece.getPieceColor(), aPiece.getPieceType());
	}
	
	
}
