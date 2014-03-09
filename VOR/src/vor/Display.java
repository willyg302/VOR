package vor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private boolean to;

	public Display() {
		to = true;
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
		.drawImageRotated(this, "obs", 7, 406, 90)
		.drawImageRotated(this, "wheel", 0, 0, 130)
		.drawImage(this, to ? "to" : "from", 305, 232)
		.flush();
	}
}
