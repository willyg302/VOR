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


/**
 * This test our radio.
 * @author dodavid
 *
 */
public class RadioTest {
	
	@Test
	public void testRadio() {
		int over = 0;
		Radio radio = new Radio();
		for (int i = 0; i < 1000; i++) {
			radio.reset();
			int radial = radio.getRadial();
			assertTrue("Radial is within bounds", radial >= 0 && radial < 360);
			assertTrue("Station ID is well-formed", radio.getStationID().matches("[A-Z]{3}"));
			if (radio.isOverStation()) {
				over++;
			}
		}
		assertTrue("The over signal is given about 5% of the time", over > 30 && over < 70);
	}
}
