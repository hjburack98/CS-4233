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
		
		if(this.board.getPieceAt(fr, fc) != null)
		{
			if(fr < 0 || fr >= WIDTH || fc < 0 || fc >= HEIGHT)
			{
				return opponentWins(turn);
			}
			if(tr < 0 || tr >= WIDTH || tc < 0 || tc >= HEIGHT)
			{
				return opponentWins(turn);
			}
			if(Math.abs(tr-fr) > 1 || Math.abs(tc-fc) > 1)
			{
				return opponentWins(turn);
			}
			if(fr-tr == 0 && fc-tc == 0)
			{
				return opponentWins(turn);
			}
			
			return OK;
		}
		
		return opponentWins(turn);
		
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
	
	private void movePiece(int fr, int fc, int tr, int tc)
	{
		PieceImpl aPiece = board.getPieceAt(fr, fc);
		board.removePiece(fr, fc);
		board.addPiece(aPiece, tr, tc);
	}
	
	
}
