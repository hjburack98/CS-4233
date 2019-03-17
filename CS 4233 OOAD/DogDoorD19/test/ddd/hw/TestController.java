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

import static ddd.hw.DogDoorController.DoorStatus.*;
import java.util.*;

/**
 * Description
 * @version Jan 31, 2019
 */
public class TestController implements DogDoorController
{
	private DoorStatus doorStatus;
	private Queue<DoorStatus> programmedStatus;
	
	/**
	 * Use this when you need to program the statuses; especially if the
	 * first status to be returned is not CLOSED.
	 * @param status the statuses to be returned, in order.
	 */
	public TestController(DoorStatus ... status)
	{
		programmedStatus = new LinkedList<DoorStatus>();
		for (DoorStatus s : status) {
			programmedStatus.add(s);
		}
		doorStatus = programmedStatus.remove();
	}
	
	/*
	 * @see ddd.hw.DogDoorController#openDoor()
	 */
	@Override
	public DoorStatus openDoor()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see ddd.hw.DogDoorController#closeDoor()
	 */
	@Override
	public DoorStatus closeDoor()
	{
		doorStatus = programmedStatus == null ? CLOSED : programmedStatus.remove();
		return doorStatus;
	}

	/*
	 * @see ddd.hw.DogDoorController#getDoorStatus()
	 */
	@Override
	public DoorStatus getDoorStatus()
	{
		return doorStatus;
	}
	
	/*
	 * @see ddd.hw.DogDoorController#reset()
	 */
	@Override
	public DoorStatus reset()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	// helper methods
	public void programResults(DoorStatus ... status)
	{
		for (DoorStatus s : status) {
			programmedStatus.add(s);
		}
	}

	public DoorStatus nextStatus()
	{
		return programmedStatus.remove();
	}
	
}
