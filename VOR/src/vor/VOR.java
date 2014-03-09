package vor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
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
 * Main class for VOR, manages link between radio and display. It is here that
 * relevant calculations are done by taking information from the radio and
 * routing it to the display.
 * 
 * @author William
 */
public class VOR {
	public static final String APP_NAME = "VOR";
	public static final String VERSION = "1.0";

	private JFrame frame;

	private Radio radio;
	private Display display;

	public VOR() {
		// Simulated radio
		radio = new Radio();

		// Our GUI
		display = new Display();
		display.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					display.swingWheel(-1);
				} else if (key == KeyEvent.VK_RIGHT) {
					display.swingWheel(1);
				}
			}
		});
		display.setFocusable(true);
		display.requestFocusInWindow();

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
					+ "A VHF omnidirectional range simulator created for ICS 314<br>"
					+ "by William Gaul, David Do, &amp; Landon Soriano.<br>"
					+ "<h2>Directions:</h2>"
					+ "Coming soon!"
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
				new VOR();
			}
		});
	}
}
