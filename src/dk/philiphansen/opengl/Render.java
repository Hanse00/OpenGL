package dk.philiphansen.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;

public class Render implements Runnable {
	@Override
	public void run() {
		try {
			createDisplay();
		} catch (LWJGLException e) {
			handleException(e);
		}

		setupOpengl();
		updateLoop();
	}

	private void createDisplay() throws LWJGLException {
		Display.setTitle("OpenGL Test");
		Display.setDisplayMode(new DisplayMode(800,600));
		Display.create();
	}

	private void handleException(LWJGLException e) {
		e.printStackTrace();
		Main.stop();
	}

	private void setupOpengl() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	private void updateLoop() {
		while (!Thread.interrupted()) {
			if (Display.isCloseRequested()) {
				Main.stop();
			}
			render();
			Display.sync(60);
		}
		Display.destroy();
	}

	private void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		synchronized (Main.getSquares()) {
			Iterator<Square> iterator = Main.getSquares().iterator();
			while (iterator.hasNext()) {
				iterator.next().render();
			}
		}

		Display.update();
	}

	public int getWidth() {
		return Display.getWidth();
	}

	public int getHeight() {
		return Display.getHeight();
	}
}
