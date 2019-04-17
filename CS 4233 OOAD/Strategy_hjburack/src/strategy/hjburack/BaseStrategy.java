package strategy.hjburack;

import static strategy.StrategyGame.MoveResult.BLUE_WINS;
import static strategy.StrategyGame.MoveResult.GAME_OVER;
import static strategy.StrategyGame.MoveResult.OK;
import static strategy.StrategyGame.MoveResult.RED_WINS;
import static strategy.StrategyGame.MoveResult.STRIKE_BLUE;
import static strategy.StrategyGame.MoveResult.STRIKE_RED;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.StrategyGame;
import strategy.Board.SquareType;
import strategy.StrategyGame.MoveResult;

public abstract class BaseStrategy implements StrategyGame
{
	protected BoardImpl board;
	protected PieceColor turn; //determine's which player can move
	protected int moveCount = 1; //8 move limit
	protected boolean gameOver = false;
	
	protected boolean allPiecesApplied;
	protected boolean repetitionApplied;
	
	//repetition rule
	//previous move location storage
		protected int fromRedRow = -1;
		protected int fromRedCol = -1;
		protected int toRedRow = -1;
		protected int toRedCol = -1;
		protected int fromBlueRow = -1;
		protected int fromBlueCol = -1;
		protected int toBlueRow = -1;
		protected int toBlueCol = -1;
		
		//counters for consecutive moves
		protected int redConsecutiveCount = 1;
		protected int blueConsecutiveCount = 1;
		
		protected boolean consecutiveMoveHit;
		
	
	/**
	 * instantiated the BETA Strategy
	 * @param board
	 */
	public BaseStrategy(BoardImpl board)
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
		
		consecutiveMoveHit = this.isConsecutiveMove(fr, fc, tr, tc);
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
			MoveResult returnVal;
			
			//if the piece at the target coordinate is your own, you lose
			if(board.getPieceAt(tr, tc).getPieceColor() == board.getPieceAt(fr, fc).getPieceColor())
			{
				return this.opponentWins(turn);
			}
			if(allPiecesApplied == true)
			{
				//if you are striking a bomb
				if(board.getPieceAt(tr, tc).getPieceType() == PieceType.BOMB)
				{
					//if the player striking the bomb is a miner
					if(board.getPieceAt(fr, fc).getPieceType() == PieceType.MINER)
					{
						returnVal = this.getStrikeResult(board.getPieceAt(fr, fc).getPieceColor());
						board.removePiece(tr, tc);
						this.movePiece(fr, fc, tr, tc);
						
						return returnVal;
					}
					
					returnVal = this.getStrikeResult(board.getPieceAt(tr, tc).getPieceColor());
					board.removePiece(fr, fc);
					board.removePiece(tr, tc); //TODO: GET CONFIRMATION
					this.swapTurn();
					
					return returnVal;
				}
				
				//if a spy is striking
				else if(board.getPieceAt(fr, fc).getPieceType() == PieceType.SPY)
				{
					if(board.getPieceAt(tr, tc).getPieceType() == PieceType.MARSHAL)
					{
						returnVal = this.getStrikeResult(board.getPieceAt(fr, fc).getPieceColor());
						board.removePiece(tr, tc);
						this.movePiece(fr, fc, tr, tc);
						
						return returnVal;
					}
				}
			}

			//if the attacker is a greater rank than the one being attacked
			//	remove the target from the game
			if(board.getPieceAt(fr, fc).getRank() > board.getPieceAt(tr, tc).getRank())
			{
				returnVal = this.getStrikeResult(board.getPieceAt(fr, fc).getPieceColor());
	
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
			if(board.getPieceAt(fr, fc).getRank() < board.getPieceAt(tr, tc).getRank())
			{
				returnVal = this.getStrikeResult(board.getPieceAt(tr, tc).getPieceColor());
				board.removePiece(fr, fc);
				this.movePiece(tr, tc, fr, fc);
				return returnVal;
				
			}
				
			//if they are the same rank, remove both pieces 
			if(board.getPieceAt(fr, fc).getRank() == board.getPieceAt(tr, tc).getRank())
			{
				board.removePiece(fr, fc);
				board.removePiece(tr, tc);
				this.swapTurn();
				return OK;
			}
		}
		
