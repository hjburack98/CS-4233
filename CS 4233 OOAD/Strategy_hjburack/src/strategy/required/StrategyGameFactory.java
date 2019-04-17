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
import strategy.Board.SquareType;
import strategy.StrategyGame.Version;
import strategy.hjburack.BoardImpl;
import strategy.hjburack.CoordinateImpl;
import strategy.hjburack.PieceImpl;
import strategy.hjburack.version.alpha.AlphaStrategyGame;
import strategy.hjburack.version.beta.BetaStrategyGame;
import strategy.hjburack.version.delta.DeltaStrategyGame;
import strategy.hjburack.version.gamma.GammaStrategyGame;
import static strategy.StrategyGame.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating Strategy games.
 * @version Mar 18, 2019
 */
public class StrategyGameFactory
{
	//gamma choke point creation
	static Map<CoordinateImpl, SquareType> gammaChokeMap = new HashMap<CoordinateImpl, SquareType>();
	static ArrayList<CoordinateImpl> gammaChokeCoordinates = new ArrayList<CoordinateImpl>();
	static CoordinateImpl choke1 = new CoordinateImpl(2,2);
	static CoordinateImpl choke2 = new CoordinateImpl(2,3);
	static CoordinateImpl choke3 = new CoordinateImpl(3,2);
	static CoordinateImpl choke4 = new CoordinateImpl(3,3);
	
	//delta choke point creation
	Map<CoordinateImpl, SquareType> deltaChokeMap = new HashMap<CoordinateImpl, SquareType>();
	
	
	
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
				
			case DELTA:
				BoardImpl deltaBoard = BoardImpl.convertBoard(board, 10, 10);
				game = new DeltaStrategyGame(deltaBoard);
				break;
				
			default:
				throw new NotImplementedException(
						"StrategyGameFactory.makeGame for version " + version);
		}
		return game;
	}
	
	private static void makeGammaChokeBoard(BoardImpl board)
	{
		gammaChokeCoordinates.add(choke1);
		gammaChokeCoordinates.add(choke2);
		gammaChokeCoordinates.add(choke3);
		gammaChokeCoordinates.add(choke4);
		
		for(int i = 0; i < board.getHeight(); i++)
		{
			for(int j = 0; j < board.getWidth(); j++)
			{
				CoordinateImpl currentCoordinate = new CoordinateImpl(i,j);
				if(gammaChokeCoordinates.contains(currentCoordinate))
				{
					gammaChokeMap.put(currentCoordinate, SquareType.CHOKE);
				}
				else
				{
					gammaChokeMap.put(currentCoordinate, SquareType.NORMAL);
				}
			}
		}
	}
}
