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

import java.util.function.BooleanSupplier;
import ddd.hw.DogDoorController;
import static ddd.hw.DogDoorController.DoorStatus.*;

/**
 * This is the main class for the Doug's Dog Door system that represents the 
 * door behavior.
 * @version Jan 29, 2019
 */
public class DogDoor
{
	private final int MAX_RETRIES = 3;
	private final DogDoorController controller;
	
	public DogDoor(DogDoorController controller)
	{
		this.controller = controller;
		int retries = 0;
		while (controller.getDoorStatus() != CLOSED && retries++ < MAX_RETRIES) {
			controller.closeDoor();
		}
		if (controller.getDoorStatus() != CLOSED) {
			throw new DogDoorException("Door is not closed. "
					+ "Try restarting the system or call service");
		}
	}
	
	/**
	 * @return true if the door is closed
	 */
	public boolean isClosed()
	{
		return controller.getDoorStatus() == CLOSED;
	}

}
