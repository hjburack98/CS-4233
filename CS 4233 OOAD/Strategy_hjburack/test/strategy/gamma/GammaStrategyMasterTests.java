package strategy.gamma;

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

public class GammaStrategyMasterTests
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
		theBoard = new TestBoard(6, 6);
		redLineup = theBoard.makeLineup(RED,
				SERGEANT, SERGEANT, COLONEL, CAPTAIN, LIEUTENANT, LIEUTENANT,
				FLAG, MARSHAL, COLONEL, CAPTAIN, LIEUTENANT, SERGEANT);
		blueLineup = theBoard.makeLineup(BLUE,
				CAPTAIN, COLONEL, SERGEANT, SERGEANT, LIEUTENANT, LIEUTENANT,
				LIEUTENANT, FLAG, SERGEANT, CAPTAIN, COLONEL, MARSHAL);
		theBoard.placeChokeAt(2, 2);
		theBoard.placeChokeAt(2, 3);
		theBoard.placeChokeAt(3, 2);
		theBoard.placeChokeAt(3, 3);
		theBoard.initialize(6, 6, redLineup, blueLineup);
		
		theGame = makeGame(GAMMA, theBoard);
	}
	
	@Test
	void redLosesAfterConsecutiveMoves()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 1, 1, 1);
		theGame.move(3, 0, 2, 0);
		assertEquals(BLUE_WINS, theGame.move(1, 1, 2, 1));
	}
	
	@Test
	void  blueLosesAfterConsecutiveMoves()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 0, 3, 0);
		theGame.move(2, 1, 3, 1);
		theGame.move(3, 0, 4, 0);
		theGame.move(3, 1, 2, 1);
		assertEquals(RED_WINS, theGame.move(4, 0, 3, 0));
	}
	
	@Test
	void cantGoToChoke()
	{
		theGame.move(1, 1, 2, 1);
		theGame.move(4, 0, 3, 0);
		assertEquals(BLUE_WINS, theGame.move(2, 1, 2, 2));
	}
	
}
