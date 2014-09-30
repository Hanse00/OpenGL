package dk.philiphansen.opengl;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OptionsPanel extends JFrame {
	public OptionsPanel() {
		setTitle("Options");
		setSize(300, 500);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.running = false;
			}
		});
	}

	public void close() {
		dispose();
	}
}
