package vor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Gfx {
	
	Graphics2D g2d;
	
	public Gfx(Graphics g) {
		this.g2d = (Graphics2D) g.create();
	}

	public static Gfx create(Graphics g) {
		return new Gfx(g);
	}
	
	public Gfx drawImage(ImageObserver context, String key, int x, int y) {
		g2d.drawImage(Resources.image(key), x, y, context);
		return this;
	}
	
	public Gfx drawImageRotated(ImageObserver context, String key, double x, double y, double degrees) {
		BufferedImage image = Resources.image(key);
		if (image != null) {
			AffineTransform trans = new AffineTransform();
			trans.translate(x, y);
			trans.rotate(Math.toRadians(degrees), image.getWidth() / 2, image.getHeight() / 2);
			g2d.drawImage(Resources.image(key), trans, context);
		}
		return this;
	}
	
	public void flush() {
		g2d.dispose();
	}
}
