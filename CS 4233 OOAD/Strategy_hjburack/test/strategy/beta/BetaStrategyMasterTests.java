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

package strategy.beta;

import static org.junit.jupiter.api.Assertions.*;
import static strategy.Piece.PieceColor.*;
import static strategy.Piece.PieceType.*;
import static strategy.StrategyGame.MoveResult.*;
import static strategy.StrategyGame.Version.*;
import static strategy.required.StrategyGameFactory.makeGame;

import java.util.List;
import org.junit.jupiter.api.*;
import strategy.*;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.hjburack.BoardImpl;
import strategy.hjburack.PieceImpl;
import strategy.testutil.TestBoard;
import static strategy.Piece.PieceColor.*;
import static strategy.Piece.PieceType.*;

/**
 * Master tests for Beta Strategy
 * @version Mar 29, 2019
 */
class BetaStrategyMasterTests
{
	private int rows = 0;
	private int columns = 0;
	private StrategyGame theGame = null;
	private List<Piece> redLineup = null;
	private List<Piece> blueLineup = null;
	private TestBoard theBoard = null;
	
	@BeforeEach
	void betaSetup() throws Exception
	{
		theBoard = new TestBoard(6, 6);
		redLineup = theBoard.makeLineup(RED,
				SERGEANT, SERGEANT, COLONEL, CAPTAIN, LIEUTENANT, LIEUTENANT,
				FLAG, MARSHAL, COLONEL, CAPTAIN, LIEUTENANT, SERGEANT);
		blueLineup = theBoard.makeLineup(BLUE,
				MARSHAL, COLONEL, CAPTAIN, SERGEANT, FLAG, LIEUTENANT,
				LIEUTENANT, LIEUTENANT, SERGEANT, SERGEANT, COLONEL, CAPTAIN);
		theBoard.initialize(6, 6, redLineup, blueLineup);
		theGame = makeGame(BETA, theBoard);
	}
	
	
	@Test
	void startingCoordinateOutOfBounds()
	{
		assertEquals(BLUE_WINS, theGame.move(-1, 1, 0, 1));
		assertEquals(BLUE_WINS, theGame.move(1, -1, 1, 0));
		assertEquals(BLUE_WINS, theGame.move(-1, -1, 0, 0));
		assertEquals(BLUE_WINS, theGame.move(6, 0, 5, 0));
		assertEquals(BLUE_WINS, theGame.move(0, 6, 0, 5));
		assertEquals(BLUE_WINS, theGame.move(6, 6, 5, 5));
		
	}
	
	@Test
	void targetCoordinateOutOfBounds()
	{
		assertEquals(BLUE_WINS, theGame.move(1, 0, 1, -1));
		assertEquals(BLUE_WINS, theGame.move(0, 1, -1, 1));
		assertEquals(BLUE_WINS, theGame.move(0, 0, -1, -1));
		assertEquals(BLUE_WINS, theGame.move(5, 0, 6, 0));
		assertEquals(BLUE_WINS, theGame.move(0, 5, 0, 6));
		assertEquals(BLUE_WINS, theGame.move(5, 5, 6, 6));
	}
	
	@Test
	void selectedMoveTooFar()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 2, 0));
		assertEquals(BLUE_WINS, theGame.move(0, 0, 0, 2));
		assertEquals(BLUE_WINS, theGame.move(0, 0, 2, 2));
		assertEquals(BLUE_WINS, theGame.move(2, 0, 0, 0));
		assertEquals(BLUE_WINS, theGame.move(0, 2, 0, 0));
		assertEquals(BLUE_WINS, theGame.move(2, 2, 0, 0));
		assertEquals(BLUE_WINS, theGame.move(0, 2, 2, 0));
	}
	
	
	@Test
	void pieceIsntMoving()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 0, 0));
	}
	
	@Test
	void validVertical()
	{
		assertEquals(OK, theGame.move(1, 1, 2, 1)); //go up
		assertEquals(OK, theGame.move(4, 0, 3, 0)); //go down	
	}
	
	@Test
	void validDiagonal()
	{
		assertEquals(OK, theGame.move(1, 1, 2, 0)); //go diagonal up left
		assertEquals(OK, theGame.move(1, 4, 2, 5)); //go diagonal up right
		assertEquals(OK, theGame.move(4, 1, 3, 0)); // go diagonal down left
		assertEquals(OK, theGame.move(4, 4, 3, 5)); // go diagonal down right
	}
	
	@Test
	void validLeft()
	{
		theGame.move(1, 1, 2, 1);
		assertEquals(OK, theGame.move(2, 1, 2, 0)); //go left
	}
	
	@Test
	void validRight()
	{
		theGame.move(1, 1, 2, 1);
		assertEquals(OK, theGame.move(2, 1, 2, 2)); //go right
	}
	
	@Test
	void collisionWithTeam()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 1, 0));
	}
	
	/*
	@Test 
	void redWinsAfterEightTurns()
	{
		theGame.move(1, 1, 2, 1);	// Move 1
		theGame.move(4, 2, 3, 2);
		theGame.move(2, 1, 1, 1);	// Move 2
		theGame.move(3, 2, 4, 2);
		theGame.move(1, 1, 2, 1);	// Move 3
		theGame.move(4, 2, 3, 2);
		theGame.move(2, 1, 1, 1);	// Move 4
		theGame.move(3, 2, 4, 2);
		theGame.move(1, 1, 2, 1);	// Move 5
		theGame.move(4, 2, 3, 2);
		theGame.move(2, 1, 1, 1);	// Move 6
		theGame.move(3, 2, 4, 2);
		theGame.move(1, 1, 2, 1);	// Move 7
		theGame.move(4, 2, 3, 2);
		assertEquals(OK, theGame.move(2, 1, 1, 1));	// Move 8
		assertEquals(RED_WINS, theGame.move(3, 2, 4, 2));
	}
	*/
	
	@Test
	void versionNotImplemented()
	{
		assertThrows(NotImplementedException.class, () -> makeGame(ZETA, null));
	}
}
