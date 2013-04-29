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


package com.marciani.ficode.provider;


import com.marciani.ficode.database.FiCodeHelper;
import com.marciani.ficode.provider.contract.AbroadCodesContract;
import com.marciani.ficode.provider.contract.ItalyCodesContract;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;


/**
 * This class is the main module for the content provider FiCodeProvider (AUTHORITY="com.marciani.ficode.provider"). This content provider accepts the folloqing paths:
 * - italy_codes
 * - abroad_codes
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 20.04.2013
 * 
 */

public class FiCodeProvider extends ContentProvider {
	
	private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final int ITALY_CODES = 1;
	private static final int ABROAD_CODES = 2;	
	
	static {
		mUriMatcher.addURI(ItalyCodesContract.AUTHORITY, ItalyCodesContract.PATH, ITALY_CODES);
		mUriMatcher.addURI(AbroadCodesContract.AUTHORITY, AbroadCodesContract.PATH, ABROAD_CODES);
	}
	
	private SQLiteDatabase database;
	private FiCodeHelper dbHelper;

	@Override
	public boolean onCreate() {
		dbHelper = new FiCodeHelper(getContext());
		return (dbHelper == null)? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,	String[] selectionArgs, String sortOrder) {
		
		database = dbHelper.getReadableDatabase();
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();	
		
		String orderBy = null;
		
		switch(mUriMatcher.match(uri)) {
		case ITALY_CODES:
			qb.setTables(ItalyCodesContract.TABLE);
			//qb.appendWhere(ItalyCodesContract.Columns.CITY + "=" + uri.getLastPathSegment());
			orderBy = TextUtils.isEmpty(sortOrder)? ItalyCodesContract.Columns.DEFAULT_SORT_ORDER : sortOrder;
			break;		
		case ABROAD_CODES:
			qb.setTables(AbroadCodesContract.TABLE);
			//qb.appendWhere(AbroadCodesContract.Columns.NATIONALITY + "=" + uri.getLastPathSegment());
			orderBy = TextUtils.isEmpty(sortOrder)? AbroadCodesContract.Columns.DEFAULT_SORT_ORDER : sortOrder;
			break;
		default:
			throw new IllegalArgumentException("Unsupported URI " + uri);
		}
		
		Cursor mCursor = qb.query(database, projection, selection, selectionArgs, null, null, orderBy);
		
		mCursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return mCursor;
	}
	
	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException();
	}

}
