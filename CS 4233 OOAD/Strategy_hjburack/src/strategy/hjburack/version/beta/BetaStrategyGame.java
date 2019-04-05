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
	PieceColor turn;
	int moveCount = 1;
	boolean gameOver = false;
	
	public BetaStrategyGame(BoardImpl board)
	{
		this.board = board;
		turn = PieceColor.RED;
	}
	
	public MoveResult move(int fr, int fc, int tr, int tc)
	{
		
		if(gameOver == true)
		{
			return GAME_OVER;
		}
		
		if(this.moveCount >= 8 && turn == PieceColor.BLUE)
		{
			gameOver = true;
			return RED_WINS;
		}
		
		if(this.isInvalidMove(fr, fc, tr, tc))
		{
			return this.opponentWins(turn);
		}
		
		//valid move
		if(board.getPieceAt(tr, tc) == null)
		{
			this.movePiece(fr, fc, tr, tc);
			return OK;
			
		}
		
		//Striking
		else
		{
			//if the piece at the target coordinate is your own, you lose
			if(board.getPieceAt(tr, tc).getPieceColor() == board.getPieceAt(fr, fc).getPieceColor())
			{
				return this.opponentWins(turn);
			}
			else
			{
				//must make sure that that the strike is only a vertical or horizontal move
				if((Math.abs(tr-fr) == 1 && tc-fc == 0) || (Math.abs(tc-fc) == 1 && tr-fr == 0))
				{
					if(board.getPieceAt(fr, fc).getRank() > board.getPieceAt(tr, tc).getRank())
					{
						MoveResult returnVal = this.getStrikeResult(board.getPieceAt(fr, fc).getPieceColor());
		
						if(board.getPieceAt(tr, tc).getPieceType() == PieceType.FLAG) {
							return this.getWinner(board.getPieceAt(fr, fc).getPieceColor());
						}
						board.removePiece(tr, tc);
						this.movePiece(fr, fc, tr, tc);
						
						return returnVal;
						
					}
					else if(board.getPieceAt(fr, fc).getRank() < board.getPieceAt(tr, tc).getRank())
					{
						MoveResult returnVal = this.getStrikeResult(board.getPieceAt(tr, tc).getPieceColor());
						board.removePiece(fr, fc);
						this.movePiece(tr, tc, fr, fc);
						return returnVal;
						
					}
					//if they are the same rank, remove both pieces
					else
					{
						board.removePiece(fr, fc);
						board.removePiece(tr, tc);
						this.swapTurn();
						return OK;
					}
				}
				else
				{
					return this.opponentWins(turn);
				}
			}
		}
	}
	
	private MoveResult opponentWins(PieceColor team)
	{
		if(team == PieceColor.RED)
		{
			gameOver = true;
			return BLUE_WINS;
		}
		else
		{
			gameOver = true;
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
		else if(board.getPieceAt(fr, fc).getPieceColor() != this.turn)
		{
			return true;
		}
		
		return false;
	}
	
	private void swapTurn()
	{
		if(turn == PieceColor.RED)
		{
			turn = PieceColor.BLUE;
		}
		else
		{
			moveCount++;
			turn = PieceColor.RED;
		}
	}
	
	private PieceImpl movePiece(int fr, int fc, int tr, int tc)
	{
		PieceImpl aPiece = board.getPieceAt(fr, fc);
		board.removePiece(fr, fc);
		board.addPiece(aPiece, tr, tc);
		PieceImpl newPiece = board.getPieceAt(tr, tc);
		this.swapTurn();
		return newPiece;
	}
	
	private MoveResult getStrikeResult(PieceColor winner)
	{
		if(winner == PieceColor.RED)
		{
			return STRIKE_RED;
		}
		
		else
		{
			return STRIKE_BLUE;
		}
	}
	
	private MoveResult getWinner(PieceColor winner)
	{
		if(winner == PieceColor.RED)
		{
			gameOver = true;
			return RED_WINS;
		}
		
		else 
		{
			gameOver = true;
			return BLUE_WINS;
		}
	}

}

