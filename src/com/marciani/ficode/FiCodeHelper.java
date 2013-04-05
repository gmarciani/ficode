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


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * This class ....
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class FiCodeHelper extends SQLiteOpenHelper {
	
	public static class Table {
		public static final String ItalyCodes = "ItalyCodes";
		public static final String AbroadCodes = "AbroadCodes";
	}

	public static class ItalyCodesColumn {
		public static final String _id = "_id";
		public static final String nationalCode = "nationalCode";
		public static final String catastalCode = "catastalCode";
		public static final String province = "province";
		public static final String city = "city";
	}

	public static class AbroadCodesColumn {
		public static final String _id = "_id";
		public static final String nationalCode = "nationalCode";
		public static final String city = "city";
	}

	public static class DatabaseInfo {
		private static final String dbName = "FiCode.sqlite";
		private static final int dbVersion = 1;
		private static final String dbDestinationFolder = "/data/data/com.marciani.ficode/databases/";
		private static final String dbDestinationPath = dbDestinationFolder + dbName;
	}	
	
	private Context myContext;

	public SQLiteDatabase database;
	
	public FiCodeHelper(Context context) {
		super(context, DatabaseInfo.dbName, null, DatabaseInfo.dbVersion);
		this.myContext = context;
	}

	
	/**
	 * 
	 * @throws IOException
	 */
	
	public void createDatabase() throws IOException {
        if (checkDatabase()) {
        	//
        } else {
            this.getWritableDatabase();
            
            try {
                copyDatabase();
            } catch (IOException ioE) {
                throw new Error("Error copying database");
            }
        }
    }

	
	/**
	 * 
	 * @return
	 */
	
    private boolean checkDatabase() {
        boolean checkDb = false;
        
        try {
            File dbfile = new File(DatabaseInfo.dbDestinationPath);
            checkDb = dbfile.exists();
        } catch (SQLiteException sqlE) {
            System.out.println("Database doesn't exist");
        }

        return checkDb;
    }

    
    /**
     * 
     */
    
	public void openDatabase() {
        database = SQLiteDatabase.openDatabase(DatabaseInfo.dbDestinationPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDatabase() {
        database.close();
        super.close();
    }

    
    /**
     * 
     * @throws IOException
     */
    
	public void copyDatabase() throws IOException {

		InputStream in = myContext.getAssets().open(DatabaseInfo.dbName);
		OutputStream out = new FileOutputStream(DatabaseInfo.dbDestinationPath);

		byte[] buf = new byte[1024];
		int len;
			
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		
		out.flush();
		in.close();
		out.close();
	}
      
	@Override
	public void onCreate(SQLiteDatabase database) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
		database.execSQL("DROP TABLE IF EXISTS" + Table.ItalyCodes);
		database.execSQL("DROP TABLE IF EXISTS" + Table.AbroadCodes);
		
		try {
			createDatabase();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}
	}
}




