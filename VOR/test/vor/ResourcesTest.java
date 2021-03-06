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

import java.util.Arrays;

import org.junit.Test;

public class ResourcesTest {

	@Test
	public void testLoadImage() {
		assertTrue("Image exists", Resources.loadImage("base"));
		assertFalse("Image does not exist", Resources.loadImage("dummy"));
	}
	
	@Test
	public void testLoadImages() {
		assertTrue("Interspersed good/bad loads", Arrays.equals(
				new boolean[] {true, false, true},
				Resources.loadImages(new String[] {"good", "dne", "bad"}
		)));
	}
	
	@Test
	public void testImage() {
		assertNull("Image not loaded yet", Resources.image("wheel"));
		Resources.loadImage("wheel");
		assertNotNull("Image loaded", Resources.image("wheel"));
	}
}
