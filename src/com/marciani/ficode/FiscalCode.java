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


import static com.marciani.ficode.VowelConsonantParser.*;
import java.util.List;
import java.util.Map;


/**
 * This class ....
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 */

public class FiscalCode {
	
	public static class FiscalCodeMap {
		protected static final Map<Integer, String> monthMap = com.marciani.ficode.CodeMaps.monthMap;
		protected static final Map<String, Integer> evenMap = com.marciani.ficode.CodeMaps.evenMap;
		protected static final Map<String, Integer> oddMap = com.marciani.ficode.CodeMaps.oddMap;
		protected static final Map<Integer, String> restMap = com.marciani.ficode.CodeMaps.restMap;
		protected static final Map<Integer, String> omocodiaMap = com.marciani.ficode.CodeMaps.omocodiaMap;		
	}
	
	
	/**
	 * Encodes the lastname, according to the regulatory D.M. 13813/1976.
	 * @param lastname The lastname to be encoded.
	 * @return codeLastname The encoding of the lastname.
	 */
	
	public static String codeLastname(String lastname) {
		if(lastname.equals("")) {
			return "***";
		} else {
			String codeLastname = new String();
			char curChr;
			int i = 0;
			int countConsonants = 0;
			int countVowels = 0;
			int tot = 0;
			
			for(i = 0, countConsonants = 0; i < lastname.length() & countConsonants < 3; i++) {
				curChr = lastname.charAt(i);
				
				if(curChr == ' ') {
					continue;
				} else if(isConsonant(curChr)) {
					codeLastname += curChr;
					countConsonants++;
				}
			}
			
			if(countConsonants<3) {
				for(i = 0, countVowels = 0; i < lastname.length() & countVowels < 3 - countConsonants; i++) {
					curChr = lastname.charAt(i);
					
					if(curChr == ' ') {
						continue;
					} else if(isVowel(curChr)) {
						codeLastname += curChr;
						countVowels++;
					}
				}
			}
			
			tot = countConsonants+countVowels;
			
			if(tot<3) {
				for(i = tot; i <= 3; i++) {
					codeLastname += "X";
				}
			}
			
			return codeLastname.toUpperCase();			
		}		
	}

	
	/**
	 * Encodes the firstname, according to the regulatory D.M. 13813/1976.
	 * @param firstname
	 * @return codeFirstname
	 */
	
	public static String codeFirstname(String firstname) {
		if(firstname.equals("")) {
			return "***";
		} else {
			String codeFirstname = new String();
			List<Character> listConsonants = parseConsonants(firstname);
			
			if(listConsonants.size() >= 4) {
				codeFirstname += listConsonants.get(0);
				codeFirstname += listConsonants.get(2);
				codeFirstname += listConsonants.get(3);			
			} else if(listConsonants.size() <= 3) {
				int i = 0;
				int countConsonants = 0;
				int countVowels = 0;
				int tot = 0;
				
				for(i = 0; i < listConsonants.size(); i++) {
					codeFirstname += listConsonants.get(i);
					countConsonants ++;
				}
				
				if(countConsonants <= 3) {
					List<Character> listVowels = parseVowels(firstname);
					for(i = 0; i < listVowels.size() & countConsonants + countVowels < 3; i++) {
						codeFirstname += listVowels.get(i);
						countVowels ++;
					}
					
					tot = countConsonants + countVowels;
					
					if(tot < 3) {
						for(i = tot; i <= 3; i++) {
							codeFirstname += "X";
						}
					}					
				}							
			}
			
			return codeFirstname.toUpperCase();			
		}		
	}

	
	/**
	 * Encodes the year of birth, according to the regulatory D.M. 13813/1976.
	 * @param year
	 * @return codeYear
	 */
	
	public static String codeYear(int year) {
		if(year == -1) {
			return "**";
		} else {
			String codeYear = new String();
			
			codeYear = String.valueOf(year % 100);
			
			return codeYear;			
		}		
	}
	
	
	/**
	 * Encodes the month of birth, according to the regulatory D.M. 13813/1976.
	 * @param month
	 * @return codeMonth
	 */
	
	public static String codeMonth(int month) {
		if(month == -1) {
			return "**";
		} else {
			String codeMonth = new String();
			
			codeMonth = FiscalCodeMap.monthMap.get(month);
			
			return codeMonth;
		}		
	}

	
	/**
	 * Encodes the day of birth in addiction of the gender, according to the regulatory D.M. 13813/1976.
	 * @param day
	 * @param gender
	 * @return codeDay
	 */
	
	public static String codeDay(int day, char gender) {
		if(day == -1 | gender == '#') {
			return "**";
		} else {
			String codeDay = new String();
			int tempDay = day;
		
			if(gender == 'F') {
				tempDay += 40;
			}
		
			if(tempDay <= 9) {
				codeDay += "0";
			}
		
			codeDay += String.valueOf(tempDay);		
		
			return codeDay;
		}
	}
	
	
	/**
	 * Encodes the control code in addiction of the partial fiscal code, according to the regulatory D.M. 13813/1976.
	 * @param partialFiscalCode
	 * @return codeControl
	 */
	
	public static String codeControl(String partialFiscalCode) {
		if(partialFiscalCode.indexOf('*') != -1) {
			return "*";
		} else {
			String codeControl = new String();	
			int tempTot = 0;
			int i = 0;
			
			for(i = 0; i < 14; i++) {
				if(partialFiscalCode.charAt(i) == '*') {
					continue;
				} else if((i + 1) % 2 == 0) {
					tempTot += FiscalCodeMap.evenMap.get(String.valueOf(partialFiscalCode.toUpperCase().charAt(i)));
				} else {
					tempTot += FiscalCodeMap.oddMap.get(String.valueOf(partialFiscalCode.toUpperCase().charAt(i)));
				}
			}
			
			codeControl = FiscalCodeMap.restMap.get(tempTot%26);		
			
			return codeControl;
		}		
	}

	
	/**
	 * Generates the fiscal code merging the partial codes, according to the regulatory D.M. 13813/1976.
	 * @param codeLastname
	 * @param codeFirstname
	 * @param codeYear
	 * @param codeMonth
	 * @param codeDay
	 * @param codeCity
	 * @return fiscalCode
	 */
	
	public static String updateFiscalCode(String codeLastname, String codeFirstname, String codeYear, String codeMonth, String codeDay, String codeCity, int omocodia) {
		StringBuilder fiscalCode = new StringBuilder();
		String codeOmocodia = new String();
		int i;
		int changed;
		int length;
		char curChr;
		
		String codeControl = codeControl(codeLastname+codeFirstname+codeYear+codeMonth+codeDay+codeCity);
		fiscalCode.append(codeLastname+codeFirstname+codeYear+codeMonth+codeDay+codeCity+codeControl);
		
		length = fiscalCode.length();
		
		for(i = 1, changed = 0; i <= length & changed < omocodia; i ++) {
			curChr = fiscalCode.charAt(length - i);
			
			if(!Character.isDigit(curChr)) {
				continue;
			} else {
				codeOmocodia = CodeMaps.omocodiaMap.get(omocodia);
				fiscalCode.setCharAt(length - i, codeOmocodia.charAt(0));
				changed ++;
			}
		}
		
		return fiscalCode.toString();
	}

}
