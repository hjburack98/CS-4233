package strategy.hjburack.version.gamma;

import strategy.StrategyGame;
import strategy.StrategyGame;
import strategy.hjburack.*;
import static strategy.StrategyGame.MoveResult.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import strategy.Board;
import strategy.Board.SquareType;
import strategy.Piece.PieceColor;
import strategy.Piece.PieceType;
import strategy.StrategyGame.MoveResult;


//implement a StrategyGame interface that will call different StrategyGame versions
public class GammaStrategyGame extends BaseStrategy
{
	/**
	 * instantiated the GAMMA Strategy
	 * @param board
	 */
	public GammaStrategyGame(BoardImpl board)
	{
		super(board);
		repetitionApplied = true;
		allPiecesApplied = false;
	}
		
}
