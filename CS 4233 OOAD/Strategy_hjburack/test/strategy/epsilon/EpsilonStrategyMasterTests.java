package strategy.epsilon;

import static org.junit.jupiter.api.Assertions.*;
import static strategy.Piece.PieceColor.*;
import static strategy.Piece.PieceType.*;
import static strategy.StrategyGame.MoveResult.*;
import static strategy.StrategyGame.Version.*;
import static strategy.required.StrategyGameFactory.makeGame;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.*;
import strategy.*;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.hjburack.BoardImpl;
import strategy.hjburack.CoordinateImpl;
import strategy.hjburack.PieceImpl;
import strategy.testutil.TestBoard;
import static strategy.Piece.PieceColor.*;
import static strategy.Piece.PieceType.*;

public class EpsilonStrategyMasterTests
{
	private int rows = 0;
	private int columns = 0;
	private StrategyGame theGame = null;
	private List<Piece> redLineup = null;
	private List<Piece> blueLineup = null;
	private TestBoard theBoard = null;
	
	@BeforeEach
	void gammaSetup() throws Exception
	{
		theBoard = new TestBoard(10, 10);
		redLineup = theBoard.makeLineup(RED,
				SERGEANT, LIEUTENANT, COLONEL, CAPTAIN, FLAG, LIEUTENANT, BOMB, SCOUT, CAPTAIN, MINER,
				BOMB, SCOUT, MAJOR, MINER, GENERAL, SERGEANT, BOMB, LIEUTENANT, COLONEL, SCOUT, 
				MAJOR, BOMB, SCOUT, MINER, CAPTAIN, SCOUT, CAPTAIN, SERGEANT, MAJOR, MINER,
				LIEUTENANT, SPY, MARSHAL, MINER, BOMB, SERGEANT, MINER, SCOUT, SCOUT, BOMB);
		blueLineup = theBoard.makeLineup(BLUE,
				SPY, SCOUT, BOMB, MINER, SERGEANT, BOMB, MINER, SCOUT, FLAG, SPY,
				MINER, MAJOR, SERGEANT, CAPTAIN, SCOUT, CAPTAIN, MINER, SCOUT, BOMB, MAJOR, 
				SERGEANT, COLONEL, LIEUTENANT, BOMB, SERGEANT, GENERAL, MINER, MAJOR, LIEUTENANT, BOMB,
				SCOUT, CAPTAIN, SCOUT, BOMB, SCOUT, MINER, CAPTAIN, COLONEL, MARSHAL, LIEUTENANT);
		
		theBoard.placeChokeAt(4, 2);
		theBoard.placeChokeAt(4, 3);
		theBoard.placeChokeAt(5, 2);
		theBoard.placeChokeAt(5, 3);
		
		theBoard.placeChokeAt(4, 6);
		theBoard.placeChokeAt(4, 7);
		theBoard.placeChokeAt(5, 6);
		theBoard.placeChokeAt(5, 6);
		
		theBoard.initialize(10, 10, redLineup, blueLineup);
		
		theGame = makeGame(EPSILON, theBoard);
	}
	
	@Test
	void depetingBomb()
	{
		theGame.move(3, 0, 4, 0);
		theGame.move(6, 9, 5, 9);
		theGame.move(4, 0, 5, 0);
		theGame.move(5, 9, 4, 9);
		theGame.move(5, 0, 5, 1);
		assertEquals(STRIKE_RED, theGame.move(4, 9, 3, 9));
		theGame.move(5, 1, 5, 0);
		theGame.move(6, 8, 6, 9);
		theGame.move(5, 0, 4, 0);
		theGame.move(6, 9, 5, 9);
		theGame.move(4, 0, 4, 1);
		theGame.move(5, 9, 4, 9);
		theGame.move(4, 1, 5, 1);
		theGame.move(4, 9, 3, 9);
		theGame.move(3, 8, 4, 8);
		assertEquals(OK, theGame.move(7, 9, 6, 9));
		theGame.move(5, 1, 5, 0);
		theGame.move(6, 9, 5, 9);
		theGame.move(5, 0, 4, 0);
		theGame.move(5, 9, 4, 9);
		theGame.move(4, 0, 4, 1);
		assertEquals(OK, theGame.move(4, 9, 3, 9));
	}
	
	@Test
	void redAgressorAdvantage()
	{
		theGame.move(3, 0, 4, 0);
		theGame.move(6, 0, 5, 0);
		assertEquals(STRIKE_RED, theGame.move(4, 0, 5, 0));
	}
	
	@Test
	void blueAgressorAdvantage()
	{
		theGame.move(3, 0, 4, 0);
		theGame.move(6, 0, 5, 0);
		theGame.move(3, 1, 4, 1);
		assertEquals(STRIKE_BLUE, theGame.move(5, 0, 4, 0));
	}
	
	
	@Test
	void scoutOrthogonalLoss()
	{
		assertEquals(STRIKE_BLUE, theGame.move(3, 8, 6, 8));
	}
	
	
	@Test
	void scoutOrthogonalWin()
	{
		assertEquals(OK,theGame.move(3, 8, 4, 8));
		theGame.move(6, 0, 5, 0);
		theGame.move(4, 8, 4, 9);
		assertEquals(STRIKE_BLUE, theGame.move(6, 9, 4, 9));
	}
	
	@Test
	void scoutOrthogonalGreaterThree()
	{
		theGame.move(3, 5, 4, 5);
		theGame.move(6, 0, 5, 0);
		theGame.move(4, 5, 4, 4);
		assertEquals(RED_WINS, theGame.move(6, 5, 2, 5));
	}
	
	@Test
	void scoutOrthogonalAttackInterference()
	{
		theGame.move(3, 5, 4, 5);
		theGame.move(6, 0, 5, 0);
		theGame.move(2, 5, 3, 5);
		assertEquals(RED_WINS, theGame.move(6, 5, 3, 5));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
