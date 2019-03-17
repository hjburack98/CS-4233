/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/
package ddd;

/**
 * Runtime excption used by the Dog Dor system.
 * @version Feb 2, 2019
 */
public class DogDoorException extends RuntimeException
{
	/**
	 * Only descriptor has a required message.
	 * @param message the text of the message
	 */
	public DogDoorException(String message)
	{
		super(message);
	}
}
