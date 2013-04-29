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


package com.marciani.ficode.database;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * This class provides the interface for the access to the specific database table ItalyCodes.
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class ItalyCodesDataSource {
	
	private SQLiteDatabase database;
	private FiCodeHelper dbHelper;
	private String[] allColumns = {FiCodeHelper.ItalyCodesColumn._id, FiCodeHelper.ItalyCodesColumn.nationalCode, FiCodeHelper.ItalyCodesColumn.catastalCode, FiCodeHelper.ItalyCodesColumn.province, FiCodeHelper.ItalyCodesColumn.city};
	private static final String cityAlphaSortAsc = FiCodeHelper.ItalyCodesColumn.city + " ASC";
	
	/**
	 * 
	 * @param context
	 */
	
	public ItalyCodesDataSource(Context context) {
		dbHelper = new FiCodeHelper(context);
		
		try {
			dbHelper.createDatabase();
		} catch (IOException ioE) {
			throw new Error("Unable to create database");
		}
	}
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		
		try {
			dbHelper.openDatabase();
		} catch(SQLException sqlE) {
			throw sqlE;
		}
	}
	
	
	/**
	 * 
	 */
	
	public void close() {
		dbHelper.closeDatabase();
	}
	
	
	/**
	 * 
	 * @param selectionFilter
	 * @return
	 */
	
	public List<ItalyCodes> getItalyCodes(String selectionFilter) {
		List<ItalyCodes> listItalyCodes = new ArrayList<ItalyCodes>();
		Cursor cursor = database.query(FiCodeHelper.Table.ItalyCodes, allColumns, selectionFilter, null, null, null, cityAlphaSortAsc);
		
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			ItalyCodes italyCode = cursorToItalyCode(cursor);
			listItalyCodes.add(italyCode);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return listItalyCodes;
	}
	
	
	/**
	 * 
	 * @param cursor
	 * @return
	 */
	
	private ItalyCodes cursorToItalyCode(Cursor cursor) {
		ItalyCodes italyCode = new ItalyCodes();
		
		italyCode.setCity(cursor.getString(4));
		italyCode.setProvince(cursor.getString(3));
		italyCode.setNationalCode(cursor.getString(1));
		
		return italyCode;		
	}
}
