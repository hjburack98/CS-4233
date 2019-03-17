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

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ddd.hw.TestController;
import ddd.hw.DogDoorController.DoorStatus;

import static ddd.hw.DogDoorController.DoorStatus.*;

/**
 * Test cases for the DogDoor class.
 * @version Jan 29, 2019
 */
class DogDoorTest
{
	@Test
	void dogDoorOnStartupFailsFourTimes()
	{
		TestController tc = new TestController(ERROR, ERROR, ERROR, ERROR);
		assertThrows(DogDoorException.class, () -> new DogDoor(tc));
	}
	
	@ParameterizedTest
	@MethodSource("initializationProvider")
	void initializationTest(int dummy, DoorStatus ...statuses)
	{
		final DogDoor dogDoor = new DogDoor(new TestController(statuses));
		assertTrue(dogDoor.isClosed());
	}
	
	// Helpers
	static Stream<Arguments> initializationProvider()
	{
		return Stream.of(
			Arguments.of(0, toArray(CLOSED)),
			Arguments.of(1, toArray(ERROR, CLOSED)),
			Arguments.of(2, toArray(ERROR, ERROR, CLOSED)),
			Arguments.of(3, toArray(ERROR, ERROR, ERROR, CLOSED))
		);
	}
	
	static DoorStatus[] toArray(DoorStatus ... statuses)
	{
		return statuses;
	}
}
