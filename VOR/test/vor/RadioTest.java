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

public class RadioTest {

	@Test
	public void testNormalizeAngle() {
		assertEquals("Degree in range", 57, Utils.normalizeAngle(57));
		assertEquals("Degree less than 0", 356, Utils.normalizeAngle(-4));
		assertEquals("Degree greater than 360", 4, Utils.normalizeAngle(364));
		assertEquals("Custom range", -168, Utils.normalizeAngle(192, 10));
	}
	
	@Test
	public void testClamp() {
		assertEquals("Value within interval", 10, Utils.clamp(10, 5, 15));
		assertEquals("Value below interval", -7, Utils.clamp(-15, -7, 12));
		assertEquals("Value above interval", 42, Utils.clamp(Integer.MAX_VALUE, 0, 42));
	}
	
	@Test
	public void testArc() {
		assertEquals("Positive arc", 35, Utils.arc(25, 60));
		assertEquals("Negative arc", -19, Utils.arc(38, 19));
		assertEquals("Passing 0", -26, Utils.arc(25, 359));
		assertEquals("Passing 0", 30, Utils.arc(350, 20));
		assertEquals("Large arc", 176, Utils.arc(24, 200));
		assertEquals("Large arc overflow", -176, Utils.arc(24, 208));
	}
}
