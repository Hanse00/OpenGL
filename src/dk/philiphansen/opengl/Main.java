package dk.philiphansen.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main {
	private int timer;
	private long lastTime;
	private ArrayList<Square> squares = new ArrayList<Square>();
	private OptionsPanel panel;

	public static void main(String[] argv) {
		new Main().start();
	}

	public void start() {
		panel = new OptionsPanel();

		try {
			createDisplay();
		} catch (LWJGLException e) {
			handleException(e);
		}

		setupOpengl();

		squares.add(new Square(100, 0, 100, 0.5f, 1.0f, 0.5f, Directions.RIGHT, 28));
		squares.add(new Square(100, 200, 0, 1.0f, 0.5f, 0.5f, Directions.UP, 16));
		squares.add(new Square(100, 500, 0, 0.5f, 0.5f, 1.0f, Directions.UP, 6));
		squares.add(new Square(100, 700, 400, 1.0f, 1.0f, 0.5f, Directions.LEFT, 20));
		squares.add(new Square(100, 350, 500, 1.0f, 0.75f, 0.5f, Directions.DOWN, 26));

		while (!Display.isCloseRequested()) {
			update();
			Display.update();
			Display.sync(60);
		}
		close();
	}

	private void createDisplay() throws LWJGLException {
		Display.setTitle("OpenGL Test");
		Display.setDisplayMode(new DisplayMode(800,600));
		Display.create();
	}

	private void handleException(LWJGLException e) {
		e.printStackTrace();
		System.exit(0);
	}

	private void setupOpengl() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	private void close() {
		Display.destroy();
		WindowEvent event = new WindowEvent(panel, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
		panel.setVisible(false);
		panel.dispose();
	}

	private void update() {
		timer += getDelta();

		if (timer >= 20) {
			for (Square square : squares) {
				square.update();
			}
			timer = 0;
		}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		for (Square square : squares) {
			square.render();
		}
	}

	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastTime);
		lastTime = time;

		return delta;
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}