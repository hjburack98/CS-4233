package strategy.hjburack.version.delta;

import strategy.hjburack.*;

//implements the BaseStrategy super class that contains the methods with toggles to pick features
public class DeltaStrategyGame extends BaseStrategy
{
	/**
	 * instantiated the DELTA Strategy
	 * @param board
	 */
	public DeltaStrategyGame(BoardImpl board)
	{
		super(board);
		eightTurnLimitApplied = false;
		repetitionApplied = true;
		allPiecesApplied = true;
		depletingBombApplied = false;
		attackerAdvantageApplied = false;
		scoutDiagonalAttackApplied = false;
	}

	
}
