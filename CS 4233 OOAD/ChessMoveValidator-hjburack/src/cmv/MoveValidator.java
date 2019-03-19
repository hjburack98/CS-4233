/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/
package cmv;

import java.lang.Math.*;
import cmv.ChessPiece.PieceColor;
import cmv.ChessPiece.PieceType;

/**
 * The MoveValidator has a single method that takes a ChessBoard instance
 * and two squares. It validates that the piece on the first square can move to
 * the second square on the given board.
 * 
 * Students must implement this method
 * 
 * @version Feb 15, 2019
 */
public class MoveValidator
{
	/**
	 * lambda for checking validity of a rook's move
	 */
	private static Validate rook = (board,from,to) -> 
	{
		boolean verticalMove = validVerticalMove(board, from, to);
		boolean horizontalMove = validHorizontalMove(board, from, to);
		
		if(verticalMove == true || horizontalMove == true)
		{
			return true;
		}
		
		return false;

	};
	
	/**
	 * lambda for checking validity of a knight's move
	 */
	private static Validate knight = (board,from,to) -> 
	{
		
	};
	
	/**
	 * lambda for checking validity of a bishop's move
	 */
	private static Validate bishop = (board,from,to) -> 
	{
		return validDiagonalMove(board, from, to);
	};
	
	/**
	 * lambda for checking validity of a queen's move
	 */
	private static Validate queen = (board,from,to) -> 
	{
		boolean verticalMove = validVerticalMove(board, from, to);
		boolean horizontalMove = validHorizontalMove(board, from, to);
		boolean diagonalMove = validDiagonalMove(board, from, to);
		
		if(verticalMove == true || horizontalMove == true || diagonalMove == true) {
			return true;
		}
		
		return false;
	};
	
	/**
	 * lambda for checking validity of a king's move
	 */
	private static Validate king = (board,from,to) -> 
	{
		boolean verticalMove = validVerticalMove(board, from, to);
		boolean horizontalMove = validHorizontalMove(board, from, to);
		boolean diagonalMove = validDiagonalMove(board, from, to);
		
		if(verticalMove == true || horizontalMove == true || diagonalMove == true) {
			return true;
		}
		
		return false;
	};
	
	/**
	 * lambda for checking validity of a pawn's move
	 */
	private static Validate pawn = (board,from,to) -> 
	{
		if(board.getPieceAt(from).getPieceColor() == PieceColor.WHITE)
		{
			//WHITE cannot have a pawn in row 1
			if(from.getRow() > 1)
			{
				//WHITE pawn can only move forward
				if(from.getColumn() == to.getColumn())
				{
					//make sure the requested move is only one space away
					if(to.getRow() - from.getRow() == 1)
					{
						//can only move forward if the space isnt occupied
						if(!board.isSquareOccupied(to))
						{
							return true;
						}
					}
					//if the pawn is on the starting row, it is able to move up 2 rows if 
					//there is no interference
					else if(from.getRow() == 2)
					{
						for(int start = from.getRow() + 1; start <= start + 1; start++)
						{
							Square interference = SquareFactory.makeSquare(from.getColumn(), start);
							if(!board.isSquareOccupied(interference))
							{
								return true;
							}
						}
					}
				}
				
				// program Diagonal movement for going up right 
				//(pawns will only take movement if the square is occupied by an enemy
				if((int) to.getColumn() - (int) from.getColumn() == to.getRow() - from.getRow())
				{
					//make sure it is only moving diagonal one space
					if(to.getRow() - from.getRow() == 1)
					{
		
						if(board.isSquareOccupied(to))
						{
							return true;
						}
					}
				}
				
				// program Diagonal movement for going up left 
				//(pawns will only take movement if the square is occupied by an enemy
				
				if((int) from.getColumn() - (int) to.getColumn() == to.getRow() - from.getRow())
				{
					if(to.getRow() - from.getRow() == 1)
					{
						if(board.isSquareOccupied(to))
						{
							return true;
						}
					}
				}
			}
		}
		
		else
		{
			//BLACK cannot have a pawn in row 8
			if(from.getRow() < 8)
			{
				//BLACK pawn can only move backward
				if(from.getColumn() == to.getColumn())
				{
					//make sure the requested move is only one space away
					if(from.getColumn() - to.getColumn() == 1)
					{
						//can only move backwards if space isn't occupied
						if(!board.isSquareOccupied(to))
						{
							return true;
						}
					}
					
					//if the pawn is on the starting row, it is able to move up 2 rows if 
					//there is no interference
					else if(from.getRow() == 7)
					{
						for(int start = from.getRow() + 1; start >= start - 1; start--)
						{
							Square interference = SquareFactory.makeSquare(from.getColumn(), start);
							if(!board.isSquareOccupied(interference))
							{
								return true;
							}
						}
					}
				}
				
			}
			
			// program Diagonal movement for going down right 
			// (pawns will only take movement if the square is occupied by an enemy)
			if((int) to.getColumn() - (int) from.getColumn() == from.getRow() - to.getRow())
			{
				//make sure it is only moving diagonal one space
				if(to.getRow() - from.getRow() == 1)
				{
	
					if(board.isSquareOccupied(to))
					{
						return true;
					}
				}
			}
			
			// program Diagonal movement for going down left
			// (Pawns will only take movement if the square is occupied by an enemy)
			if((int) from.getColumn() - (int) to.getColumn() == from.getRow() - to.getRow())
			{
				if(to.getRow() - from.getRow() == 1)
				{
					if(board.isSquareOccupied(to))
					{
						return true;
					}
				}
			}
		}
	};
	

