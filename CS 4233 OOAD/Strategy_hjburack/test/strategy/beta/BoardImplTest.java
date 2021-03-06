package strategy.beta;

import static org.junit.jupiter.api.Assertions.*;
import static strategy.Piece.PieceColor.BLUE;
import static strategy.Piece.PieceColor.RED;
import static strategy.Piece.PieceType.CAPTAIN;
import static strategy.Piece.PieceType.COLONEL;
import static strategy.Piece.PieceType.FLAG;
import static strategy.Piece.PieceType.LIEUTENANT;
import static strategy.Piece.PieceType.MARSHAL;
import static strategy.Piece.PieceType.SERGEANT;
import static strategy.StrategyGame.Version.BETA;
import static strategy.required.StrategyGameFactory.makeGame;
import java.util.List;
import org.junit.jupiter.api.*;
import strategy.Piece;
import strategy.Piece.*;
import strategy.StrategyGame.*;
import strategy.hjburack.*;
import strategy.required.*;
import strategy.testutil.*;

public class BoardImplTest
{
	
	
	@Test
	public void canConvertBoard()
	{
		TestBoard theBoard = new TestBoard(6, 6);
		List<Piece> redLineup = theBoard.makeLineup(RED,
				SERGEANT, SERGEANT, COLONEL, CAPTAIN, LIEUTENANT, LIEUTENANT,
				FLAG, MARSHAL, COLONEL, CAPTAIN, LIEUTENANT, SERGEANT);
		List<Piece> blueLineup = theBoard.makeLineup(BLUE,
				MARSHAL, COLONEL, CAPTAIN, SERGEANT, FLAG, LIEUTENANT,
				LIEUTENANT, LIEUTENANT, SERGEANT, SERGEANT, COLONEL, CAPTAIN);
		theBoard.initialize(6, 6, redLineup, blueLineup);
		
		
		BoardImpl convertedBoard = BoardImpl.convertBoard(theBoard, 6, 6);
		
		assertEquals(new PieceImpl(PieceColor.RED, PieceType.SERGEANT), convertedBoard.getPieceAt(0, 0));
	}
	
	@Test
	public void addPiece()
	{
		 TestBoard theBoard = new TestBoard(6, 6);
		List<Piece> redLineup = theBoard.makeLineup(RED,
				SERGEANT, SERGEANT, COLONEL, CAPTAIN, LIEUTENANT, LIEUTENANT,
				FLAG, MARSHAL, COLONEL, CAPTAIN, LIEUTENANT, SERGEANT);
		List<Piece> blueLineup = theBoard.makeLineup(BLUE,
				MARSHAL, COLONEL, CAPTAIN, SERGEANT, FLAG, LIEUTENANT,
				LIEUTENANT, LIEUTENANT, SERGEANT, SERGEANT, COLONEL, CAPTAIN);
		theBoard.initialize(6, 6, redLineup, blueLineup);
			
			
		BoardImpl convertedBoard = BoardImpl.convertBoard(theBoard, 6, 6);
		
		PieceImpl newPiece = new PieceImpl(PieceColor.RED, PieceType.MARSHAL);
		
		convertedBoard.addPiece(newPiece, 3, 3);
			
		assertEquals(new PieceImpl(PieceColor.RED, PieceType.MARSHAL), convertedBoard.getPieceAt(3, 3));
	}
	
	@Test
	public void removePiece()
	{
		TestBoard theBoard = new TestBoard(6, 6);
		List<Piece> redLineup = theBoard.makeLineup(RED,
				SERGEANT, SERGEANT, COLONEL, CAPTAIN, LIEUTENANT, LIEUTENANT,
				FLAG, MARSHAL, COLONEL, CAPTAIN, LIEUTENANT, SERGEANT);
		List<Piece> blueLineup = theBoard.makeLineup(BLUE,
				MARSHAL, COLONEL, CAPTAIN, SERGEANT, FLAG, LIEUTENANT,
				LIEUTENANT, LIEUTENANT, SERGEANT, SERGEANT, COLONEL, CAPTAIN);
		theBoard.initialize(6, 6, redLineup, blueLineup);
		
		
		BoardImpl convertedBoard = BoardImpl.convertBoard(theBoard, 6, 6);
		
		convertedBoard.removePiece(0, 0);
		assertEquals(null, convertedBoard.getPieceAt(0,  0));
	}
	
}
