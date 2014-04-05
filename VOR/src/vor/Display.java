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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Display extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	private VOR vor;

	public Display() {
		vor = new VOR();
		
		Resources.loadImages(new String[] {"base", "obs", "wheel", "to", "from", "good", "bad", "font"});
		
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(512, 576);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Oddly, Swing rotates components opposite of our display rotation, so we need to do 360 - desired here
		double wheelDegrees = 360 - vor.getOBS();
		double obsDegrees = (3 * wheelDegrees) % 360;
		
		Gfx gfx = Gfx.create(g)
				.drawImage(this, "base", 0, 0)
				.drawImageRotated(this, "obs", 7, 470, obsDegrees)
				.drawImageRotated(this, "wheel", 0, 64, wheelDegrees);
		if (vor.isSignalGood()) {
			gfx.drawImage(this, vor.isGoingTo() ? "to" : "from", 305, 299);
		}
		gfx.drawImage(this, vor.isSignalGood() ? "good" : "bad", 160, 309)
				.drawRay(256, 192, 257, 90 + (5 * vor.getCDI() / 2), 6, Color.WHITE)
				.drawText(this, "font", 33, 25, String.format("%03d", vor.getOBS()))
				.drawText(this, "font", 391, 25, vor.getStationID())
				.flush();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			onRotateOBS(-1);
		} else if (key == KeyEvent.VK_RIGHT) {
			onRotateOBS(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//
	}
	
	public void onRotateOBS(int delta) {
		vor.rotateOBS(delta);
		this.repaint();
	}
}
