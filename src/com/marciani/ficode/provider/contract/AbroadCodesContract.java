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


package com.marciani.ficode.provider.contract;


import android.net.Uri;
import android.provider.BaseColumns;


/**
 * This class is the Contract Class for the access to table AbroadCodes in the SQLite database FiCode, via the content provider FiCodeProvider by the path
 * abroad_codes.
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 20.04.2013
 * 
 */

public final class AbroadCodesContract {

	private AbroadCodesContract() {
		
	}
	
	public static final String AUTHORITY = "com.marciani.ficode.provider";
	
	public static final String PATH = "abroad_codes";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
	
	public static final String DBNAME = "FiCode";
	
	public static final String TABLE = "AbroadCodes";
	
	public static final class Columns implements BaseColumns {
		public static final String _ID = "_id";
		public static final String NATIONAL_CODE = "nationalCode";
		public static final String NATIONALITY = "city";	
		
		public static final String DEFAULT_SORT_ORDER = NATIONALITY + " DESC";
	}

}
