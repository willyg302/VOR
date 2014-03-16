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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Maintains static HashMaps of various resources, accessible by String key.
 * 
 * @author William
 */
public class Resources {
	private static HashMap<String, BufferedImage> images = new HashMap<>();

	/**
	 * Attempts to load an image from the resources directory.
	 * 
	 * @param key Filename to load, and key to store the image with
	 * @return A boolean indicating whether loading was successful
	 */
	public static boolean loadImage(String key) {
		try {
			BufferedImage img = ImageIO.read(Resources.class.getResource("/res/" + key + ".png"));
			images.put(key, img);
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Attempts to load multiple images as specified by the given String array.
	 * 
	 * @param keys Filenames to load, and keys to store the images with
	 * @return A boolean array indicating whether each load was successful
	 */
	public static boolean[] loadImages(String[] keys) {
		boolean[] success = new boolean[keys.length];
		for (int i = 0; i < keys.length; i++) {
			success[i] = loadImage(keys[i]);
		}
		return success;
	}

	/**
	 * TODO Should this call loadImage() if image is not initially present?
	 * 
	 * @param key Key of image to retrieve
	 * @return Image if found, otherwise null
	 */
	public static BufferedImage image(String key) {
		if (!images.containsKey(key)) {
			return null;
		}
		return images.get(key);
	}
}
