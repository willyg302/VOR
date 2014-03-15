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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Display extends JPanel implements ActionListener {

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
				.drawRay(256, 128, 257, 90 + (5 * needleDegrees / 2), 6,
						Color.WHITE).flush();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	private double fixDegrees(double degrees, int fix) {
		degrees += fix;
		if (degrees < 0) {
			degrees += 360;
		} else if (degrees > 359) {
			degrees -= 360;
		}
		return degrees;
	}

	public void swingWheel(int degrees) {
		wheelDegrees = fixDegrees(wheelDegrees, degrees);
		obsDegrees = fixDegrees(obsDegrees, 3 * degrees);
		repaint();
	}
}
