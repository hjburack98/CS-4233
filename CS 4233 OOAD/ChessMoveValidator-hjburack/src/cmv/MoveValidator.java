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
		//check two rows up
		if(from.getRow() + 2 == to.getRow())
		{
			//check one row right
			if(from.getColumn() + 1 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
			//check one row left
			if(from.getColumn() - 1 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
		}

		//check one row up
		if(from.getRow() + 1 == to.getRow())
		{
			//check two rows right
			if(from.getColumn() + 2 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
			//check two rows left
			if(from.getColumn() - 2 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
		}
		
		//check two rows down
		if(from.getRow() - 2 == to.getRow())
		{
			//check one row right
			if(from.getColumn() + 1 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
			//check one row left
			if(from.getColumn() - 1 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
		}
		//check one row down and two rows right, one row down and two rows left
		//Check one row down
		if(from.getRow() + 1 == to.getRow())
		{
			//check two rows right
			if(from.getColumn() + 2 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
			//check two rows left
			if(from.getColumn() - 2 == to.getColumn())
			{
				if(!board.isSquareOccupied(to))
				{
					return true;
				}
				else if(board.getPieceAt(to).getPieceColor() != board.getPieceAt(to).getPieceColor())
				{
					return true;
				}
			}
			
		}
		
		return false;
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
		
		return(verticalMove || horizontalMove || diagonalMove);
	};
	
	/**
	 * lambda for checking validity of a king's move
	 */
	private static Validate king = (board,from,to) -> 
	{
		if(Math.abs(from.getRow() - to.getRow()) > 1 && Math.abs(from.getColumn() - to.getColumn()) > 1)
			{
				return false;
			}
		
		boolean verticalMove = validVerticalMove(board, from, to);
		boolean horizontalMove = validHorizontalMove(board, from, to);
		boolean diagonalMove = validDiagonalMove(board, from, to);
		
		return(verticalMove || horizontalMove || diagonalMove);
	};
	
	/**
	 * lambda for checking validity of a pawn's move
	 */
	private static Validate pawn = (board,from,to) -> 
	{
		if(board.getPieceAt(from).getPieceColor() == PieceColor.WHITE)
		{
			if(from.getRow() == 1)
			{
				return false;
			}
			
			if(from.getRow() + 1 == to.getRow() && (from.getColumn() + 1 == to.getColumn() || from.getColumn() - 1 == to.getColumn()))
			{
				if(board.isSquareOccupied(to) && board.getPieceAt(to).getPieceColor() == PieceColor.BLACK)
				{
					return true;
				}
			}
			
			if(from.getRow() == 2)
			{
				if(from.getColumn() == to.getColumn() &&(from.getRow() + 1 == to.getRow() || from.getRow() + 2 == to.getRow()))
				{
					return true;
				}
			}
			else
			{
				if(from.getColumn() == to.getColumn() && from.getRow() + 1 == to.getRow())
				{
					if(!board.isSquareOccupied(to))
					{
						return true;
					}
				}
			}
			
		}
		
		else
		{
			//cant go beyond 8
			if(from.getRow() == 8)
			{
				return false;
			}
			
			if(from.getRow() - 1 == to.getRow() && (from.getColumn() + 1 == to.getColumn() || from.getColumn() - 1 == to.getColumn()))
			{
				if(board.isSquareOccupied(to) && board.getPieceAt(to).getPieceColor() == PieceColor.WHITE)
				{
					return true;
				}
			}
			
			if(from.getRow() == 2)
			{
				if(from.getColumn() == to.getColumn() &&(from.getRow() - 1 == to.getRow() || from.getRow() - 2 == to.getRow()))
				{
					return true;
				}
			}
			else
			{
				if(from.getColumn() == to.getColumn() && from.getRow() - 1 == to.getRow())
				{
					if(!board.isSquareOccupied(to))
					{
						return true;
					}
				}
			}
		}
		return false;
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
			throw new CMVException("Ending point not on board.");
		}
		
		//if "from" and "to" are the same square, throw exception
		if(to.getColumn() == from.getColumn() && to.getRow() == from.getRow())
		{
			throw new CMVException("Starting and ending points are at the same square");
		}

		//If "from" is occupied, check what is in "from" and run appropriate validate
		if(board.isSquareOccupied(from))
		{
			if(board.getPieceAt(from).getPieceType() == PieceType.ROOK)
			{
				return rook.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.KNIGHT)
			{
				return knight.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.BISHOP)
			{
				return bishop.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.QUEEN)
			{
				return queen.validate(board, from, to);
			}
			else if(board.getPieceAt(from).getPieceType() == PieceType.KING)
			{
				return king.validate(board, from, to);
			}
			else
			{
				return pawn.validate(board, from, to);
			}
		}
		else
		{
			return false;
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
		return (validVerticalUpMove(board, from, to) || validVerticalDownMove(board, from, to));
	}
	
	public static boolean validVerticalUpMove(ChessBoard board, Square from, Square to)
	{
		//TO MOVE VERTICAL
		//column stays the same, rows change
		if(from.getColumn() == to.getColumn())
		{	
			//for going up
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
					else
					{
						return false;
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean validVerticalDownMove(ChessBoard board, Square from, Square to)
	{
		//TO MOVE VERTICAL
		//column stays the same, rows change
		if(from.getColumn() == to.getColumn())
		{
			//for going down
			if(from.getRow() > to.getRow())
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
					else
					{
						return false;
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
		return (validHorizontalRightMove(board, from, to) || validHorizontalLeftMove(board, from, to));
	}
	
	public static boolean validHorizontalRightMove(ChessBoard board, Square from, Square to)
	{
		//TO MOVE HORIZONTAL
		//rows stay the same, the column changes
		if(from.getRow() == to.getRow())
			
			//for going forwards
			if(from.getColumn() < to.getColumn()) {
				for(char start = (char) (from.getColumn() + 1); start <= to.getColumn(); start++)
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
					else
					{
						return false;
					}
				}
			}
		return false;
	}
	
	public static boolean validHorizontalLeftMove(ChessBoard board, Square from, Square to)
	{
		//TO MOVE HORIZONTAL
		//rows stay the same, the columns change
		if(from.getRow() == to.getRow())
		{
			//for going left
			if(from.getColumn() > to.getColumn())
			{
				for(char start = (char) (from.getColumn() - 1); start >= to.getColumn(); start--)
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
					else
					{
						return false;
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
	public static boolean validDiagonalMove(ChessBoard board, Square from, Square to)
	{
		return (validDiagonalUpMove(board, from, to) || validDiagonalDownMove(board, from, to) || validDiagonalDownRight(board, from, to) || validDiagonalDownLeft(board, from, to));
	}
	
	/**
	 * Determine if a valid diagonal up move can be made
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if diagonal up move can be made
	 */
	public static boolean validDiagonalUpMove(ChessBoard board, Square from, Square to)
	{
		return (validDiagonalUpRight(board, from, to) || validDiagonalUpLeft(board, from, to));
	}
	
	/**
	 * Determine if a valid diagonal down move can be made
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if diagonal down move can be made
	 */
	public static boolean validDiagonalDownMove(ChessBoard board, Square from, Square to)
	{
		return (validDiagonalDownRight(board, from, to) || validDiagonalDownLeft(board, from, to));
	}
	
	
	
	/**
	 * Determine if the move requested is a valid diagonal move up right
	 * @param board
	 * @param from
	 * @param to
	 * @return true if diagonal up right move can be made
	 */
	public static boolean validDiagonalUpRight(ChessBoard board, Square from, Square to)
	{
		if((int) to.getColumn() - (int) from.getColumn() == to.getRow() - from.getRow())
		{		
			//values to test if squares in the path of the move are taken
			char incrementCol = (char) (from.getColumn() + 1);
			int incrementRow = from.getRow() + 1;
			
			//used while loop to increment both col and row at the same time
			while(incrementCol <= to.getColumn() && incrementRow <= to.getRow())
			{
				Square interference = SquareFactory.makeSquare(incrementCol, incrementRow);
				//if the spot is empty, check if it is the target spot
				if(!board.isSquareOccupied(interference))
				{
					//return true if target spot
					if(incrementCol == to.getColumn() && incrementRow == to.getRow())
					{
						return true;
					}
				}
				//if the spot isn't empty but is at the target spot, check to make sure the piece in target is an enemy piece
				else if (incrementCol == to.getColumn() && incrementRow == to.getRow())
				{
					//if the spot is an enemy piece, return true
					if(board.getPieceAt(interference).getPieceColor() != board.getPieceAt(from).getPieceColor())
					{
						return true;
					}
				}
				
				incrementCol ++;
				incrementRow ++;
			}
		}
		return false;
	}
	
	/**
	 * Determine if the move requested is diagonal up left
	 * @param board
	 * @param from
	 * @param to
	 * @return true diagonal up left move can be made
	 */
	public static boolean validDiagonalUpLeft(ChessBoard board, Square from, Square to)
	{
		if((int) from.getColumn() - (int) to.getColumn() == to.getRow() - from.getRow())
		{	
			//values to test if squares in the path of the move are taken
			char incrementCol = (char) (from.getColumn() - 1);
			int incrementRow = from.getRow() + 1;
			
			//used while loop to increment both col and row at the same time
			while(incrementCol >= to.getColumn() && incrementRow <= to.getRow())
			{
				Square interference = SquareFactory.makeSquare(incrementCol, incrementRow);
				//if the spot is empty, check if it is the target spot
				if(!board.isSquareOccupied(interference))
				{
					//return true if target spot
					if(incrementCol == to.getColumn() && incrementRow == to.getRow())
					{
						return true;
					}
				}
				//if the spot isn't empty but is at the target spot, check to make sure the piece in target is an enemy piece
				else if (incrementCol == to.getColumn() && incrementRow == to.getRow())
				{
					//if the spot is an enemy piece, return true
					if(board.getPieceAt(interference).getPieceColor() != board.getPieceAt(from).getPieceColor())
					{
						return true;
					}
				}
				
				incrementCol --;
				incrementRow ++;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Determine if the move requested is diagonal down right
	 * @param board
	 * @param from
	 * @param to
	 * @return true if diagonal down right move can be made
	 */
	public static boolean validDiagonalDownRight(ChessBoard board, Square from, Square to)
	{
		if((int) to.getColumn() - (int) from.getColumn() == from.getRow() - to.getRow())
		{
			//values to test if squares in the path of the move are taken
			char incrementCol = (char) (from.getColumn() + 1);
			int incrementRow = from.getRow() - 1;
			
			//used while loop to increment both col and row at the same time
			while(incrementCol <= to.getColumn() && incrementRow >= to.getRow())
			{
				Square interference = SquareFactory.makeSquare(incrementCol, incrementRow);
				//if the spot is empty, check if it is the target spot
				if(!board.isSquareOccupied(interference))
				{
					//return true if target spot
					if(incrementCol == to.getColumn() && incrementRow == to.getRow())
					{
						return true;
					}
				}
				//if the spot isn't empty but is at the target spot, check to make sure the piece in target is an enemy piece
				else if (incrementCol == to.getColumn() && incrementRow == to.getRow())
				{
					//if the spot is an enemy piece, return true
					if(board.getPieceAt(interference).getPieceColor() != board.getPieceAt(from).getPieceColor())
					{
						return true;
					}
				}
				
				incrementCol ++;
				incrementRow --;
			}
		}
		
		return false;
	}
	
	/**
	 * Determine if the move requested is diagonal down left
	 * @param board
	 * @param from
	 * @param to
	 * @return true if diagonal down left move can be made
	 */
	public static boolean validDiagonalDownLeft(ChessBoard board, Square from, Square to)
	{
		if((int) from.getColumn() - (int) to.getColumn() == from.getRow() - to.getRow())
		{
			//values to test if squares in the path of the move are taken
			char incrementCol = (char) (from.getColumn() - 1);
			int incrementRow = from.getRow() - 1;
			
			//used while loop to increment both col and row at the same time
			while(incrementCol >= to.getColumn() && incrementRow >= to.getRow())
			{
				Square interference = SquareFactory.makeSquare(incrementCol, incrementRow);
				//if the spot is empty, check if it is the target spot
				if(!board.isSquareOccupied(interference))
				{
					//return true if target spot
					if(incrementCol == to.getColumn() && incrementRow == to.getRow())
					{
						return true;
					}
				}
				//if the spot isn't empty but is at the target spot, check to make sure the piece in target is an enemy piece
				else if (incrementCol == to.getColumn() && incrementRow == to.getRow())
				{
					//if the spot is an enemy piece, return true
					if(board.getPieceAt(interference).getPieceColor() != board.getPieceAt(from).getPieceColor())
					{
						return true;
					}
				}
				
				incrementCol --;
				incrementRow --;
			}
		}
		
		return false;
	}
	
}
