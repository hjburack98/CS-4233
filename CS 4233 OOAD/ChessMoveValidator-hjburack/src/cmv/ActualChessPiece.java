package cmv;

import java.util.*;
import cmv.ChessPiece.PieceColor;
import cmv.ChessPiece.PieceType;

/**
 * A data structure that contains the color and type of a chess piece
 * 
 * 
 * @version Mar 18, 2019
 */

public class ActualChessPiece implements ChessPiece
{
	public PieceType type;
	public PieceColor color;
	
	public ActualChessPiece(PieceType type, PieceColor color)
	{
		this.type = type;
		this.color = color;
	}
	
	/**
	 * @return the piece type
	 */
	public PieceType getPieceType()
	{
		return this.type;
	}
	
	/**
	 * @return the piece color
	 */
	public PieceColor getPieceColor()
	{
		return this.color;
	}
	
}
