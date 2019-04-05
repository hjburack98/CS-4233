package strategy.hjburack.version.beta;

import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.MoveResult.*;
import strategy.Piece.PieceColor;


public class BetaStrategyGame implements StrategyGame
{
	
	private final int WIDTH = 6;
	private final int HEIGHT = 6;
	private BoardImpl board;
	
	public BetaStrategyGame(BoardImpl board)
	{
		this.board = board;
	}
	
	public MoveResult move(int fr, int fc, int tr, int tc)
	{
		PieceColor turn = PieceColor.RED;

		if(this.isInvalidMove(fr, fc, tr, tc))
		{
			return opponentWins(turn);
		}
		
		
		return OK;
		
	}
	
	private static MoveResult opponentWins(PieceColor team)
	{
		if(team == PieceColor.RED)
		{
			return BLUE_WINS;
		}
		else
		{
			return RED_WINS;
		}
	}
	
	private boolean isInvalidMove(int fr, int fc, int tr, int tc)
	{
		if(board.getPieceAt(fr, fc) == null)
		{
			return true;
		}
		else if(fr < 0 || fr >= WIDTH || fc < 0 || fc >= HEIGHT)
		{
			return true;
		}
		else if(tr < 0 || tr >= WIDTH || tc < 0 || tc >= HEIGHT)
		{
			return true;
		}
		else if(Math.abs(tr-fr) > 1 || Math.abs(tc-fc) > 1)
		{
			return true;
		}
		else if(fr-tr == 0 && fc-tc == 0)
		{
			return true;
		}
		
		return false;
	}
	
	private void movePiece(int fr, int fc, int tr, int tc)
	{
		PieceImpl aPiece = board.getPieceAt(fr, fc);
		board.removePiece(fr, fc);
		board.addPiece(aPiece, tr, tc);
	}
	
	
}
