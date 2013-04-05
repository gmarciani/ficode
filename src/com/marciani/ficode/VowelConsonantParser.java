/*  FiCode: Android app for IT Fiscal Code calculation, with the support for 
    the omocodia and the generation of abroad codes.
    Copyright (C) 2013  Giacomo Marciani <giacomo.marciani@gmail.com>.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.marciani.ficode;


import java.util.ArrayList;
import java.util.List;


/**
 * This class provides specific methods for vowels and consonants parsing into Strings.
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class VowelConsonantParser {
	
	
	/**
	 * 
	 * @param str
	 * @return vowelList
	 */
	
	public static List<Character> parseVowels(String str) {
		List<Character> vowelList = new ArrayList<Character>();
		char curChr;
		int i;
		
		for(i = 0; i < str.length(); i ++) {
			curChr = str.charAt(i);
			if(isVowel(curChr)) {
				vowelList.add(curChr);
			}
		}	
		
		return vowelList;
	}
	
	
	/**
	 * 
	 * @param str
	 * @return consonantList
	 */
	
	public static List<Character> parseConsonants(String str) {
		List<Character> consonantList = new ArrayList<Character>();
		char curChr;
		int i;
		
		for(i = 0; i < str.length(); i ++) {
			curChr = str.charAt(i);
			if(isConsonant(curChr)) {
				consonantList.add(curChr);
			}
		}	
		
		return consonantList;
	}
	
	
	/**
	 * 
	 * @param chr
	 * @return isVowel
	 */
	
	public static boolean isVowel(char chr) {
		boolean isVowel;
		
		chr = Character.toUpperCase(chr);
		
		if(chr == 'A' | chr == 'E' | chr == 'I' | chr == 'O' | chr == 'U') {
			isVowel = true;
		} else {
			isVowel = false;
		}
		
		return isVowel;
	}
	
	
	/**
	 * 
	 * @param chr
	 * @return isConsonant
	 */
	
	public static boolean isConsonant(char chr) {
		boolean isConsonant;
		
		chr = Character.toUpperCase(chr);
		
		if(chr == 'B' | chr == 'C' | chr == 'D' | chr == 'F' | chr == 'G' | 
				chr == 'H' | chr == 'J' | chr == 'K' | chr == 'L' | chr == 'M' | 
				chr == 'N' | chr == 'P' | chr == 'Q' | chr == 'R' | chr == 'S' | 
				chr == 'T' | chr == 'V' | chr == 'W' | chr == 'X' | chr == 'Y' | 
				chr == 'Z') {
			isConsonant = true;
		} else {
			isConsonant = false;
		}
		
		return isConsonant;		
	}
}
