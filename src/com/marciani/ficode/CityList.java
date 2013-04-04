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

import java.util.List;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

/**
 * This class ....
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class CityList extends ListActivity {		

	private ItalyCodeDataSource datasource;
	private static final String selectionFilter = FiCodeHelper.ItalyCodesColumn.city + " like ";
	
	EditText etCityBrowse;
	String city = "";
	String nationalCode;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		setupActionBar();				
				
		etCityBrowse = (EditText) findViewById(R.id.etCityBrowse);
		
		datasource = new ItalyCodeDataSource(this);
		datasource.open();		
		
		etCityBrowse.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				city = etCityBrowse.getText().toString();
				
				List<ItalyCode> listItalyCode = datasource.getItalyCodes(selectionFilter + "'" + city + "%'");
				ArrayAdapter<ItalyCode> adapterItalyCode = new ArrayAdapter<ItalyCode>(getApplicationContext(), android.R.layout.simple_list_item_1, listItalyCode);
				setListAdapter(adapterItalyCode);
			}			
		});	
	}

	@Override
	public void onResume() {
		super.onResume();
		
		datasource.open();		
		
		Intent intFiCode = getIntent();
		city = intFiCode.getStringExtra(FiCode.Data.STR_CITY);
		
		etCityBrowse.setText(city);	
		etCityBrowse.setSelection(etCityBrowse.getText().length());
		
		List<ItalyCode> listItalyCode = datasource.getItalyCodes(selectionFilter + "'" + city + "%'");
		ArrayAdapter<ItalyCode> adapterItalyCode = new ArrayAdapter<ItalyCode>(this, android.R.layout.simple_list_item_1, listItalyCode);
		setListAdapter(adapterItalyCode);
	}
	
	@Override
	public void onPause() {		
		super.onPause();
		datasource.close();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);				
		
		ItalyCode item = (ItalyCode) l.getAdapter().getItem(position);
		
		city = item.getCity();
		nationalCode = item.getNationalCode();
		
		etCityBrowse.setText(city);	
		etCityBrowse.setSelection(etCityBrowse.getText().length());
		
		Intent intFiCode = new Intent(getApplicationContext(), FiCode.class);
		intFiCode.putExtra(FiCode.Data.STR_CITY, city);
		intFiCode.putExtra(FiCode.Data.STR_NATIONAL_CODE, nationalCode);
		startActivity(intFiCode);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(false);
			getActionBar().setTitle(R.string.CityList);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_activity_city_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.miQuit:
			android.os.Process.killProcess(android.os.Process.myPid());
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
