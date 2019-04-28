package strategy.hjburack.version.epsilon;

import strategy.hjburack.*;

//implements the BaseStrategy super class that contains the methods with toggles to pick features
public class EpsilonStrategyGame extends BaseStrategy
{
	/**
	 * instantiated the EPSILON Strategy
	 * @param board
	 */
	public EpsilonStrategyGame(BoardImpl board)
	{
		super(board);
		eightTurnLimitApplied = false;
		repetitionApplied = true;
		allPiecesApplied = true;
		depletingBombApplied = true;
		attackerAdvantageApplied = true;
		scoutOrthogonalAttackApplied = true;
	}

}
