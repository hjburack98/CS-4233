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
				CAPTAIN, COLONEL, SERGEANT, SERGEANT, LIEUTENANT, LIEUTENANT,
				LIEUTENANT, FLAG, SERGEANT, CAPTAIN, COLONEL, MARSHAL);
		theBoard.initialize(6, 6, redLineup, blueLineup);
		theGame = makeGame(BETA, theBoard);
	}
	
	
	@Test
	void startingCoordinateLowRow()
	{
		assertEquals(BLUE_WINS, theGame.move(-1, 1, 0, 1));
	}
	@Test
	void startingCoordinateLowCol()
	{
		assertEquals(BLUE_WINS, theGame.move(1, -1, 1, 0));
	}
	@Test
	void startingCoordinateAllLow()
	{
		assertEquals(BLUE_WINS, theGame.move(-1, -1, 0, 0));
	}
	@Test
	void startingCoordinateHighRow()
	{
		assertEquals(BLUE_WINS, theGame.move(6, 0, 5, 0));
	}
	@Test
	void startingCoordinateHighCol()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 6, 0, 5));
	}
	@Test
	void startingCoordinateAllHigh()
	{
		assertEquals(BLUE_WINS, theGame.move(6, 6, 5, 5));
	}
	
	@Test
	void targetCoordinateLowRow()
	{
		assertEquals(BLUE_WINS, theGame.move(1, 0, 1, -1));
	}
	@Test
	void targetCoodinateLowCol()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 1, -1, 1));
	}
	@Test
	void targetCoordinateAllLow()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, -1, -1));
	}
	@Test
	void targetCoordinateHighRow()
	{
		assertEquals(BLUE_WINS, theGame.move(5, 0, 6, 0));
	}
	@Test
	void targetCoordHighCol()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 5, 0, 6));
	}
	@Test
	void targetCoordAllHigh()
	{
		assertEquals(BLUE_WINS, theGame.move(5, 5, 6, 6));
	}
	
	@Test
	void tooFarUp()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 2, 0));
		
	}
	@Test
	void tooFarDown()
	{
		assertEquals(BLUE_WINS, theGame.move(2, 0, 0, 0));
	}
	@Test
	void tooFarLeft()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 2, 0, 0));
	}
	@Test
	void tooFarRight()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 0, 2));
	}
	@Test 
	void tooFarDiagonalUop()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 2, 2));
	}
	@Test 
	void tooFarDiagonalDown()
	{
		assertEquals(BLUE_WINS, theGame.move(2, 2, 0, 0));
	}
	@Test 
	void tooFarDiagonalLeft()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 2, 2, 0));
	}
	@Test 
	void tooFarDiagonalRight()
	{
		assertEquals(BLUE_WINS, theGame.move(2, 0, 0, 2));
	}
	@Test
	void CantMoveOpponentPiece()
	{
		assertEquals(BLUE_WINS, theGame.move(4, 0, 3, 0));
	}
	
	@Test
	void pieceIsntMoving()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 0, 0));
	}
	
	@Test
	void selectedFlag()
	{
		assertEquals(BLUE_WINS, theGame.move(1, 0, 2, 0));
	}
	@Test
	void cantDiagonalUpLeft()
	{
		assertEquals(BLUE_WINS, theGame.move(1, 1, 2, 0));
	}
	@Test
	void cantDiagonalDownLeft()
	{
		theGame.move(1, 1, 2, 1);
		assertEquals(RED_WINS, theGame.move(4, 1, 3, 0));
	}
	@Test
	void cantDiagonalUpRight()
	{
		assertEquals(BLUE_WINS, theGame.move(1, 4, 2, 5));
	}
	@Test
	void cantDiagonalDownRight()
	{
		theGame.move(1, 1, 2, 1);
		assertEquals(RED_WINS, theGame.move(4, 3, 3, 4));
	}

	
	
	@Test
	void validVertical()
	{
		assertEquals(OK, theGame.move(1, 1, 2, 1)); //go up
		assertEquals(OK, theGame.move(4, 0, 3, 0)); //go down	
	}
	
	@Test
	void validLeft()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 0, 3, 0);
		assertEquals(OK, theGame.move(2, 1, 2, 0)); //go left
	}
	
	@Test
	void validRight()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 0, 3, 0);
		assertEquals(OK, theGame.move(2, 1, 2, 2)); //go right
	}
	
	@Test
	void collisionWithTeam()
	{
		assertEquals(BLUE_WINS, theGame.move(0, 0, 1, 0));
	}
	
	@Test
	void verticalStrikeRedWins()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 1, 3, 1);
		assertEquals(STRIKE_RED, theGame.move(2, 1, 3, 1));
		
	}
	
	@Test
	void horizontalStrikeBlueWins()
	{
		theGame.move(1, 2, 2, 2);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 2, 2, 1);
		theGame.move(3, 0, 2, 0);
		theGame.move(1, 5, 2, 5);
		assertEquals(STRIKE_BLUE, theGame.move(2, 0, 2, 1));
	}
	
	@Test
	void redAttackerLosesStrike()
	{
		theGame.move(1, 2, 2, 2);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 2, 2, 1);
		theGame.move(3, 0, 2, 0);
		assertEquals(STRIKE_BLUE, theGame.move(2, 1, 2, 0));
	}
	
	@Test
	void blueAttackerLosesStrike()
	{
		assertEquals(OK, theGame.move(1, 1, 2, 1));
		assertEquals(OK, theGame.move(4, 5, 3, 5));
		assertEquals(OK, theGame.move(2, 1, 3, 1));
		assertEquals(OK, theGame.move(3, 5, 2, 5));
		assertEquals(STRIKE_BLUE, theGame.move(1, 5, 2, 5));
		assertEquals(STRIKE_RED, theGame.move(4, 1, 3, 1));
		
	}
	
	@Test
	void strikeSameType()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 1, 2, 0);
		assertEquals(OK, theGame.move(3, 0, 2, 0));
		assertEquals(BLUE_WINS, theGame.move(2, 0, 2, 1));
	}
	
	@Test
	void strikeBlueFlagRedWin()
	{
		theGame.move(1,	4, 2, 4);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 4, 3, 4);
		theGame.move(3, 0, 2, 0);
		assertEquals(RED_WINS, theGame.move(3, 4, 4, 4));
	}
	
	@Test
	void strikeRedFlagBlueWin()
	{
		theGame.move(1,5,2,5);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 5, 3, 5);
		theGame.move(3, 0, 2, 0);
		theGame.move(1,4,2,4);
		assertEquals(BLUE_WINS, theGame.move(2, 0, 1, 0));
	}
	
	//TODO: FIX THIS BUG
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
		assertEquals(GAME_OVER, theGame.move(1, 5, 2, 5));
	}
	*/
	
	
	@Test
	void versionNotImplemented()
	{
		assertThrows(NotImplementedException.class, () -> makeGame(ZETA, null));
	}
}
