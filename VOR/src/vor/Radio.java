/**
 * VHF Omnidirectional Range simulator in Java
 * Copyright (C) 2014  William Gaul, David Do, Landon Soriano
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package vor;

public class Radio {
	
	private int radial;
	private String stationID;  // In Morse Code
	

	/* National */public Radio() {
		this.radial = 0;
	}
	
	public int getRadial() {
		return radial;
	}
	
	public String getStationID() {
		return stationID;
	}

}
