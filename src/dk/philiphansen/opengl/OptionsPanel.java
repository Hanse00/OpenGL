package dk.philiphansen.opengl;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.*;
import java.util.ArrayList;
import java.util.Locale;

public class OptionsPanel extends JFrame {
	private NumberFormatter integerFormatter;
	private JPanel panel;
	private JFormattedTextField sizeField;
	private JFormattedTextField speedField;
	private JSlider startXSlider;
	private JSlider startYSlider;
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	private JComboBox directionPicker;
	private JButton addButton;
	private JButton clearButton;

	public OptionsPanel() {
		setupFrame();
		setupPanel();
		setupIntegerFormat();

		defineIntegerFields();
		defineSliders();
		defineComboBox();
		defineButton();

		addAllComponents();
		add(panel);
		setVisible(true);
	}

	private void setupFrame() {
		setTitle("Options");
		setSize(800, 100);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.stop();
			}
		});
	}

	private void setupPanel() {
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setLayout(new GridLayout(1, 11));
	}

	private void defineIntegerFields() {
		sizeField = new JFormattedTextField(integerFormatter);
		speedField = new JFormattedTextField(integerFormatter);
	}

	private void defineSliders() {
		startXSlider = new JSlider(JSlider.HORIZONTAL, 0, 800, 0);
		startYSlider = new JSlider(JSlider.HORIZONTAL, 0, 600, 0);

		redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	}

	private void defineComboBox() {
		directionPicker = new JComboBox(new String[]{"Up", "Down", "Left", "Right"});
		directionPicker.setSelectedIndex(0);
	}

	private void defineButton() {
		addButton = new JButton("Add");
		clearButton = new JButton("Clear");

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.parseInt(sizeField.getText());
				int startX = startXSlider.getValue();
				int startY = startYSlider.getValue();
				float red = redSlider.getValue() / (float) 255;
				float green = greenSlider.getValue() / (float) 255;
				float blue = blueSlider.getValue() / (float) 255;
				Directions direction;

				switch (directionPicker.getSelectedIndex()) {
					case 0:
						direction = Directions.UP;
						break;
					case 1:
						direction = Directions.DOWN;
						break;
					case 2:
						direction = Directions.LEFT;
						break;
					case 3:
						direction = Directions.RIGHT;
						break;
					default:
						direction = Directions.UP;
				}

				int speed = Integer.parseInt(speedField.getText());

				Main.addSquare(new Square(size, startX, startY, red, green, blue, direction, speed));
			}
		});

		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.clearSquares();
			}
		});
	}

	private void addAllComponents() {
		panel.add(sizeField);
		panel.add(startXSlider);
		panel.add(startYSlider);
		panel.add(redSlider);
		panel.add(greenSlider);
		panel.add(blueSlider);
		panel.add(directionPicker);
		panel.add(speedField);
		panel.add(addButton);
		panel.add(clearButton);
	}

	private void setupIntegerFormat() {
		integerFormatter = new NumberFormatter(new DecimalFormat("#####")) {
			@Override
			public String valueToString(Object value) throws ParseException {
				if (value == null) {
					return "";
				} else {
					return super.valueToString(value);
				}
			}

			@Override
			public Object stringToValue(String string) throws ParseException {
				if (string.equals("")) {
					return null;
				} else {
					return super.stringToValue(string);
				}
			}
		};
		integerFormatter.setValueClass(Integer.class);
		integerFormatter.setMinimum(0);
		integerFormatter.setMaximum(Integer.MAX_VALUE);
		integerFormatter.setAllowsInvalid(false);
		integerFormatter.setCommitsOnValidEdit(true);
	}

	public void close() {
		dispose();
	}
}
