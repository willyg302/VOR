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

import static org.junit.Assert.*;

import org.junit.Test;

public class VORTest {
	
	/**
	 * Performs random sanity checks on VOR functionality.
	 */
	@Test
	public void testVOR() {
		VOR vor = new VOR(new Radio());  // We don't want to use the default timed radio
		assertTrue("Station ID is well-formed", vor.getStationID().matches("[A-Z]{3}"));
		// Determine intercepted radial
		int i = 0;
		for (i = 0; i < 360; i++) {
			if (vor.getCDI() == 0 && !vor.isGoingTo()) {
				break;
			}
			vor.rotateOBS(1);
		}
		// Do one full rotation starting from intercepted radial
		for (int j = 0; j < 360; j++) {
			if (j < 10 || (j > 170 && j < 190) || j > 350) {
				assertTrue("CDI has proper bounds", Math.abs(vor.getCDI()) < 10);
			}
			if (j == 90 || j == 270) {
				assertFalse("abeam is detected properly", vor.isSignalGood());
			}
			if (j > 90 && j < 270) {
				assertTrue("To/from is calculated properly", vor.isGoingTo());
			}
			vor.rotateOBS(1);
		}
	}
	
	/**
	 * Stress-tests the VOR over all 360x360 possible combinations of
	 * intercepted and desired radials.
	 */
	@Test
	public void testVORLoop() {
		FakeRadio radio = new FakeRadio();
		VOR vor = new VOR(radio);
		int[] cdis = new int[360];
		for (int i = 0; i < 360; i++) {
			radio.setRadial(i);
			int abeam1 = Utils.normalizeAngle(i + 90);
			int abeam2 = Utils.normalizeAngle(i - 90);
			// Cache expected CDI values for intercepted radial i
			int cdi = 0;
			int x = i;
			int step = -1;
			for (int j = 0; j < 360; j++) {
				cdis[x] = Utils.clamp(cdi, -10, 10);
				if (j == 90 || j == 270) {
					step = -step;
				}
				cdi += step;
				x = Utils.normalizeAngle(x + 1);
			}
			// Loop over desired radial values
			for (int j = 0; j < 360; j++) {
				assertEquals("getOBS works", j, vor.getOBS());
				assertEquals("CDI is calculated properly", cdis[j], vor.getCDI());
				if (j == abeam1 || j == abeam2) {
					assertFalse("abeam is detected properly", vor.isSignalGood());
				}
				assertEquals("To/from is calculated properly", Math.abs(Utils.arc(i, j)) > 90, vor.isGoingTo());
				vor.rotateOBS(1);
			}
		}
	}
}
