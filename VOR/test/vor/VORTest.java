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
		VOR vor = new VOR();
		assertTrue("Station ID is well-formed", vor.getStationID().matches("[A-Z]{3}"));
		
		// TODO
		//getCDI()
		//isSignalGood()
		//isGoingTo()
	}
	
	/**
	 * Stress-tests the VOR over all 360x360 possible combinations of
	 * intercepted and desired radials.
	 */
	@Test
	public void testVORLoop() {
		FakeRadio radio = new FakeRadio();
		VOR vor = new VOR(radio);
		for (int i = 0; i < 360; i++) {
			radio.setRadial(i);
			int abeam1 = Utils.normalizeAngle(i + 90);
			int abeam2 = Utils.normalizeAngle(i - 90);
			for (int j = 0; j < 360; j++) {
				assertEquals("getOBS works", j, vor.getOBS());
				int norm = Utils.normalizeAngle(j, i);
				if ((norm > i - 10 && norm < i + 10) || (norm < i - 170 || norm > i + 170)) {
					assertTrue("CDI has proper bounds", Math.abs(vor.getCDI()) < 10);
				}
				
				// TODO
				//getCDI() for all values
				
				if (j == abeam1 || j == abeam2) {
					assertFalse("abeam is detected properly", vor.isSignalGood());
				}
				assertEquals("To/from is calculated properly", Math.abs(Utils.arc(i, j)) > 90, vor.isGoingTo());
				vor.rotateOBS(1);
			}
		}
	}
}
