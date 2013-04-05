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


/**
 * This class provides the interface for the access to a generic database record of the specific database table ItalyCodes.
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class ItalyCode {
	
	private long _id;
	private String nationalCode;
	private String catastalCode;
	private String province;
	private String city;

	
	/**
	 * 
	 * @return
	 */
	
	public long getId() {
		return _id;
	}

	
	/**
	 * 
	 * @param id
	 */
	
	public void setId(long id) {
		this._id = id;
	}

	
	/**
	 * 
	 * @return
	 */
	
	public String getNationalCode() {
		return nationalCode;
	}

	
	/**
	 * 
	 * @param nationalCode
	 */
	
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	
	/**
	 * 
	 * @return
	 */
	
	public String getCatastalCode() {
		return catastalCode;
	}

	
	/**
	 * 
	 * @param catastalCode
	 */
	
	public void setCatastalCode(String catastalCode) {
		this.catastalCode = catastalCode;
	}

	
	/**
	 * 
	 * @return
	 */
	
	public String getProvince() {
		return province;
	}

	
	/**
	 * 
	 * @param province
	 */
	
	public void setProvince(String province) {
		this.province = province;
	}

	
	/**
	 * 
	 * @return
	 */
	
	public String getCity() {
		return city;
	}

	
	/**
	 * 
	 * @param city
	 */
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return (city + " (" + province + ")");
	}
}
