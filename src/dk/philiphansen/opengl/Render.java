package dk.philiphansen.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Render {
	public void init() {
		try {
			createDisplay();
		} catch (LWJGLException e) {
			handleException(e);
		}

		setupOpengl();

		while (Main.running) {
			if (Display.isCloseRequested()) {
				Main.running = false;
			}
			update();
			Display.sync(60);
		}

		Display.destroy();
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

	private void update() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		for (Square square : Main.getSquares()) {
			square.render();
		}

		Display.update();
	}
}
