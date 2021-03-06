/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cmv;

import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static cmv.SquareFactory.makeSquare;
import static cmv.MoveValidator.canMove;
import static cmv.ChessPiece.PieceColor.*;
import static cmv.ChessPiece.PieceType.*;
import static cmv.ChessPieceFactory.*;

/**
 * This is a sample of the type of tests that will be run on your code. You should make sure
 * that these run on your assignment code before submitting your work.
 * @version Mar 9, 2019
 */
class ValidationTest
{
	@ParameterizedTest
	@MethodSource("testCaseProvider")
	void validationTest(ChessBoard board, Square source, Square target, boolean expected)
	{
		assertEquals(expected, canMove(board, source, target));
	}
	
	static Stream<Arguments> testCaseProvider()
	{
		return Stream.of(
			
			// Rook tests
			//vertical
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK)), 
					makeSquare('h', 1), makeSquare('e', 3), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK), makeSquare('h', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), false) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(WHITE, ROOK), makeSquare('h', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(BLACK, ROOK), makeSquare('h', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), true) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(BLACK, ROOK), makeSquare('h', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), false) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(BLACK, ROOK), makeSquare('h', 1), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), true) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(BLACK, ROOK), makeSquare('h', 1), makePiece(BLACK, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(BLACK, ROOK), makeSquare('h', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK)), 
					makeSquare('h', 1), makeSquare('h', 3), true) ,
			Arguments.of(makeBoard(makeSquare('h', 3), makePiece(WHITE, ROOK)), 
					makeSquare('h', 3), makeSquare('h', 1), true) ,
			
			//Horizontal
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, ROOK)), 
					makeSquare('e', 4), makeSquare('h', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, ROOK)), 
					makeSquare('e', 4), makeSquare('a', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, ROOK), makeSquare('a', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('a', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, ROOK), makeSquare('h', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, ROOK), makeSquare('h', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 4), false) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, ROOK), makeSquare('a', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('a', 4), false) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, ROOK), makeSquare('c', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('a', 4), false) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, ROOK), makeSquare('f', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 4), false) ,
			
			//Diagonal Fails
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, ROOK)), 
					makeSquare('e', 3), makeSquare('h', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, ROOK)), 
					makeSquare('e', 3), makeSquare('g', 1), false),
			
			// Bishop tests
			Arguments.of(makeBoard(makeSquare('c', 1), makePiece(BLACK, BISHOP)), 
					makeSquare('c', 1), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('c', 3), makePiece(BLACK, BISHOP)), 
					makeSquare('c', 3), makeSquare('e', 1), true),
			Arguments.of(makeBoard(makeSquare('c', 3), makePiece(BLACK, BISHOP)), 
					makeSquare('c', 3), makeSquare('a', 1), true),
			Arguments.of(makeBoard(makeSquare('c', 3), makePiece(BLACK, BISHOP)), 
					makeSquare('c', 3), makeSquare('a', 5), true),
			
				//not diagonal moves
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('h', 1), false),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('a', 1), false),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('h', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('a', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('h', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('h', 7), false),
			
				//Diagonal interference fail
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, BISHOP), makeSquare('f', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 7), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, BISHOP), makeSquare('d', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('b', 7), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, BISHOP), makeSquare('f', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 1), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, BISHOP), makeSquare('d', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 2), false),
			
				//Diagonal target square has same color piece
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, BISHOP), makeSquare('g', 6), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, BISHOP), makeSquare('c', 6), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, BISHOP), makeSquare('g', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 2), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, BISHOP), makeSquare('c', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 2), false),
			
				//Diagonal take enemy piece
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, BISHOP), makeSquare('g', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, BISHOP), makeSquare('c', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, BISHOP), makeSquare('g', 2), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 2), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, BISHOP), makeSquare('c', 2), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 2), true),
			
			// Queen tests
				//vertical
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, QUEEN)), 
					makeSquare('h', 1), makeSquare('e', 3), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, QUEEN), makeSquare('h', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), false) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(WHITE, QUEEN), makeSquare('h', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(BLACK, QUEEN), makeSquare('h', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), true) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(BLACK, QUEEN), makeSquare('h', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), false) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(BLACK, QUEEN), makeSquare('h', 1), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), true) ,
			Arguments.of(makeBoard(makeSquare('h', 4), makePiece(BLACK, QUEEN), makeSquare('h', 1), makePiece(BLACK, KNIGHT)), 
					makeSquare('h', 4), makeSquare('h', 1), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(BLACK, QUEEN), makeSquare('h', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), false) ,
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, QUEEN)), 
					makeSquare('h', 1), makeSquare('h', 3), true) ,
			Arguments.of(makeBoard(makeSquare('h', 3), makePiece(WHITE, QUEEN)), 
					makeSquare('h', 3), makeSquare('h', 1), true) ,
			
				//Horizontal
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), 
					makeSquare('e', 4), makeSquare('h', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), 
					makeSquare('e', 4), makeSquare('a', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('a', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('a', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('h', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 4), true) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('h', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 4), false) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('a', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('a', 4), false) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('c', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('a', 4), false) ,
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('f', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 4), false) ,
			
				//Diagonal moves
			Arguments.of(makeBoard(makeSquare('c', 1), makePiece(BLACK, QUEEN)), 
					makeSquare('c', 1), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('c', 3), makePiece(BLACK, QUEEN)), 
					makeSquare('c', 3), makeSquare('e', 1), true),
			Arguments.of(makeBoard(makeSquare('c', 3), makePiece(BLACK, QUEEN)), 
					makeSquare('c', 3), makeSquare('a', 1), true),
			Arguments.of(makeBoard(makeSquare('c', 3), makePiece(BLACK, QUEEN)), 
					makeSquare('c', 3), makeSquare('a', 5), true),
			
				//Diagonal interference fail
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('f', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 7), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('d', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('b', 7), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN), makeSquare('f', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 1), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN), makeSquare('f', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('h', 7), false),
			
				//Diagonal target square has same color piece
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('g', 6), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('c', 6), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN), makeSquare('g', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 2), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN), makeSquare('c', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 2), false),
			
				//Diagonal take enemy piece
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('g', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, QUEEN), makeSquare('c', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN), makeSquare('g', 2), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 2), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN), makeSquare('c', 2), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 2), true),
			
			// King tests
				//Test valid movements
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('f', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('f', 4), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('f', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('d', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('d', 4), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('d', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('e', 5), true),
			
				//Take enemy in moved space
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('f', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('f', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 4), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('f', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('e', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('d', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('d', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 4), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('d', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('e', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('e', 5), true),
			
				//invalid moves
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('g', 7), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('g', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('g', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING)), 
					makeSquare('e', 4), makeSquare('e', 2), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('c', 2), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('c', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('c', 1), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING)), 
					makeSquare('e', 4), makeSquare('e', 6), false),
			
				//can't take piece of the same color
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('f', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('f', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('f', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KING), makeSquare('e', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('e', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('d', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('d', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('d', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KING), makeSquare('e', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('e', 5), false),
			
			// Knight tests
				//valid movements
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 2), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 2), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 3), true),
			
				//invalid moves
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 2), false),
			
				//interference moves
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('f', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('d', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('g', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('c', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('f', 2), makePiece(BLACK, KNIGHT)),
					makeSquare('e', 4), makeSquare('f', 2), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('d', 2), makePiece(BLACK, KNIGHT)),
					makeSquare('e', 4), makeSquare('d', 2), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('g', 3), makePiece(BLACK, KNIGHT)),
					makeSquare('e', 4), makeSquare('g', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('c', 3), makePiece(BLACK, KNIGHT)),
					makeSquare('e', 4), makeSquare('c', 3), true),
			
				//Can't take piece of the same color
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('f', 6), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('d', 6), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 6), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('g', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('g', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, KNIGHT), makeSquare('c', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('c', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('f', 2), makePiece(WHITE, KNIGHT)),
					makeSquare('e', 4), makeSquare('f', 2), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('d', 2), makePiece(WHITE, KNIGHT)),
					makeSquare('e', 4), makeSquare('d', 2), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('g', 3), makePiece(WHITE, KNIGHT)),
					makeSquare('e', 4), makeSquare('g', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, KNIGHT), makeSquare('c', 3), makePiece(WHITE, KNIGHT)),
					makeSquare('e', 4), makeSquare('c', 3), false),
			
			// Pawn tests
				//standard move forward
					//white
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('e', 5), true),
						//white can't go backwards or sideways ever, or diagonal without an enemy there
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('e', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('f', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('f', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('d', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('f', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN)), 
					makeSquare('e', 4), makeSquare('d', 3), false),
			
					//black
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('e', 3), true),
						//black cannot go forwards or sideways, or diagonal without an enemy there
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('e', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('f', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('f', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('d', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('f', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN)), 
					makeSquare('e', 4), makeSquare('d', 3), false),
			
				//move forward from initial space (row = 2)
					//white
			Arguments.of(makeBoard(makeSquare('e', 2), makePiece(WHITE, PAWN)), 
					makeSquare('e', 2), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 2), makePiece(WHITE, PAWN)), 
					makeSquare('e', 2), makeSquare('e', 4), true),
			
					//black
			Arguments.of(makeBoard(makeSquare('e', 7), makePiece(BLACK, PAWN)), 
					makeSquare('e', 7), makeSquare('e', 6), true),
			Arguments.of(makeBoard(makeSquare('e', 7), makePiece(BLACK, PAWN)), 
					makeSquare('e', 7), makeSquare('e', 5), true),
			
				//moving forward can't happen if there is interference
					//white
			Arguments.of(makeBoard(makeSquare('e', 2), makePiece(WHITE, PAWN), makeSquare('e', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 2), makeSquare('e', 4), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN), makeSquare('e', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('e', 5), false),
			
					//black
			Arguments.of(makeBoard(makeSquare('e', 7), makePiece(BLACK, PAWN), makeSquare('e', 6), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 7), makeSquare('e', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN), makeSquare('e', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('e', 3), false),
			
				//taking pieces
					//white
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN), makeSquare('f', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 5), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN), makeSquare('d', 5), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 5), true),
						//white can't take pieces backwards
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN), makeSquare('f', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 3), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, PAWN), makeSquare('d', 3), makePiece(BLACK, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 3), false),
					
					//black
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN), makeSquare('f', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN), makeSquare('d', 3), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 3), true),
						//black can't take pieces forward
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN), makeSquare('f', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('f', 5), false),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(BLACK, PAWN), makeSquare('d', 5), makePiece(WHITE, KNIGHT)), 
					makeSquare('e', 4), makeSquare('d', 5), false),
			
			//pieces can't start on their respective color's back row
				//white
			Arguments.of(makeBoard(makeSquare('e', 1), makePiece(WHITE, PAWN)), 
					makeSquare('e', 1), makeSquare('e', 2), false),
			
				//black
			Arguments.of(makeBoard(makeSquare('e', 8), makePiece(BLACK, PAWN)), 
					makeSquare('e', 8), makeSquare('e', 7), false)
		);
	}
	
	@ParameterizedTest
	@MethodSource("errorTestCaseProvider")
	void errorThrowsTest(ChessBoard board, Square source, Square target)
	{
		assertThrows(CMVException.class, () -> canMove(board, source, target));
	}
	
	static Stream<Arguments> errorTestCaseProvider()
	{
		return Stream.of(
			// Empty board
			Arguments.of(makeBoard(), makeSquare('e', 4), makeSquare('e', 5)),
			
			//"from" and "to" are the same space
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 4), makeSquare('e', 4)),
			
			//"from" not in range exception
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 0), makeSquare('e', 4)),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 9), makeSquare('e', 4)),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('A', 4), makeSquare('e', 4)),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('z', 4), makeSquare('e', 4)),
			
			//"to" not in range exception
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 4), makeSquare('e', 0)),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 4), makeSquare('e', 9)),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 4), makeSquare('A', 4)),
			Arguments.of(makeBoard(makeSquare('e', 4), makePiece(WHITE, QUEEN)), makeSquare('e', 4), makeSquare('z', 4))
			
		
		);
	}
	

	// Helper methods
	/**
	 * Create the board configuration. 
	 * @param sp alternating squares and pieces
	 * @return the ChessBoard
	 */
	private static ChessBoard makeBoard(Object ...sp)
	{
		Map<Square, ChessPiece> config = new HashMap<Square, ChessPiece>();
		int i = 0;
		int max = sp.length;
		while (i < max) {
			Square s = (Square)sp[i++];
			ChessPiece p = (ChessPiece)sp[i++];
			config.put(s, p);
		}
		return new ChessBoard(config);
	}
}
