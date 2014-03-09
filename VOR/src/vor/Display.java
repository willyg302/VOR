package vor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private boolean to;
	private double obsDegrees, wheelDegrees, needleDegrees;

	public Display() {
		to = true;
		obsDegrees = 0;
		wheelDegrees = 0;
		needleDegrees = 0;
		Resources.loadImage("base");
		Resources.loadImage("obs");
		Resources.loadImage("wheel");
		Resources.loadImage("to");
		Resources.loadImage("from");
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(512, 512);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Gfx.create(g)
		.drawImage(this, "base", 0, 0)
		.drawImageRotated(this, "obs", 7, 406, obsDegrees)
		.drawImageRotated(this, "wheel", 0, 0, wheelDegrees)
		.drawImage(this, to ? "to" : "from", 305, 232)
		.drawRay(256, 128, 257, 90 + (5 * needleDegrees / 2), 6, Color.WHITE)
		.flush();
	}
}
