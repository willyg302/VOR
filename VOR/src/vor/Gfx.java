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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Encapsulates a {@link Graphics2D} object for chained convenience drawing methods.
 * One should always call Gfx.create() first with an associated {@link Graphics} object.
 * After all drawing is complete, call flush().
 * 
 * @author William
 */
public class Gfx {
	
	Graphics2D g2d;
	
	private Gfx(Graphics g) {
		this.g2d = (Graphics2D) g.create();
	}

	public static Gfx create(Graphics g) {
		return new Gfx(g);
	}
	
	public Gfx drawImage(ImageObserver context, String key, int x, int y) {
		BufferedImage image = Resources.image(key);
		if (image != null) {
			g2d.drawImage(image, x, y, context);
		}
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
