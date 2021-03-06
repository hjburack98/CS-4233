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
		if(s1.length() != s2.length() || s2.length() != s3.length() || s1.length() != s3.length())
		{
			throw new OCRException("OCR Strings are not the same length");
		}
		return processSpace(s1, s2, s3);
	}
	
	private String processSpace(String s1, String s2, String s3)
	{
		int index = 0;
		int subStart = 0;
		int subEnd = 0;
		String hold1 = "";
		String hold2 = "";
		String hold3 = "";
		String returnString = "";
		
		while(index < s1.length())
		{
			if(isSpace(s1.charAt(index), s2.charAt(index), s3.charAt(index)))
			{
				index++;
			}
			
			else
			{
				subStart = index;
				while(!isSpace(s1.charAt(index), s2.charAt(index), s3.charAt(index)))
				{
					index++;
					subEnd = index;
					if(index == s1.length())
					{
						break;
					}
				}
				
				hold1 = s1.substring(subStart, subEnd);
				hold2 = s2.substring(subStart, subEnd);
				hold3 = s3.substring(subStart, subEnd);
				
				returnString += search(hold1, hold2, hold3);
			}
		}
		
		return returnString;
	}
	
	/**
	 * Determines if the OCR Strings make up any number
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return the corresponding number, or nothing
	 */
	private String search(String s1, String s2, String s3)
	{
		String returnString = "";
		returnString+=(search1(s1, s2, s3));
		returnString+=(search2(s1, s2, s3));
		returnString+=(search3(s1, s2, s3));
		returnString+=(search4(s1, s2, s3));
		returnString+=(search5(s1, s2, s3));
		returnString+=(search6(s1, s2, s3));
		returnString+=(search7(s1, s2, s3));
		returnString+=(search8(s1, s2, s3));
		returnString+=(search9(s1, s2, s3));
		returnString+=(search0(s1, s2, s3));
		
		return returnString;
	}
	
	/**
	 * Determines if the OCR Strings make up the number 1
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 1 if the OCR makes a 1, or nothing
	 */
	private String search1(String s1, String s2, String s3)
	{
		if(s1.equals(" ") && s2.equals("|") && s3.equals("|"))
		{
			return("1");
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 2
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 2 if the OCR makes a 2, or nothing
	 */
	private String search2(String s1, String s2, String s3)
	{
		if(s1.equals(" _ ") && s2.equals(" _|") && s3.equals( "|_ "))
		{
			return "2";
		}
		
		return "";
	}

	/**
	 * Determines if the OCR Strings make up the number 3
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 3 if the OCR makes a 3, or nothing
	 */
	private String search3(String s1, String s2, String s3)
	{
		if(s1.equals("_ ") && s2.equals("_|") && s3.equals("_|"))
		{
			return "3";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 4
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 4 if the OCR makes a 4, or nothing
	 */
	private String search4(String s1, String s2, String s3)
	{
		if(s1.equals("   ") && s2.equals("|_|") && s3.equals("  |"))
		{
			return "4";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 5
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 5 if the OCR makes a 5, or nothing
	 */
	private String search5(String s1, String s2, String s3)
	{
		if(s1.equals(" _ ") && s2.equals("|_ ") && s3.equals(" _|"))
		{
			return "5";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 6
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 6 if the OCR makes a 6, or nothing
	 */
	private String search6(String s1, String s2, String s3)
	{
		if(s1.equals(" _ ") && s2.equals("|_ ") && s3.equals("|_|"))
		{
			return "6";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 7
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 7 if the OCR makes a 7, or nothing
	 */
	private String search7(String s1, String s2, String s3)
	{
		if(s1.equals("_ ") && s2.equals(" |") && s3.equals(" |"))
		{
			return "7";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 8
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 8 if the OCR makes a 8, or nothing
	 */
	private String search8(String s1, String s2, String s3)
	{
		if(s1.equals(" _ ") && s2.equals("|_|") && s3.equals("|_|"))
		{
			return "8";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 9
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 9 if the OCR makes a 9, or nothing
	 */
	private String search9(String s1, String s2, String s3)
	{
		if(s1.equals(" _ ") && s2.equals("|_|") && s3.equals(" _|"))
		{
			return "9";
		}
		
		return "";
	}
	
	/**
	 * Determines if the OCR Strings make up the number 0
	 * @param s1 first line of OCR
	 * @param s2 second line of OCR	
	 * @param s3 third line of OCR
	 * @return 0 if the OCR makes a 0, or nothing
	 */
	private String search0(String s1, String s2, String s3)
	{
		if(s1.equals( " _ ") && s2.equals("| |") && s3.equals("|_|"))
		{
			return "0";
		}
		
		return "";
	}
	
	/**
	 * Determines if the given characters make up a space (" ")
	 * @param s1 character from first line of OCR
	 * @param s2 character from second line of OCR
	 * @param s3 character from third line of OCR
	 * @return true if the characters make a space
	 */
	private boolean isSpace(char s1, char s2, char s3)
	{
		if(s1 == ' ' && s2 == ' ' && s3 == ' ')
		{
			return true;
		}
		
		return false;
	}
	
	
	
}
