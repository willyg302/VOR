package vor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	
	public Gfx drawImageRotated(ImageObserver context, String key, int x, int y, double degrees) {
		BufferedImage image = Resources.image(key);
		if (image != null) {
			double rad = Math.toRadians(degrees);
			double w = image.getWidth() / 2;
			double h = image.getHeight() / 2;
			AffineTransform at = AffineTransform.getRotateInstance(rad, w, h);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter(image, null), x, y, context);
		}
		return this;
	}
	
	public Gfx drawRay(int x, int y, int length, double degrees, int thickness, Color color) {
		g2d.setStroke(new BasicStroke(thickness));
		g2d.setColor(color);
		double rad = Math.toRadians(degrees);
		int a = x + (int) (length * Math.cos(rad));
		int b = y + (int) (length * Math.sin(rad));
		g2d.drawLine(x, y, a, b);
		return this;
	}
	
	public void flush() {
		g2d.dispose();
	}
}
