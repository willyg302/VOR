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
	
	private int desired;

	public VOR() {
		// Simulated radio
		radio = new Radio();

		desired = 0;
	}
	
	public void rotateOBS(int delta) {
		desired = Utils.normalizeAngle(desired + delta);
	}
	
	/**
	 * @return OBS = Omni Bearing Selector (desired radial)
	 */
	public int getOBS() {
		return desired;
	}
	
	/**
	 * @return CDI = Course Deviation Indicator (essentially the needle angle)
	 */
	public int getCDI() {
		int intercepted = radio.getRadial();
		int arc = Utils.arc(desired, intercepted);
		if (arc > 90) {
			arc = 180 - arc;
		} else if (arc < -90) {
			arc = -180 - arc;
		}
		return Utils.clamp(arc, -10, 10);
	}
	
	/**
	 * The signal is BAD if it is within 1 degree of being "abeam".
	 * abeam is defined as 90 degrees to either side of the desired radial.
	 * TODO The signal is also BAD if the plane is over the station.
	 * 
	 * @return true if the signal is good
	 */
	public boolean isSignalGood() {
		int intercepted = radio.getRadial();
		int arc = Utils.arc(desired, intercepted);
		return Math.abs(Math.abs(arc) - 90) > 1;
	}
	
	/**
	 * We are FROM if our desired radial is within 90 degrees of the intercepted radial.
	 * Otherwise, we are TO.
	 * 
	 * @return true if we are heading towards the station
	 */
	public boolean isGoingTo() {
		int intercepted = radio.getRadial();
		return Math.abs(Utils.arc(desired, intercepted)) > 90;
	}
	
	public String getStationID() {
		return radio.getStationID();
	}
}
