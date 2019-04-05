package strategy.hjburack.version.beta;

import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.MoveResult.*;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;


public class BetaStrategyGame implements StrategyGame
{
	
	private final int WIDTH = 6;
	private final int HEIGHT = 6;
	private BoardImpl board;
	PieceColor turn = PieceColor.RED;
	
	public BetaStrategyGame(BoardImpl board)
	{
		this.board = board;
		turn = PieceColor.RED;
	}
	
	public MoveResult move(int fr, int fc, int tr, int tc)
	{
		/*
		MoveResult gameWon = null;
		if(gameWon != null)
		{
			return GAME_OVER;
		}
		*/
		
		if(this.isInvalidMove(fr, fc, tr, tc))
		{
			return opponentWins(turn);
		}
		
		//valid move
		if(board.getPieceAt(tr, tc) == null)
		{
			this.movePiece(fr, fc, tr, tc);
			changeColor(turn);
			return OK;
			
		}
		
		//Striking
		else
		{
			//if the piece at the target coordinate is your own, you lose
			if(board.getPieceAt(tr, tc).getPieceColor() == board.getPieceAt(fr, fc).getPieceColor())
			{
				return opponentWins(turn);
			}
			else
			{
				//must make sure that that the strike is only a vertical or horizontal move
				if((Math.abs(tr-fr) == 1 && tc-fc == 0) || (Math.abs(tc-fc) == 1 && tr-fr == 0))
				{
					if(board.getPieceAt(fr, fc).getRank() > board.getPieceAt(tr, tc).getRank())
					{
						MoveResult returnVal = null;
						if(board.getPieceAt(fr, fc).getPieceColor() == PieceColor.RED)
						{
							returnVal = STRIKE_RED;
						}
						else
						{
							returnVal = STRIKE_BLUE;
						}
						if(board.getPieceAt(tc, tr).getPieceType() == PieceType.FLAG) {
							if(board.getPieceAt(fr, fc).getPieceColor() == PieceColor.RED)
							{
								returnVal = RED_WINS;
							}
							else
							{
								returnVal = BLUE_WINS;
							}
						}
						board.removePiece(tr, tc);
						this.movePiece(fr, fc, tr, tc);
						
						return returnVal;
						
					}
					else if(board.getPieceAt(fr, fc).getRank() < board.getPieceAt(tr, tc).getRank())
					{
						MoveResult returnVal = null;
						if(board.getPieceAt(fr, fc).getPieceColor() == PieceColor.RED)
						{
							returnVal = STRIKE_BLUE;
						}
						else
						{
							returnVal = STRIKE_RED;
						}
						
						board.removePiece(fr, fc);
						this.movePiece(tr, tc, fr, fc);
						return returnVal;
						
					}
					//if they are the same rank, remove both pieces
					else
					{
						board.removePiece(fr, fc);
						board.removePiece(tr, tc);
						return OK;
					}
				}
				else
				{
					return opponentWins(turn);
				}
			}
		}
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
	
	private static PieceColor changeColor(PieceColor color)
	{
		if(color == PieceColor.RED)
		{
			return PieceColor.BLUE;
		}
		else
		{
			return PieceColor.RED;
		}
	}
	private static MoveResult youWin(PieceColor team)
	{
		if(team == PieceColor.BLUE)
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
		else if(fr < 0 || fr >= HEIGHT || fc < 0 || fc >= WIDTH)
		{
			return true;
		}
		else if(tr < 0 || tr >= HEIGHT || tc < 0 || tc >= WIDTH)
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
		else if(board.getPieceAt(fr, fc).getPieceType() == PieceType.FLAG)
		{
			return true;
		}
		
		return false;
	}
	
	private PieceImpl movePiece(int fr, int fc, int tr, int tc)
	{
		PieceImpl aPiece = board.getPieceAt(fr, fc);
		board.removePiece(fr, fc);
		board.addPiece(aPiece, tr, tc);
		PieceImpl newPiece = board.getPieceAt(tr, tc);
		return newPiece;
	}
	
	
}
