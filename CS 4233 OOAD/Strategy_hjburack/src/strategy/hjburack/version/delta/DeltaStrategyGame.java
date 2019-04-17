package strategy.hjburack.version.delta;

import strategy.StrategyGame;
import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.MoveResult.*;
import java.util.Map;
import strategy.Board.SquareType;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.StrategyGame.MoveResult;

public class DeltaStrategyGame extends BaseStrategy
{
	
	/**
	 * instantiated the GAMMA Strategy
	 * @param board
	 */
	public DeltaStrategyGame(BoardImpl board)
	{
		super(board);
		repetitionApplied = true;
		allPiecesApplied = true;
	}

	
}
