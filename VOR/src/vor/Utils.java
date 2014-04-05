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
	
	/**
	 * Normalizes {@code angle} to the interval [{@code center} - 180, {@code center} + 180).
	 */
	public static int normalizeAngle(int angle, int center) {
		return angle - 360 * (int)Math.floor((angle + 180 - center) / 360.0);
	}
	
	/**
	 * Normalizes {@code angle} to the interval [0, 359).
	 */
	public static int normalizeAngle(int angle) {
		return normalizeAngle(angle, 180);
	}
	
	/**
	 * Clamps {@code val} to the interval [{@code low}, {@code high}].
	 */
	public static int clamp(int val, int low, int high) {
		return Math.max(low, Math.min(high, val));
	}
	
	/**
	 * Determines the shortest rotation, in degrees, necessary to get from {@code x} to {@code y}.
	 */
	public static int arc(int x, int y) {
		return normalizeAngle(y - x, 0);
	}
}
