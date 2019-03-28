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
	void sense1() //3
	{
		assertEquals(translator.translate(" ", "|", "|"), "1");
	}
	
	@Test
	void sense7() //4
	{
		assertEquals(translator.translate("_ ", " |", " |"), "7");
	}
	
	@Test
	void sense0() //5
	{
		assertEquals(translator.translate(" _ ", "| |", "|_|"), "0");
	}
	
	@Test
	void sense8() //6
	{
		assertEquals(translator.translate(" _ ", "|_|", "|_|"), "8");
	}
	
	@Test
	void sense9() //7
	{
		assertEquals(translator.translate(" _ ", "|_|", " _|"), "9");
	}
	
	@Test
	void sense6() //8
	{
		assertEquals(translator.translate(" _ ", "|_ ", "|_|"), "6");
	}
	
	@Test
	void sense3() //9
	{
		assertEquals(translator.translate("_ ", "_|", "_|"), "3");
	}
	
	@Test
	void sense2() //10
	{
		assertEquals(translator.translate(" _ ", " _|", "|_ "), "2");
	}
	
	@Test
	void sense5() //11
	{
		assertEquals(translator.translate(" _ ", "|_ ", " _|"), "5");
	}
	
	@Test
	void sense4() //12
	{
		assertEquals(translator.translate("   ", "|_|", "  |"), "4");
	}
	
	
	@Test
	void withLeadingSpace() //13
	{
		assertEquals(translator.translate("  ", " |", " |"), "1");
	}
	
	@Test
	void withTrailingSpace() //14
	{
		assertEquals(translator.translate(" _  ", "| | ", "|_| "), "0");
	}
	
	@Test
	void multipleNumbers() //15
	{
		assertEquals(translator.translate(" _  _  ", " _|  | ", " _|  | "),"37");
	}
	
	@Test
	void multipleSpaceBetween() //16
	{
		assertEquals(translator.translate("      _ ", "|_|   _|", "  |  |_ "), "42");
	}
	

}
