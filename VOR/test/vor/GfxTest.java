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

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.junit.Test;

/**
 * To test our graphics functions, we prepare a reference image of what we'd
 * like an image to look like after calling all functions upon it. We assume
 * that if the functions work properly, they will generate a matching image.
 * 
 * @author William Gaul
 */
public class GfxTest {
	
	private boolean bufferedImagesEqual(BufferedImage a, BufferedImage b) {
		if (a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
			return false;
		}
		for (int i = 0; i < a.getWidth(); i++) {
			for (int j = 0; j < a.getHeight(); j++) {
				if (a.getRGB(i, j) != b.getRGB(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Test
	public void testGfx() {
		Resources.loadImages(new String[] {"base", "obs", "wheel", "to", "from", "good", "bad", "font", "ref"});
		BufferedImage ref = Resources.image("ref");
		BufferedImage image = new BufferedImage(512, 576, BufferedImage.TYPE_3BYTE_BGR);
		Gfx.create(image.createGraphics())
				.drawImage(null, "base", 0, 0)
				.drawImageRotated(null, "obs", 7, 470, 60)
				.drawImageRotated(null, "wheel", 0, 64, 333)
				.drawImage(null, "from", 305, 299)
				.drawImage(null, "bad", 160, 309)
				.drawRay(256, 192, 257, 90 + (5 * -5 / 2), 6, Color.WHITE)
				.drawText(null, "font", 33, 25, String.format("%03d", 42))
				.drawText(null, "font", 391, 25, "CQD")
				.flush();
		assertTrue("Gfx functions work", bufferedImagesEqual(image, ref));
	}
}
