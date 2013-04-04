/*  FiCode: Android App for Fiscal Code calculation.
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

import java.util.HashMap;
import java.util.Map;


/**
 * This class ....
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class CodeMaps {

	public static final Map<Integer, String> monthMap;

	public static final Map<String, Integer> oddMap;

	public static final Map<String, Integer> evenMap;

	public static final Map<Integer, String> restMap;

	public static final Map<Integer, String> omocodiaMap;

	static {
		monthMap = new HashMap<Integer, String>();

		monthMap.put(1,"A");
		monthMap.put(2,"B");
		monthMap.put(3,"C");
		monthMap.put(4,"D");
		monthMap.put(5,"E");
		monthMap.put(6,"H");
		monthMap.put(7,"L");
		monthMap.put(8,"M");
		monthMap.put(9,"P");
		monthMap.put(10,"R");
		monthMap.put(11,"S");
		monthMap.put(12,"T");
	}

	static {
		oddMap = new HashMap<String, Integer>();

		oddMap.put("0",1);
		oddMap.put("1",0);
		oddMap.put("2",5);
		oddMap.put("3",7);
		oddMap.put("4",9);
		oddMap.put("5",13);
		oddMap.put("6",15);
		oddMap.put("7",17);
		oddMap.put("8",19);
		oddMap.put("9",21);

		oddMap.put("A",1);
		oddMap.put("B",0);
		oddMap.put("C",5);
		oddMap.put("D",7);
		oddMap.put("E",9);
		oddMap.put("F",13);
		oddMap.put("G",15);
		oddMap.put("H",17);
		oddMap.put("I",19);
		oddMap.put("J",21);
		oddMap.put("K",2);
		oddMap.put("L",4);
		oddMap.put("M",18);
		oddMap.put("N",20);
		oddMap.put("O",11);
		oddMap.put("P",3);
		oddMap.put("Q",6);
		oddMap.put("R",8);
		oddMap.put("S",12);
		oddMap.put("T",14);
		oddMap.put("U",16);
		oddMap.put("V",10);
		oddMap.put("W",22);
		oddMap.put("X",25);
		oddMap.put("Y",24);
		oddMap.put("Z",23);
	}

	static {
		evenMap = new HashMap<String, Integer>();

		evenMap.put("0",0);
		evenMap.put("1",1);
		evenMap.put("2",2);
		evenMap.put("3",3);
		evenMap.put("4",4);
		evenMap.put("5",5);
		evenMap.put("6",6);
		evenMap.put("7",7);
		evenMap.put("8",8);
		evenMap.put("9",9);

		evenMap.put("A",0);
		evenMap.put("B",1);
		evenMap.put("C",2);
		evenMap.put("D",3);
		evenMap.put("E",4);
		evenMap.put("F",5);
		evenMap.put("G",6);
		evenMap.put("H",7);
		evenMap.put("I",8);
		evenMap.put("J",9);
		evenMap.put("K",10);
		evenMap.put("L",11);
		evenMap.put("M",12);
		evenMap.put("N",13);
		evenMap.put("O",14);
		evenMap.put("P",15);
		evenMap.put("Q",16);
		evenMap.put("R",17);
		evenMap.put("S",18);
		evenMap.put("T",19);
		evenMap.put("U",20);
		evenMap.put("V",21);
		evenMap.put("W",22);
		evenMap.put("X",23);
		evenMap.put("Y",24);
		evenMap.put("Z",25);
	}

	static {
		restMap = new HashMap<Integer, String>();

		restMap.put(0,"A");
		restMap.put(1,"B");
		restMap.put(2,"C");
		restMap.put(3,"D");
		restMap.put(4,"E");
		restMap.put(5,"F");
		restMap.put(6,"G");
		restMap.put(7,"H");
		restMap.put(8,"I");
		restMap.put(9,"J");
		restMap.put(10,"K");
		restMap.put(11,"L");
		restMap.put(12,"M");
		restMap.put(13,"N");
		restMap.put(14,"O");
		restMap.put(15,"P");
		restMap.put(16,"Q");
		restMap.put(17,"R");
		restMap.put(18,"S");
		restMap.put(19,"T");
		restMap.put(20,"U");
		restMap.put(21,"V");
		restMap.put(22,"W");
		restMap.put(23,"X");
		restMap.put(24,"Y");
		restMap.put(25,"Z");
	}

	static {
		omocodiaMap = new HashMap<Integer, String>();

		omocodiaMap.put(0,"L");
		omocodiaMap.put(1,"M");
		omocodiaMap.put(2,"N");
		omocodiaMap.put(3,"P");
		omocodiaMap.put(4,"Q");
		omocodiaMap.put(5,"R");
		omocodiaMap.put(6,"S");
		omocodiaMap.put(7,"T");
		omocodiaMap.put(8,"U");
		omocodiaMap.put(9,"V");
	}
}
