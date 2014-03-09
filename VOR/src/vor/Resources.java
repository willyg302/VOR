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
