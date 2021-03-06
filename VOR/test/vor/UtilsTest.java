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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class UtilsTest {

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
	
	/**
	 * This stress-tests the {@link vor.Utils#randomInt(int, int) randomInt} function
	 * by generating 100000 integers in the range [1, 100] and keeping track of the
	 * lowest and highest generated values. Ideally they <i>should</i> be 1 and 100,
	 * but it is possible to get a false negative, so as long as this test passes
	 * most of the time it is okay.
	 */
	@Test
	public void testRandomInt() {
		int lowest = Integer.MAX_VALUE;
		int highest = Integer.MIN_VALUE;
		for (int i = 0; i < 100000; i++) {
			int rand = Utils.randomInt(1, 100);
			if (rand < lowest) {
				lowest = rand;
			}
			if (rand > highest) {
				highest = rand;
			}
		}
		if (lowest != 1) {
			fail("The lowest value should be 1, was " + lowest);
		}
		if (highest != 100) {
			fail("The highest value should be 100, was " + highest);
		}
	}
}
