package dk.philiphansen.opengl;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OptionsPanel extends JFrame {
	private ArrayList<Square> squares = new ArrayList<Square>();

	public OptionsPanel() {
		setTitle("Options");
		setSize(300, 500);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.stop = true;
			}
		});
	}

	public void close() {
		System.out.println("Stopping control panel");
		dispose();
	}


}
