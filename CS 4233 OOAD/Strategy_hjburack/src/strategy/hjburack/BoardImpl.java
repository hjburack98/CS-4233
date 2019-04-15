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
import strategy.Piece;
import strategy.Piece.*;
import strategy.hjburack.*;

/**
 * Description
 * @version Mar 18, 2019
 */
public class BoardImpl implements Board
{

	private Map<CoordinateImpl, PieceImpl> board;
	private int width;
	private int height;
	
	/**
	 * create a board as a hashmap
	 */
	public BoardImpl(Map<CoordinateImpl, PieceImpl> board )
	{
		this.board = board;
	}
	
	/**
	 * converts the board from any layout of <<Interface>> board to BoardImpl
	 * @param the Board interface object
	 * @param the width of the board
	 * @param the height of the board
	 * @return a BoardImpl copy of the Board interface given
	 */
	public static BoardImpl convertBoard(Board aBoard, int width, int height)
	{
		Map<CoordinateImpl, PieceImpl> board = new HashMap<>();
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				CoordinateImpl coordinate = new CoordinateImpl(x,y);
				PieceImpl scannedPiece = PieceImpl.convertPiece(aBoard.getPieceAt(x, y));
				board.put(coordinate, scannedPiece);
			}
		}
		
		BoardImpl convertedBoard = new BoardImpl(board);
		convertedBoard.width = width;
		convertedBoard.height = height;
		return convertedBoard;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	/**
	 * removes a piece from the hashmap
	 * @param x coordinate of the piece to be removed
	 * @param y coordinate of the piece to be removed
	 */
	public void removePiece(int x, int y)
	{
		PieceImpl aPiece = this.getPieceAt(x, y);
		this.board.remove(new CoordinateImpl(x,y), aPiece);
	}
	
	/**
	 * adds a new PieceImpl to the BoardImpl hashmap
	 * @param aPiece the PieceImpl that will be added
	 * @param x coordinate of the piece to be added
	 * @param y coordinate of the piece to be added
	 */
	public void addPiece(PieceImpl aPiece, int x, int y)
	{
		this.board.put(new CoordinateImpl(x, y), aPiece);
	}

	/**
	 * get the piece at a specific location
	 * @param the row of the target piece
	 * @param the column of the target piece
	 * @return the piece that is at the given row and column
	 */
	@Override
	public PieceImpl getPieceAt(int row, int column)
	{
		return this.board.get(new CoordinateImpl(row, column));
	}

	/**
	 * get the type of square at a specific location
	 * @param the row of the target piece
	 * @param the column of the target piece
	 * @return the type of the square that is at the given row and column
	 */
	@Override
	public SquareType getSquareTypeAt(int row, int column)
	{
		if(this.getSquareTypeAt(row, column) == SquareType.CHOKE)
		{
			return Board.SquareType.CHOKE;
		}
		
		return SquareType.NORMAL;
	}

	
}
