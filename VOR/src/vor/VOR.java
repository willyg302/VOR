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
 * Main class for VOR.
 * 
 * @author William
 */
public class VOR {
	private Radio radio;
	
	private int desired, needle;

	public VOR() {
		// Simulated radio
		radio = new Radio();

		desired = 0;
		needle = 0;
	}
	
	/**
	 * needleDegrees is obs - intercepted radial
	 * For example, if we say we want 30 deg TO (210 FROM), and the intercepted radial FROM the station is 221,
	 * then our needleDegrees = 210 - 221 = -11.
	 * 
	 * TO/FROM
	 * 
	 * The intercepted radial is the ray FROM the station. So if our OBS is within 90 deg to either side of this
	 * value, we are FROM. Otherwise, we are TO.
	 */
	
	// TODO abeam, good/bad signal
	
	public void rotateOBS(int delta) {
		int intercepted = radio.getRadial();
		
		desired = Utils.clampDegrees(desired + delta);
		needle = Utils.clamp(Utils.arc(desired, intercepted), -10, 10);
	}
	
	public int getDesired() {
		//System.out.println(desired);
		return desired;
	}
	
	public int getNeedle() {
		//System.out.println(needle);
		return needle;
	}
}
