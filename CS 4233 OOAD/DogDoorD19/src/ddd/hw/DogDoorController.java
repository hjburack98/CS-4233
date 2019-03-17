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
package ddd.hw;

import ddd.*;

/**
 * This interface describes the behavior that the Dog Door software requires from
 * the hardware provided.
 * @version Jan 31, 2019
 */
public interface DogDoorController
{
	enum DoorStatus {OPEN, CLOSED, ERROR};
	
	/**
	 * Open the door
	 * @return the DoorStatus after the operation completes
	 */
	DoorStatus openDoor();
	
	/**
	 * Close the door.
	 * @return the DoorStatus after the operation completes
	 */
	DoorStatus closeDoor();
	
	/**
	 * @return the current door status
	 */
	DoorStatus getDoorStatus();
	
	/**
	 * Reset the door to an initialized state.
	 * @return
	 */
	DoorStatus reset();
}
