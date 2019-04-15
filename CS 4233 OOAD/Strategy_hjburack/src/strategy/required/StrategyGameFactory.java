/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package strategy.required;

import strategy.*;
import strategy.StrategyGame.Version;
import strategy.hjburack.BoardImpl;
import strategy.hjburack.version.alpha.AlphaStrategyGame;
import strategy.hjburack.version.beta.BetaStrategyGame;
import strategy.hjburack.version.gamma.GammaStrategyGame;
import static strategy.StrategyGame.*;

/**
 * Factory for creating Strategy games.
 * @version Mar 18, 2019
 */
public class StrategyGameFactory
{
	public static StrategyGame makeGame(Version version, Board board)
	{
		StrategyGame game;
		switch (version)
		{
			case ALPHA:					// No need for the board
				game = new AlphaStrategyGame();
				break;
			case BETA:
				BoardImpl betaBoard = BoardImpl.convertBoard(board, 6, 6);
				game = new BetaStrategyGame(betaBoard);
				break;
				
			case GAMMA:
				BoardImpl gammaBoard = BoardImpl.convertBoard(board, 6, 6);
				game = new GammaStrategyGame(gammaBoard);
				break;
				
			default:
				throw new NotImplementedException(
						"StrategyGameFactory.makeGame for version " + version);
		}
		return game;
	}
}
