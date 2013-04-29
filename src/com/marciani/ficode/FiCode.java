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


import java.util.Map;
import com.marciani.ficode.R;
import com.marciani.ficode.fiscalcode.FiscalCode;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This class is the main module for insertion and validation of personal data, and for the code generation forwarding and their display.
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class FiCode extends Activity {
	
	public static class Data {
		protected static final String STR_LASTNAME = "com.marciani.ficode.FiCode.LASTNAME";
		protected static final String STR_FIRSTNAME = "com.marciani.ficode.FiCode.FIRSTNAME";
		protected static final String INT_GENDER = "com.marciani.ficode.FiCode.GENDER";
		protected static final String INT_DAY = "com.marciani.ficode.FiCode.DAY";
		protected static final String INT_MONTH = "com.marciani.ficode.FiCode.MONTH";
		protected static final String INT_YEAR = "com.marciani.ficode.FiCode.YEAR";
		protected static final String STR_CITY = "com.marciani.ficode.FiCode.CITY";
		protected static final String STR_NATIONAL_CODE = "com.marciani.ficode.FiCode.DATA_NATIONAL_CODE";
		protected static final String INT_OMOCODIA = "com.marciani.ficode.FiCode.OMOCODIA";
		protected static final String STR_USER_NATIONALITY = "com.marciani.ficode.FiCode.USER_NATIONALITY";
		protected static final String APP_PREF = "com.marciani.ficode.pref";
	}
	
	public static class FiscalCodeMap {
		protected static final Map<Integer, String> monthMap = com.marciani.ficode.fiscalcode.CodeMaps.monthMap;
		protected static final Map<String, Integer> evenMap = com.marciani.ficode.fiscalcode.CodeMaps.evenMap;
		protected static final Map<String, Integer> oddMap = com.marciani.ficode.fiscalcode.CodeMaps.oddMap;
		protected static final Map<Integer, String> restMap = com.marciani.ficode.fiscalcode.CodeMaps.restMap;
		protected static final Map<Integer, String> omocodiaMap = com.marciani.ficode.fiscalcode.CodeMaps.omocodiaMap;
	}
	
	EditText etLastname;
	EditText etFirstname;
	EditText etCity;
	RadioGroup rgGender;
	DatePicker dpBirthday;	
	ImageButton ibtnBrowse;	
	TextView tvFiscalCode;
	SeekBar sbOmocodia;
	RadioGroup rgUserNationality;
	
	AlertDialog dialogOmocodia;
	AlertDialog dialogUserNationality;
	
	String lastname;
	String firstname;
	char gender = '#';
	int genderId = -1;
	int day = 27;
	int month = 05;
	int year = 1990;
	String city = "";
	int omocodia = 0;
	String userNationality = "Italy";
	
	String codeLastname = "***";
	String codeFirstname = "***";
	String codeDay = "**";
	String codeMonth = "**";
	String codeYear = "**";
	String codeCity = "****";
	String codeControl = "*";	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fi_code);		
				
		etLastname = (EditText) findViewById(R.id.etLastname);
		etFirstname = (EditText) findViewById(R.id.etFirstname);
		etCity = (EditText) findViewById(R.id.etCity);
		rgGender = (RadioGroup) findViewById(R.id.rgGender);
		dpBirthday = (DatePicker) findViewById(R.id.dpBirthday);
		ibtnBrowse = (ImageButton) findViewById(R.id.ibtnBrowse);
		tvFiscalCode = (TextView) findViewById(R.id.tvFiCode);	
		
		etLastname.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {	
				lastname = s.toString();
				codeLastname = FiscalCode.codeLastname(lastname);
				tvFiscalCode.setText(FiscalCode.updateFiscalCode(codeLastname, codeFirstname, codeYear, codeMonth, codeDay, codeCity, omocodia));			
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub				
			}			
		});
		
		etFirstname.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				firstname = s.toString();
				codeFirstname = FiscalCode.codeFirstname(firstname);
				tvFiscalCode.setText(FiscalCode.updateFiscalCode(codeLastname, codeFirstname, codeYear, codeMonth, codeDay, codeCity, omocodia));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub				
			}			
		});
		
		rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup g, int checkedId) {
				if(checkedId == g.getChildAt(0).getId()) {
					gender = 'M';
					genderId = checkedId;
					codeDay = FiscalCode.codeDay(day, gender);
					codeMonth = FiscalCode.codeMonth(month+1);
					codeYear = FiscalCode.codeYear(year);
				} else if(checkedId == g.getChildAt(1).getId()){
					gender = 'F';
					genderId = checkedId;
					codeDay = FiscalCode.codeDay(day, gender);
					codeMonth = FiscalCode.codeMonth(month+1);
					codeYear = FiscalCode.codeYear(year);
				} else {
					codeDay = "**";
				}
				
				tvFiscalCode.setText(FiscalCode.updateFiscalCode(codeLastname, codeFirstname, codeYear, codeMonth, codeDay, codeCity, omocodia));
			}
		});
				
		etCity.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {	
				city = s.toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub	
			}			
		});
		
		ibtnBrowse.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intCityList = new Intent(getApplicationContext(), CityList.class);	
				intCityList.putExtra(Data.STR_CITY, city);
				intCityList.putExtra(Data.STR_USER_NATIONALITY, userNationality);
				startActivity(intCityList);				
			}
		});		
		
		AlertDialog.Builder builderDialogOmocodia = new AlertDialog.Builder(this);
		builderDialogOmocodia.setTitle(R.string.Omocodia);
		
		LayoutInflater inflaterDialogOmocodia = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View contentDialogOmocodia = inflaterDialogOmocodia.inflate(R.layout.alert_dialog_omocodia, (ViewGroup) findViewById(R.id.omocodia_alert_dialog_layout_root));			
		builderDialogOmocodia.setView(contentDialogOmocodia);				
		
		sbOmocodia = (SeekBar) contentDialogOmocodia.findViewById(R.id.sbOmocodia);					
		
		sbOmocodia.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			Toast toastOmocodia;
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				toastOmocodia = Toast.makeText(getApplicationContext(), "Omocodia: " + seekBar.getProgress(), Toast.LENGTH_SHORT);
	            toastOmocodia.show();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
			}
		});	
		
		builderDialogOmocodia.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
			
			Toast toastOmocodia;
	           public void onClick(DialogInterface dialog, int id) {
	               omocodia = sbOmocodia.getProgress();
	               toastOmocodia = Toast.makeText(getApplicationContext(), "Omocodia: " + omocodia, Toast.LENGTH_SHORT);
		           toastOmocodia.show();
		           
		           tvFiscalCode.setText(FiscalCode.updateFiscalCode(codeLastname, codeFirstname, codeYear, codeMonth, codeDay, codeCity, omocodia));
	           }
	       });
		
		builderDialogOmocodia.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               sbOmocodia.setProgress(omocodia);
	           }
	       });	
		
		dialogOmocodia = builderDialogOmocodia.create();
		
		AlertDialog.Builder builderDialogUserNationality = new AlertDialog.Builder(this);
		builderDialogUserNationality.setTitle(R.string.UserNationality);
		
		LayoutInflater inflaterDialogUserNationality = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View contentDialogUserNationality = inflaterDialogUserNationality.inflate(R.layout.alert_dialog_user_nationality, (ViewGroup) findViewById(R.id.user_nationality_alert_dialog_layout_root));			
		builderDialogUserNationality.setView(contentDialogUserNationality);
		
		rgUserNationality = (RadioGroup) contentDialogUserNationality.findViewById(R.id.rgUserNationality);		
		
		builderDialogUserNationality.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
			
			Toast toastUserNationality;
	           public void onClick(DialogInterface dialog, int id) {
	               userNationality = (rgUserNationality.getCheckedRadioButtonId() == R.id.rbItaly) ? "Italy" : "Abroad";
	               toastUserNationality = Toast.makeText(getApplicationContext(), "Nationality: " + userNationality, Toast.LENGTH_SHORT);
		           toastUserNationality.show();		           
		           
		           refreshMenu();
		           etCity.setHint((userNationality.equals("Italy")) ? R.string.City : R.string.Nationality);
	           }
	       });
		
		builderDialogUserNationality.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               rgUserNationality.check((userNationality.equals("Italy")) ? R.id.rbItaly : R.id.rbAbroad);
	           }
	       });	
		
		dialogUserNationality = builderDialogUserNationality.create();
	}

	@Override
	public void onResume() {
		super.onResume();
		
		SharedPreferences appPref = getSharedPreferences(Data.APP_PREF, 0);
		
		lastname = appPref.getString(Data.STR_LASTNAME, lastname);
		firstname = appPref.getString(Data.STR_FIRSTNAME, firstname);
		genderId = appPref.getInt(Data.INT_GENDER, genderId);
		day = appPref.getInt(Data.INT_DAY, day);
		month = appPref.getInt(Data.INT_MONTH, month);
		year = appPref.getInt(Data.INT_YEAR, year);	
		omocodia = appPref.getInt(Data.INT_OMOCODIA, omocodia);
		userNationality = appPref.getString(Data.STR_USER_NATIONALITY, userNationality);
		
		refreshMenu();
		
		Intent intCityList = getIntent();		
		if(intCityList.hasExtra(Data.STR_CITY) & intCityList.hasExtra(Data.STR_NATIONAL_CODE)) { 
			city = intCityList.getStringExtra(Data.STR_CITY);
			codeCity = intCityList.getStringExtra(Data.STR_NATIONAL_CODE);
		}
		
		etLastname.setText(lastname);
		etLastname.setSelection(etLastname.getText().length());		
		etFirstname.setText(firstname);
		etFirstname.setSelection(etFirstname.getText().length());		
		rgGender.check(genderId);		
		dpBirthday.init(year, month, day, new DatePicker.OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker v, int y, int m, int d) {
				int checkedId = rgGender.getCheckedRadioButtonId();
				int checkedM = rgGender.getChildAt(0).getId();
				int checkedF = rgGender.getChildAt(1).getId();
				
				if(checkedId == checkedM) {
					gender = 'M';
				} else if(checkedId == checkedF) {
					gender = 'F';
				} else {
					gender = '#';
				}
				
				day = d;
				month = m;
				year = y;
				
				codeDay = FiscalCode.codeDay(day, gender);
				codeMonth = FiscalCode.codeMonth(month+1);
				codeYear = FiscalCode.codeYear(year);
				
				tvFiscalCode.setText(FiscalCode.updateFiscalCode(codeLastname, codeFirstname, codeYear, codeMonth, codeDay, codeCity, omocodia));
			}
		});
		
		etCity.setHint((userNationality.equals("Italy")) ? R.string.City : R.string.Nationality);
		etCity.setText(city);
		etCity.setSelection(etCity.getText().length());
		
		sbOmocodia.setProgress(omocodia);
		
		tvFiscalCode.setText(FiscalCode.updateFiscalCode(codeLastname, codeFirstname, codeYear, codeMonth, codeDay, codeCity, omocodia));		
	}
	
	@Override
	public void onStop() {
		super.onStop();		
		
		SharedPreferences appPref = getSharedPreferences(Data.APP_PREF, 0);
	    SharedPreferences.Editor editorPref = appPref.edit();
	    
	    editorPref.putString(Data.STR_LASTNAME, lastname);
	    editorPref.putString(Data.STR_FIRSTNAME, firstname);
	    editorPref.putInt(Data.INT_GENDER, genderId);
	    editorPref.putInt(Data.INT_DAY, day);
	    editorPref.putInt(Data.INT_MONTH, month);
	    editorPref.putInt(Data.INT_YEAR, year);
	    editorPref.putString(Data.STR_CITY, city);
	    editorPref.putString(Data.STR_NATIONAL_CODE, codeCity);
	    editorPref.putInt(Data.INT_OMOCODIA, omocodia);
	    editorPref.putString(Data.STR_USER_NATIONALITY, userNationality);
	    
	    editorPref.commit();	    
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_activity_fi_code, menu);
		return true;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void refreshMenu() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			invalidateOptionsMenu();
		}
	}	
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
		
	    menu.clear();
	    
	    getMenuInflater().inflate(R.menu.menu_activity_fi_code, menu);

	    if(userNationality.equals("Italy")) {
	    	menu.findItem(R.id.miUserNationality).setIcon(R.drawable.ic_launcher_it);	
	    } else { 
	    	menu.findItem(R.id.miUserNationality).setIcon(R.drawable.ic_launcher_world);        
	    }

	    return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.miUserNationality:
			dialogUserNationality.show();
			break;
		case R.id.miOmocodiaSettings:	
			dialogOmocodia.show();			
			break;
		case R.id.miQuit:
			finish();
			break;
		default:
			break;			
		}
		
		return true;		
	}	
}
