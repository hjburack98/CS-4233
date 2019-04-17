package strategy.delta;

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

public class DeltaStrategyMasterTests
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
				LIEUTENANT, SPY, SCOUT, MINER, BOMB, SERGEANT, MINER, BOMB, MARSHAL, SCOUT);
		blueLineup = theBoard.makeLineup(BLUE,
				SPY, SCOUT, BOMB, MINER, SERGEANT, BOMB, MINER, SCOUT, FLAG, SPY,
				MINER, MAJOR, SERGEANT, CAPTAIN, SCOUT, CAPTAIN, MINER, SCOUT, BOMB, MAJOR, 
				SCOUT, COLONEL, LIEUTENANT, BOMB, SERGEANT, GENERAL, MINER, MAJOR, SCOUT, BOMB,
				MINER, CAPTAIN, SCOUT, BOMB, LIEUTENANT, LIEUTENANT, CAPTAIN, COLONEL, MARSHAL, SERGEANT);
		
		theBoard.placeChokeAt(4, 2);
		theBoard.placeChokeAt(4, 3);
		theBoard.placeChokeAt(5, 2);
		theBoard.placeChokeAt(5, 3);
		
		theBoard.placeChokeAt(4, 6);
		theBoard.placeChokeAt(4, 7);
		theBoard.placeChokeAt(5, 6);
		theBoard.placeChokeAt(5, 6);
		
		theBoard.initialize(10, 10, redLineup, blueLineup);
		
		theGame = makeGame(DELTA, theBoard);
	}
	
	@Test
	void spyStrikingMarshal()
	{
		theGame.move(3, 1, 4, 1);
		theGame.move(6, 1, 5, 1);
		assertEquals(STRIKE_RED, theGame.move(4, 1, 5, 1));
	}
	
	@Test
	void marshalStrikingSpy()
	{
		theGame.move(3, 1, 4, 1);
		theGame.move(6, 1, 5, 1);
		theGame.move(3, 0, 4, 0);
		assertEquals(STRIKE_BLUE, theGame.move(5, 1, 4, 1));
	}
	
	
	
	
	
	
	
}
