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

/**
 * Our simulated radio.
 * 
 * @author David Do
 */
public class Radio {
	
	private int radial;
	private String stationID;

	/* National */public Radio() {
		this.radial = Utils.randomInt(0, 359);
		// @William modified to use char magic
		this.stationID = new StringBuilder()
				.append((char)Utils.randomInt((int)'A', (int)'Z'))
				.append((char)Utils.randomInt((int)'A', (int)'Z'))
				.append((char)Utils.randomInt((int)'A', (int)'Z'))
				.toString();
	}
	
	public int getRadial() {
		return radial;
	}
	
	public String getStationID() {
		return stationID;
	}
	
	public boolean overStation() {
		int rand = Utils.randomInt(1, 100);
		return (rand > 90);
	}
}
