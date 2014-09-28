package dk.philiphansen.opengl;

import javax.swing.*;
import java.util.ArrayList;

public class OptionsPanel extends JFrame {
	private ArrayList<Square> squares = new ArrayList<Square>();

	public OptionsPanel() {
		setTitle("Options");
		setSize(300, 500);
		setVisible(true);
	}
}