	return this.opponentWins(turn);
	}
	
	/**
	 * return's that the opponent's color wins the game
	 * @param team of the current player
	 * @return the move result that the opposing team wins
	 */
	protected MoveResult opponentWins(PieceColor team)
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
	protected boolean isInvalidMove(int fr, int fc, int tr, int tc)
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
		else if(tr < 0 || tr >= board.getHeight() || tc < 0 || tc >= board.getWidth())
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
		//making the same consistent move
		else if(consecutiveMoveHit == true && repetitionApplied == true)
		{
			return true;
		}
		//try to move into a choke position
		else if(board.getSquareTypeAt(tr, tc) == SquareType.CHOKE)
		{
			return true;
		}
		//target is more than one block away
		else if(Math.abs(tr-fr) > 1 || Math.abs(tc-fc) > 1)
		{
		
			if(allPiecesApplied == true && board.getPieceAt(fr, fc).getPieceType() == PieceType.SCOUT)
			{
				if(this.checkInterference(fr, fc, tr, tc) == true)
				{
					return true;
				}
			}
			
			//if not all pieces are applied
			else
			{
				return true;
					
			}
		}
		
		//return false if valid move
		return false;
	}
	
	/**
	 * changes which player it is in the game
	 */
	protected void swapTurn()
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
	protected PieceImpl movePiece(int fr, int fc, int tr, int tc)
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

	protected MoveResult getStrikeResult(PieceColor strikeWinner)
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
	protected MoveResult getWinner(PieceColor winner)
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
	
	/**
	 * 
	 * @param fr
	 * @param fc
	 * @param tr
	 * @param tc
	 * @return
	 */
	protected boolean isConsecutiveMove(int fr, int fc, int tr, int tc)
	{
		//red piece color
		if(turn == PieceColor.RED)
		{
			if(board.getPieceAt(tr, tc) != null)
			{
				redConsecutiveCount = 0;
			}
			
			//if the current move is the same as the previous, increment and check repetition
			else if(fr == toRedRow && fc == toRedCol && tr == fromRedRow && tc == fromRedCol)
			{
				redConsecutiveCount++;
				
				//if the game hits the repetition rule, return true
				if(redConsecutiveCount == 3)
				{
					return true;
				}
			}
			
			//if it is a normal move, make the correct move counter 1
			else
			{
				redConsecutiveCount = 1;
			}
			
			fromRedRow = fr;
			fromRedCol = fc;
			toRedRow = tr;
			toRedCol = tc;
		}
		
		//blue piece color
		else
		{
			if(board.getPieceAt(tr, tc) != null)
			{
				blueConsecutiveCount = 0;
			}
			
			//if the current move is the same as the previous, increment and check repetition
			else if(fr == toBlueRow && fc == toBlueCol && tr == fromBlueRow && tc == fromBlueCol)
			{
				blueConsecutiveCount++;
				
				//if the game hits the repetition rule, return true
				if(blueConsecutiveCount == 3)
				{
					return true;
				}
			}
			
			//if it is a normal move, make the correct move counter 1
			else
			{
				blueConsecutiveCount = 1;
			}
			
			fromBlueRow = fr;
			fromBlueCol = fc;
			toBlueRow = tr;
			toBlueCol = tc;
		}
		
		return false;
	}
	
	/**
	 * checks if there is any interference when a scout moves more than one space
	 * @param fr origin row
	 * @param fc origin column
	 * @param tr target row
	 * @param tc target column
	 * @return return true if a piece is in the piece's path
	 */
	private boolean checkInterference(int fr, int fc, int tr, int tc)
	{
		int vertDiff = Math.abs(fr-tr);
		int horDiff = Math.abs(fc-tc);
		
		//the origin and target are in the same row
		if(vertDiff == 0)
		{
			//if the target is to the right of the origin
			if(tc - fc > 0)
			{
				for(int i = fc+1; i <= tc; i++)
				{
					if(board.getPieceAt(fr, i) != null)
					{
						return true;
					}
				}
			}
			
			//if the target is to the left of the origin
			else
			{
				for(int i = fc - 1; i >= tc; i--)
				{
					if(board.getPieceAt(fr, i) != null)
					{
						return true;
					}
				}
			}
		}
		
		//the origin and the target are in the same column
		if(horDiff == 0)
		{
			//if the target is above the origin
			if(tr-fr > 0)
			{
				for(int i = fr+1; i <= tr; i++)
				{
					if(board.getPieceAt(i, fc) != null)
					{
						return true;
					}
				}
			}
			
			//if the target is below the origin
			else
			{
				for(int i = fr-1; i >= tr; i--)
				{
					if(board.getPieceAt(i, fc) != null)
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