	/**
	 * Determines if a move can be made
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if the move can be made false otherwise
	 * @throws CMVException if there is an error, such as no piece on the from square
	 */
	public static boolean canMove(ChessBoard board, Square from, Square to)
	{
		
		/*
		 * THOUGHT PROCESS / TASK LIST
		 * Steps - MAY INCLUDE STEP 3 IN CLASS FOR EACH TYPE OF PIECE
		 * 1: Determine if the squares are in the board's range. If not, return CMVException that squares not on board
		 * 		THIS IS ALREADY IMPLEMENTED IN board.GetPieceAt(from)
		 * 
		 * 2: Determine if/what piece is in "from" square. Return CMVException if no piece on "from" square
		 * 3: Use the piece's move restrictions to determine if it is able to get to "to" square
		 *    Note - this will also consider other pieces on the board and pieces in the path of the target piece
		 *    
		 *    POTENTIAL METHOD IN EACH CLASS?
		 *       This class would have a function of validMove for each piece. This is where the rules for 
		 *       each type of piece will go. Using this strat will allow for a new class to be made if 
		 *       for some reason a new piece was to be added to the game
		 *     
		 *    POTENTIAL LAMBDA
		 *    	 Create a lambda for each piece that will access an interface with canMove
		 *    	 Each of these lambdas would call from a "Validate" functional interface, 
		 *    	 and be implemented in ActualChessPiece
		 *    	 The function will call validate on the piece that is in square "from"  
		 *    
		 * 4: Return true if the move is valid. Return false otherwise
		 */
		
		//if "from" is not in range, throw and exception
		if(from.getColumn() < 'a' || from.getColumn() > 'h' || from.getRow() < 1 || from.getRow() > 8)
		{
			throw new CMVException("Starting point not on board.");
		}
		
		//if "to" is not in range, throw and exception
		if(to.getColumn() < 'a' || to.getColumn() > 'h' || to.getRow() < 1 || to.getRow() > 8)
		{
			throw new CMVException("Starting point not on board.");
		}

		//If "from" is occupied, check what is in "from" and run appropriate validate
		if(board.isSquareOccupied(from))
		{
			if(board.getPieceAt(from).getPieceType() == PieceType.ROOK)
			{
				rook.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.KNIGHT)
			{
				knight.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.BISHOP)
			{
				bishop.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.QUEEN)
			{
				queen.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.KING)
			{
				king.validate(board, from, to);
			}
			else
			{
				pawn.validate(board, from, to);
			}
		}
		else
		{
			throw new CMVException("Empty square selected");
		}
	}
	
