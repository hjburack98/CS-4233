package strategy.beta;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.Version.*;
import static strategy.required.StrategyGameFactory.*;
import static strategy.StrategyGame.MoveResult.*;


public class PieceImplTest
{
	@Test
	public void createMarshal()
	{
		PieceImpl piece = new PieceImpl(PieceColor.BLUE, PieceType.MARSHAL);
		assertEquals(piece.getRank(), 12);
		assertEquals(piece.getPieceColor(), PieceColor.BLUE);
		assertEquals(piece.getPieceType(), PieceType.MARSHAL);
	}
	
	@Test
	public void createColonel()
	{
		PieceImpl piece = new PieceImpl(PieceColor.BLUE, PieceType.COLONEL);
		assertEquals(piece.getRank(), 10);
		assertEquals(piece.getPieceColor(), PieceColor.BLUE);
		assertEquals(piece.getPieceType(), PieceType.COLONEL);
	}
	
	@Test
	public void createCaptain()
	{
		PieceImpl piece = new PieceImpl(PieceColor.BLUE, PieceType.CAPTAIN);
		assertEquals(piece.getRank(), 8);
		assertEquals(piece.getPieceColor(), PieceColor.BLUE);
		assertEquals(piece.getPieceType(), PieceType.CAPTAIN);
	}
	
	@Test
	public void createLieutenant()
	{
		PieceImpl piece = new PieceImpl(PieceColor.BLUE, PieceType.LIEUTENANT);
		assertEquals(piece.getRank(), 7);
		assertEquals(piece.getPieceColor(), PieceColor.BLUE);
		assertEquals(piece.getPieceType(), PieceType.LIEUTENANT);
	}
	
	@Test
	public void createSergeant()
	{
		PieceImpl piece = new PieceImpl(PieceColor.BLUE, PieceType.SERGEANT);
		assertEquals(piece.getRank(), 6);
		assertEquals(piece.getPieceColor(), PieceColor.BLUE);
		assertEquals(piece.getPieceType(), PieceType.SERGEANT);
	}
	
	@Test
	public void createFlag()
	{
		PieceImpl piece = new PieceImpl(PieceColor.BLUE, PieceType.FLAG);
		assertEquals(piece.getRank(), 1);
		assertEquals(piece.getPieceColor(), PieceColor.BLUE);
		assertEquals(piece.getPieceType(), PieceType.FLAG);
	}
	
	@Test
	public void redBeatBlue()
	{
		PieceImpl piece = new PieceImpl(PieceColor.RED, PieceType.MARSHAL);
		PieceImpl opponentPiece = new PieceImpl(PieceColor.BLUE, PieceType.FLAG);
		assertTrue(piece.beatEnemy(opponentPiece));
	}
	
	@Test
	public void redLosesBlue()
	{
		PieceImpl piece = new PieceImpl(PieceColor.RED, PieceType.CAPTAIN);
		PieceImpl opponentPiece = new PieceImpl(PieceColor.BLUE, PieceType.MARSHAL);
		assertFalse(piece.beatEnemy(opponentPiece));
	}
	
	@Test
	public void redChallengesRed()
	{
		PieceImpl piece = new PieceImpl(PieceColor.RED, PieceType.MARSHAL);
		PieceImpl opponentPiece = new PieceImpl(PieceColor.RED, PieceType.FLAG);
		assertFalse(piece.beatEnemy(opponentPiece));
	}
	
	@Test
	public void challengesSameRank()
	{
		PieceImpl piece = new PieceImpl(PieceColor.RED, PieceType.MARSHAL);
		PieceImpl opponentPiece = new PieceImpl(PieceColor.BLUE, PieceType.MARSHAL);
		assertFalse(piece.beatEnemy(opponentPiece));
	}
}
