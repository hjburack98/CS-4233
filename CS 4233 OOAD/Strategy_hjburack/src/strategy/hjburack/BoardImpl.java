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

package strategy.hjburack;

import java.util.*;
import strategy.Board;
import strategy.hjburack.*;

/**
 * Description
 * @version Mar 18, 2019
 */
public class BoardImpl implements Board
{

	private Map<CoordinateImpl, PieceImpl> board;
	
	/**
	 * Description
	 */
	public BoardImpl(int width, int height, List<PieceImpl> redPiece, List<PieceImpl> bluePiece)
	{
		
	}
	
	/**
	 * Copy Constructor
	 */
	public static BoardImpl(Board aBoard, int width, int height)
	{
		Map<CoordinateImpl, PieceImpl> board;
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				CoordinateImpl coordinate = new CoordinateImpl(x,y);
				PieceImpl scannedPiece = new PieceImpl(aBoard.)
				board.put(coordinate, aBoard.getPieceAt(x, y));
			}
		}
	}

}
