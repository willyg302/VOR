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
		Graphics2D g2d = (Graphics2D) g.create();
		
		
		
		g2d.drawImage(Resources.image("base"), 0, 0, this);
		g2d.drawImage(Resources.image("obs"), 7, 406, this);
		g2d.drawImage(Resources.image("wheel"), 0, 0, this);
		
		g2d.drawImage(Resources.image(to ? "to" : "from"), 305, 232, this);
		
		g2d.dispose();
	}
}
