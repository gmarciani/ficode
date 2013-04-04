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

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.res.TypedArray;

/**
 * This class ....
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class LanguageSettings extends ListActivity {
	
	protected static final String SETTING_LANGUAGE_CODE = "";
	
	ListView lvCityList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language_settings);	
		
		setupActionBar();	
		
		String[] Languages = getApplicationContext().getResources().getStringArray(R.array.Languages);
		TypedArray Flags = getApplicationContext().getResources().obtainTypedArray(R.array.Flags);
		
		LanguageAdapter adapter = new LanguageAdapter(this, R.layout.list_row_language_settings, Languages, Flags);
		    
		setListAdapter(adapter);
		
		lvCityList = getListView();
		lvCityList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				
				Toast toastLanguageChanged;
				
				switch (position) {
				case 0:
					setupAppLanguage("EN");
					
					toastLanguageChanged = Toast.makeText(getApplicationContext(), R.string.LanguageEnglish, Toast.LENGTH_SHORT);
					toastLanguageChanged.show();
					break;
				case 1:
					setupAppLanguage("DE");
					
					toastLanguageChanged = Toast.makeText(getApplicationContext(), R.string.LanguageGerman, Toast.LENGTH_SHORT);
					toastLanguageChanged.show();
					break;
				case 2:
					setupAppLanguage("FR");
					
					toastLanguageChanged = Toast.makeText(getApplicationContext(), R.string.LanguageFrench, Toast.LENGTH_SHORT);
					toastLanguageChanged.show();
					break;
				case 3:
					setupAppLanguage("ES");
					
					toastLanguageChanged = Toast.makeText(getApplicationContext(), R.string.LanguageSpanish, Toast.LENGTH_SHORT);
					toastLanguageChanged.show();
					break;
				case 4:
					setupAppLanguage("IT");
					
					toastLanguageChanged = Toast.makeText(getApplicationContext(), R.string.LanguageItalian, Toast.LENGTH_SHORT);
					toastLanguageChanged.show();
					break;
				default:
					break;					
				}			
			}
		});
		
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(false);
			getActionBar().setTitle(R.string.LanguageSettings);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_activity_language_settings, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	
	/**
	 * 
	 * @param languageId
	 */
	
	public void setupAppLanguage(String languageId) {
		//
	}

}
