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
	private JLabel sizeLabel;
	private JLabel startXLabel;
	private JLabel startYLabel;
	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	private JLabel directionLabel;
	private JLabel speedLabel;
	private GridBagConstraints constraints;

	public OptionsPanel() {
		setupFrame();
		setupPanel();
		setupIntegerFormat();

		defineLabels();
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
		setSize(800, 200);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.stop();
			}
		});
	}

	private void setupPanel() {
		panel = new JPanel();
		setResizable(false);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.ipadx = 10;
		constraints.ipady = 10;
		constraints.insets = new Insets(5, 5, 5, 5);
	}

	private void defineLabels() {
		sizeLabel = new JLabel("Size");
		startXLabel = new JLabel("X Position");
		startYLabel = new JLabel("Y Position");
		redLabel = new JLabel("Red");
		greenLabel = new JLabel("Green");
		blueLabel = new JLabel("Blue");
		directionLabel = new JLabel("Direction");
		speedLabel = new JLabel("Speed");
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
		panel.add(sizeLabel, constraints);

		constraints.gridx = 1;
		panel.add(startXLabel, constraints);

		constraints.gridx = 2;
		panel.add(startYLabel, constraints);

		constraints.gridx = 3;
		panel.add(redLabel, constraints);

		constraints.gridx = 4;
		panel.add(greenLabel, constraints);

		constraints.gridx = 5;
		panel.add(blueLabel, constraints);

		constraints.gridx = 6;
		panel.add(directionLabel, constraints);

		constraints.gridx = 7;
		panel.add(speedLabel, constraints);

		constraints.gridy = 1;
		constraints.gridx = 0;
		panel.add(sizeField, constraints);

		constraints.gridy = 1;
		constraints.gridx = 1;
		panel.add(startXSlider, constraints);

		constraints.gridy = 1;
		constraints.gridx = 2;
		panel.add(startYSlider, constraints);

		constraints.gridy = 1;
		constraints.gridx = 3;
		panel.add(redSlider, constraints);

		constraints.gridy = 1;
		constraints.gridx = 4;
		panel.add(greenSlider, constraints);

		constraints.gridy = 1;
		constraints.gridx = 5;
		panel.add(blueSlider, constraints);

		constraints.gridy = 1;
		constraints.gridx = 6;
		panel.add(directionPicker, constraints);

		constraints.gridy = 1;
		constraints.gridx = 7;
		panel.add(speedField, constraints);

		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		panel.add(addButton, constraints);

		constraints.gridy = 2;
		constraints.gridx = 6;
		constraints.gridwidth = 2;
		panel.add(clearButton, constraints);
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
