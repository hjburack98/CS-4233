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
package ocr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the OCR translator.
 * @version Mar 13, 2019
 */
class OCRTranslatorTest
{
	OCRTranslator translator = new OCRTranslator();
	
	@Test
	void emptyString() //1
	{
		assertEquals(translator.translate("","",""), "");
	}
	
	@Test
	void onlySpaces() //2
	{
		assertEquals(translator.translate(" ", " ", " "), "");
	}
	
	
	@Test
	void unevenSpacing()//3
	{
		assertEquals(translator.translate("", " ", ""), "");
	}
	
	@Test
	void sense1() //4
	{
		assertEquals(translator.translate(" ", "|", "|"), "1");
	}
	
	@Test
	void sense7() //5
	{
		assertEquals(translator.translate("_ ", " |", " |"), "7");
	}

}
