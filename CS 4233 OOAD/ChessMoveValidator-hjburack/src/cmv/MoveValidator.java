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
	 * Determines if a move can be made
	 * @param board the board state
	 * @param from the square the piece is moving from
	 * @param to the square the piece is moving to
	 * @return true if the move can be made false otherwise
	 * @throws CMVException if there is an error, such as no piece on the from square
	 */
	public static boolean canMove(ChessBoard board, Square from, Square to)
	{
		throw new MethodNotImplementedException("MoveValidator.canMove");
		/*
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
	} 
	
}
