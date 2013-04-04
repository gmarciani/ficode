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


/**
 * This class ....
 * 
 * @author Giacomo Marciani <giacomo.marciani@gmail.com>
 * 
 * @version 1.0 03.04.2013
 * 
 */

public class AbroadCode {
	
	private long id;
	private String codiceNazionale;
	private String comune;

	
	/**
	 * 
	 * @return
	 */
	
	public long getId() {
		return id;
	}

	
	/**
	 * 
	 * @param id
	 */
	
	public void setId(long id) {
		this.id = id;
	}

	
	/**
	 * 
	 * @return
	 */
	
	public String getCodiceNazionale() {
		return codiceNazionale;
	}

	
	/**
	 * 
	 * @param codiceNazionale
	 */
	
	public void setCodiceNazionale(String codiceNazionale) {
		this.codiceNazionale = codiceNazionale;
	}

	
	/**
	 * 
	 * @return
	 */
	
	public String getComune() {
		return comune;
	}

	
	/**
	 * 
	 * @param comune
	 */
	
	public void setComune(String comune) {
		this.comune = comune;
	}

	
	/**
	 * 
	 */
	
	@Override
	public String toString() {
		return comune;
	}
}
