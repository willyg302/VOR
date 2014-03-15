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
 * Holds public static utility methods.
 * 
 * @author William
 */
public class Utils {
	
	public static int clampDegrees(int degrees) {
		if (degrees < 0) {
			degrees += 360;
		} else if (degrees > 359) {
			degrees -= 360;
		}
		return degrees;
	}
	
	public static int clamp(int val, int low, int high) {
		return Math.max(low, Math.min(high, val));
	}
	
	// TODO This fails miserably on abeam (we should really have an abeam indicator)
	public static int arc(int x, int y) {
		int angle = clampDegrees(x - y);
		int sign = 1;
		if (angle > 180) {
			sign = -1;
			angle %= 180;
		}
		if (angle > 90) {
			angle = 180 - angle;
		}
		return sign * angle;
	}
}
