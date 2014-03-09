package vor;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class VOR {
	public static final String APP_NAME = "VOR";
	public static final String VERSION = "1.0";
	public JFrame frame;

	public VOR() {

		// Make actual frame
		frame = new JFrame(APP_NAME);
		frame.setPreferredSize(new Dimension(518, 540));
		frame.add(new Display());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				handleQuit();
			}
		});
		frame.setVisible(true);
	}

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