	/**
	 * Determines if a vertical move can be made
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if vertical move can be made
	 */
	public static boolean validVerticalMove(ChessBoard board, Square from, Square to)
	{
		
		//TO MOVE VERTICAL
		//column stays the same, rows change
		if(from.getColumn() == to.getColumn())
		{
			//Make sure the king can only move one space
			if(board.getPieceAt(from).getPieceType() == PieceType.KING)
			{
				if(Math.abs(from.getRow() - to.getRow()) > 1)
				{
					return false;
				}
			}
			
			//for going forwards
			if(from.getRow() < to.getRow()) {
				for(int start = from.getRow() + 1; start <= to.getRow(); start++)
				{
					//check if there are any pieces in the path of the move, if there arent, return true
					Square interference = SquareFactory.makeSquare(from.getColumn(), start);
					if(!board.isSquareOccupied(interference))
					{
						if(start == to.getRow())
						{
							return true;
						}
					}
					
					//if there is a piece at the target square, it is a valid move
					else if(start == to.getRow())
					{
						//makes sure the piece is not on the same team
						if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(from).getPieceColor())
						{
							return true;
						}
					}
				}
			}
			
			//for going backwards
			else
			{
				for(int start = from.getRow() - 1; start >= to.getRow(); start--)
				{
					//check if there are any pieces in the path of the move, if there arent, return true
					Square interference = SquareFactory.makeSquare(from.getColumn(), start);
					if(!board.isSquareOccupied(interference))
					{
						if(start == to.getRow())
						{
							return true;
						}
					}
					
					//if there is a piece at the target square, it is a valid move
					else if(start == to.getRow())
					{
						if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(from).getPieceColor()) 
						{
							return true;
						}
					}
				}
			}
		}
			
		return false;
	}
	
	/**
	 * Determines if a horizontal move can be made
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if horizontal move can be made
	 */
	public static boolean validHorizontalMove(ChessBoard board, Square from, Square to)
	{
		//TO MOVE HORIZONTAL
		//rows stay the same, the column changes
		if(from.getRow() == to.getRow())
		{
			if(board.getPieceAt(from).getPieceType() == PieceType.KING)
			{
				if(Math.abs(from.getColumn() - to.getColumn()) > 1)
				{
					return false;
				}
			}
			//for going forwards
			if(from.getColumn() < to.getColumn()) {
				for(char start = (char) (from.getColumn() + 1); start < to.getColumn(); start++)
				{
					//check if there are any pieces in the path of the move, if there aren't, return true
					Square interference = SquareFactory.makeSquare(start, from.getRow());
					if(!board.isSquareOccupied(interference))
					{
						if(start == to.getColumn())
						{
							return true;
						}
					}
					//if there is interference in the last spot, make sure it is an enemy piece
					else if(start == to.getColumn())
					{
						if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(from).getPieceColor())
						{
							return true;
						}
					}
				}
			}
			
			//for going backwards
			else
			{
				for(char start = (char) (from.getColumn() - 1); start > to.getColumn(); start--)
				{
					//check if there are any pieces in the path of the move, if there arent, return true
					Square interference = SquareFactory.makeSquare(start, from.getRow());
					if(!board.isSquareOccupied(interference))
					{
						if(start == to.getColumn())
						{
							return true;
						}
					}
					//if there is a piece at the target square, it is a valid move
					else if(start == to.getColumn())
					{
						if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(from).getPieceColor())
						{
							return true;
						}
					}
				}
			}
		}
			
		return false;
	}
	
	/**
	 * Determine if a valid diagonal move can be made 
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if diagonal move can be made
	 */
	public static boolean validDiagonalMoves(ChessBoard board, Square from, Square to)
	{
		//King is only allowed to move diagonal one space
		if(board.getPieceAt(from).getPieceType() == PieceType.KING)
		{
			if(from.getColumn() - to.getColumn() > 1 || to.getColumn() - from.getColumn() > 1)
			{
				return false;
			}
		}
	}
	
	/**
	 * Determine if the move requested is diagonal
	 * @param board
	 * @param from
	 * @param to
	 * @return true if the move is a diagonal (meaning the piece's column changes at the same rate of its row
	 */
	public static boolean isDiagonalMove(ChessBoard board, Square from, Square to)
	{
		
	}
	
}
