package strategy.hjburack.version.beta;

import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.MoveResult.*;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;


public class BetaStrategyGame extends BaseStrategy
{
	/**
	 * instantiated the BETA Strategy
	 * @param board
	 */
	public BetaStrategyGame(BoardImpl board)
	{
		super(board);
		repetitionApplied = false;
	}
	
}

