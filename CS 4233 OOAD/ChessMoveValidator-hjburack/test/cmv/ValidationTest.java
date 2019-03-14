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
			// Empty board
			Arguments.of(makeBoard(), makeSquare('e', 4), makeSquare('e', 5), false),
			
			// Rook tests
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK)), 
					makeSquare('h', 1), makeSquare('e', 3), false),
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK), makeSquare('h', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), false),
			
			// Bishop tests
			Arguments.of(makeBoard(makeSquare('c', 1), makePiece(BLACK, BISHOP)), 
					makeSquare('c', 1), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('h', 1), false)
			
			// Queen tests
			
			// King tests
			
			// Knight tests
			
			// Pawn tests
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
