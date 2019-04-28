package strategy.hjburack.version.beta;

import strategy.hjburack.*;

//implements the BaseStrategy super class that contains the methods with toggles to pick features
public class BetaStrategyGame extends BaseStrategy
{
	/**
	 * instantiated the BETA Strategy
	 * @param board
	 */
	public BetaStrategyGame(BoardImpl board)
	{
		super(board);
		eightTurnLimitApplied = true;
		repetitionApplied = false;
		allPiecesApplied = false;
		depletingBombApplied = false;
		attackerAdvantageApplied = false;
		scoutOrthogonalAttackApplied = false;
	}
	
}

