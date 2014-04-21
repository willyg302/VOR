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
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

/**
 * Our desktop Java app.
 * 
 * @author William Gaul
 */
public class App {
	public static final String APP_NAME = "VOR";
	public static final String VERSION = "1.0";

	private JFrame frame;
	
	private Display display;

	public App() {
		// Our GUI
		display = new Display();
		
		// Make actual frame
		initializeFrame();
	}

	private void initializeFrame() {
		frame = new JFrame(APP_NAME);
		frame.add(display);
		initializeMenuBar();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				handleQuit();
			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initializeMenuBar() {
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");

		// FILE
		fileMenu.add(new JMenuItem(exitAction));

		// HELP
		helpMenu.add(new JMenuItem(aboutAction));

		// Actual MENU BAR
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		frame.setJMenuBar(menuBar);
	}

	Action exitAction = new AbstractAction("Exit") {
		private static final long serialVersionUID = 1L;

		{
			putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_Q, Toolkit.getDefaultToolkit()
							.getMenuShortcutKeyMask()));
		}

		public void actionPerformed(ActionEvent e) {
			handleQuit();
		}
	};

	Action aboutAction = new AbstractAction("About " + APP_NAME) {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1));
			JTextPane textPane = new JTextPane();
			textPane.setContentType("text/html");
			textPane.setText("<body style=\"font-family: arial\">"
					+ String.format("<h1>%s %s</h1>", APP_NAME, VERSION)
					+ "A VHF Omnidirectional Range simulator created for ICS 314<br>"
					+ "by William Gaul, David Do, &amp; Landon Soriano.<br>"
					+ "<h2>Directions:</h2>"
					+ "Use the left and right arrow keys to set your desired radial.<br>"
					+ "More coming soon!"
					+ "</body>");
			textPane.setBackground(new Color(0xeeeeee));
			textPane.setEditable(false);
			panel.add(textPane);
			JOptionPane.showMessageDialog(frame, panel, "About " + APP_NAME,
					JOptionPane.INFORMATION_MESSAGE);
		}
	};

	public void handleQuit() {
		// We can intercept window close and perform any cleanup here
		Runtime.getRuntime().exit(0);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new App();
			}
		});
	}
}
