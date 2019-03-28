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

/**
 * This class has a single method that will translate OCR digits to a string of
 * text digits that correspond to the OCR digits.
 * 
 * @version Mar 13, 2019
 */
public class OCRTranslator
{
	

	/**
	 * Default constructor. You may not add parameters to this. This is
	 * the only constructor for this class and is what the master tests will use.
	 */
	public OCRTranslator()
	{

	}
	
	/**
	 * Translate a string of OCR digits to a corresponding string of text
	 * digits. OCR digits are represented as three rows of characters (|, _, and space).
	 * @param s1 the top row of the OCR input
	 * @param s2 the middle row of the OCR input
	 * @param s3 the third row of the OCR input
	 * @return a String containing the digits corresponding
	 */
	public String translate(String s1, String s2, String s3)
	{		
		return search(s1, s2, s3);
	}
	
	
	private String search(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		returnString.append(search1(s1, s2, s3));
		returnString.append(search2(s1, s2, s3));
		returnString.append(search3(s1, s2, s3));
		returnString.append(search4(s1, s2, s3));
		returnString.append(search5(s1, s2, s3));
		returnString.append(search6(s1, s2, s3));
		returnString.append(search7(s1, s2, s3));
		returnString.append(search8(s1, s2, s3));
		returnString.append(search9(s1, s2, s3));
		returnString.append(search0(s1, s2, s3));
		
		return returnString.toString();
	}
	
	private String search1(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " " && s2 == "|" && s3 == "|")
		{
			returnString.append("1");
		}
		
		return returnString.toString();
	}
	
	private String search2(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " _ " && s2 == " _|" && s3 == "|_ ")
		{
			returnString.append("2");
		}
		
		return returnString.toString();
	}
	
	private String search3(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == "_ " && s2 == "_|" && s3 == "_|")
		{
			returnString.append("3");
		}
		
		return returnString.toString();
	}
	
	private String search4(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == "   " && s2 == "|_|" && s3 == "  |")
		{
			returnString.append("4");
		}
		
		return returnString.toString();
	}
	
	private String search5(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " _ " && s2 == "|_ " && s3 == " _|")
		{
			returnString.append("5");
		}
		
		return returnString.toString();
	}
	
	private String search6(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " _ " && s2 == "|_ " && s3 == "|_|")
		{
			returnString.append("6");
		}
		
		return returnString.toString();
	}
	
	private String search7(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == "_ " && s2 == " |" && s3 == " |")
		{
			returnString.append("7");
		}
		
		return returnString.toString();
	}
	
	private String search8(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " _ " && s2 == "|_|" && s3 == "|_|")
		{
			returnString.append("8");
		}
		
		return returnString.toString();
	}
	
	private String search9(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " _ " && s2 == "|_|" && s3 == " _|")
		{
			returnString.append("9");
		}
		
		return returnString.toString();
	}
	
	private String search0(String s1, String s2, String s3)
	{
		StringBuilder returnString = new StringBuilder();
		if(s1 == " _ " && s2 == "| |" && s3 == " |_| ")
		{
			returnString.append("0");
		}
		
		return returnString.toString();
	}
	
	private boolean isSpace(String s1, String s2, String s3)
	{
		if(s1 == " " && s2 == " " && s3 == " ")
		{
			return true;
		}
		
		return false;
	}
	
	
	
}
