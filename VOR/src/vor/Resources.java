package vor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Resources {
	private static HashMap<String, BufferedImage> images = new HashMap<>();
	
	public static boolean loadImage(String key) {
		try {
			BufferedImage img = ImageIO.read(Resources.class.getResource("/res/" + key + ".png"));
			images.put(key,	img);
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static BufferedImage image(String key) {
		if (!images.containsKey(key)) {
			return null;
		}
		return images.get(key);
	}
}
