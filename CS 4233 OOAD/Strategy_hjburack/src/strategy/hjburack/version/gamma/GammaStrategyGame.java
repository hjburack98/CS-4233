package strategy.hjburack.version.gamma;

import strategy.hjburack.*;

//implements the BaseStrategy super class that contains the methods with toggles to pick features
public class GammaStrategyGame extends BaseStrategy
{
	/**
	 * instantiated the GAMMA Strategy
	 * @param board
	 */
	public GammaStrategyGame(BoardImpl board)
	{
		super(board);
		eightTurnLimitApplied = false;
		repetitionApplied = true;
		allPiecesApplied = false;
		depletingBombApplied = false;
		attackerAdvantageApplied = false; 
		scoutOrthogonalAttackApplied = false;
	}
		
}
