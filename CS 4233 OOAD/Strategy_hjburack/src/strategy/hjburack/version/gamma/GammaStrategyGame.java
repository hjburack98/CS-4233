package strategy.hjburack.version.gamma;

import strategy.StrategyGame;
import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.MoveResult.*;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.StrategyGame.MoveResult;

public class GammaStrategyGame implements StrategyGame
{
	private BoardImpl board;
	PieceColor turn; //determine's which player can move
	int moveCount = 1; //8 move limit
	boolean gameOver = false;
	
	/**
	 * instantiated the BETA Strategy
	 * @param board
	 */
	public GammaStrategyGame(BoardImpl board)
	{
		this.board = board;
		turn = PieceColor.RED;
	}
	
	/**
	 * will move a piece from one coordinate to the target coordinate
	 * @param the x coordinate of the piece that will be moved
	 * @param the y coordinate of the piece that will be moved
	 * @param the x coordinate of the target location
	 * @param the y coordinate of the target location
	 * @return the outcome of the requested move
	 */
	public MoveResult move(int fr, int fc, int tr, int tc)
	{
		//will always return game over if someone won the game
		if(gameOver == true)
		{
			return GAME_OVER;
		}
		
		//will automatically end the game if there have been more than 8 turns
		if(this.moveCount >= 8 && turn == PieceColor.BLUE)
		{
			gameOver = true;
			return RED_WINS;
		}
		
		//if move is invalid, the opponent will win the game
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
				//if the attacker is a greater rank than the one being attacked
				//	remove the target from the game
				if(board.getPieceAt(fr, fc).getRank() > board.getPieceAt(tr, tc).getRank())
				{
					MoveResult returnVal = this.getStrikeResult(board.getPieceAt(fr, fc).getPieceColor());
	
					//if the piece being striked is a flag, the attacker wins the game
					if(board.getPieceAt(tr, tc).getPieceType() == PieceType.FLAG) {
						return this.getWinner(board.getPieceAt(fr, fc).getPieceColor());
					}
					board.removePiece(tr, tc);
					this.movePiece(fr, fc, tr, tc);
						
					return returnVal;
						
				}
				//if the attacker is a lesser rank than the one being attacked
				//	remove the attacker
				else if(board.getPieceAt(fr, fc).getRank() < board.getPieceAt(tr, tc).getRank())
				{
					MoveResult returnVal = this.getStrikeResult(board.getPieceAt(tr, tc).getPieceColor());
					board.removePiece(fr, fc);
					this.movePiece(tr, tc, fr, fc);
					return returnVal;
					
				}
				
				//if they are the same rank, remove both pieces 
				else if(board.getPieceAt(fr, fc).getRank() == board.getPieceAt(tr, tc).getRank())
				{
					board.removePiece(fr, fc);
					board.removePiece(tr, tc);
					this.swapTurn();
					return OK;
				}
			}
		}
		
		return this.opponentWins(turn);
	}
	
	/**
	 * return's that the opponent's color wins the game
	 * @param team of the current player
	 * @return the move result that the opposing team wins
	 */
	private MoveResult opponentWins(PieceColor team)
	{
		//if team is red, return BLUE_WINS
		if(team == PieceColor.RED)
		{
			gameOver = true;
			return BLUE_WINS;
		}
		//if team is blue, return RED_WINS
		else
		{
			gameOver = true;
			return RED_WINS;
		}
	}

	/**
	 * determine;s if the given move is invalid
	 * @param fr row of the piece being moved
	 * @param fc column of the piece being moves
	 * @param tr row of the target square
	 * @param tc column of the target square
	 * @return true if the move given is invalid
	 */
	private boolean isInvalidMove(int fr, int fc, int tr, int tc)
	{
		//No piece in the selected spot
		if(board.getPieceAt(fr, fc) == null)
		{
			return true;
		}
		//"from" square is out of bounds
		else if(fr < 0 || fr >= board.getHeight() || fc < 0 || fc >= board.getWidth())
		{
			return true;
		}
		//"to" square is out of bounds
		else if(tr < 0 || tr >= board.getHeight() || tc < 0 || tc >= board.getHeight())
		{
			return true;
		}
		//target is more than one block away
		else if(Math.abs(tr-fr) > 1 || Math.abs(tc-fc) > 1)
		{
			return true;
		}
		//"from" and "to" are in the same spot
		else if(fr == tr && fc == tc)
		{
			return true;
		}
		//trying to move the flag
		else if(board.getPieceAt(fr, fc).getPieceType() == PieceType.FLAG)
		{
			return true;
		}
		//trying to move opponent's piece
		else if(board.getPieceAt(fr, fc).getPieceColor() != this.turn)
		{
			return true;
		}
		//making diagonal move
		else if(Math.abs(tr-fr) > 0 && Math.abs(tc-fc) > 0)
		{
			return true;
		}
		
		//return false if valid move
		return false;
	}
	
	/**
	 * changes which player it is in the game
	 */
	private void swapTurn()
	{
		//if red, return blue
		if(turn == PieceColor.RED)
		{
			turn = PieceColor.BLUE;
		}
		//if blue, return red
		else
		{
			moveCount++;
			turn = PieceColor.RED;
		}
	}
	
	/**
	 * actually changes the location of a piece
	 * @param fr row of the piece being moved
	 * @param fc column of the piece being moves
	 * @param tr row of the target square
	 * @param tc column of the target square
	 * @return a new piece made with the updated coordinates
	 */
	private PieceImpl movePiece(int fr, int fc, int tr, int tc)
	{
		PieceImpl aPiece = board.getPieceAt(fr, fc);
		board.removePiece(fr, fc);
		board.addPiece(aPiece, tr, tc);
		PieceImpl newPiece = board.getPieceAt(tr, tc);
		this.swapTurn();
		return newPiece;
	}
	
	/**
	 * returns the moveResult for the winning strike
	 * @param winner the color of the team that won the strike
	 * @return the MoveResult for that color's winning strike
	 */

	private MoveResult getStrikeResult(PieceColor strikeWinner)
	{
		//if strike winner is red, return STRIKE_RED
		if(strikeWinner == PieceColor.RED)
		{
			return STRIKE_RED;
		}
		//if strike winner is blue, return STRIKE_BLUE
		else
		{
			return STRIKE_BLUE;
		}
	}
	
	/**
	 * returns the MoveResult for the winner of the game
	 * @param winner the color of the winning team
	 * @return the MoveResult for that color winning the game
	 */
	private MoveResult getWinner(PieceColor winner)
	{
		//if winner is red, return RED_WINS
		if(winner == PieceColor.RED)
		{
			gameOver = true;
			return RED_WINS;
		}
		//if winner is blue, return BLUE_WINS
		else 
		{
			gameOver = true;
			return BLUE_WINS;
		}
	}
}
