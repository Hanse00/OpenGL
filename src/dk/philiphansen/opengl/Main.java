package dk.philiphansen.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main {
	public static void main(String[] argv) {
		new Main().start();
	}

	public void start() {
		try {
			createDisplay();
		} catch (LWJGLException e) {
			handleException(e);
		}

		setupOpengl();

		while (!Display.isCloseRequested()) {
			render();
			Display.update();
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
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	private void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(100,100);
		GL11.glVertex2f(100+100,100);
		GL11.glVertex2f(100+100,100+100);
		GL11.glVertex2f(100,100+100);
		GL11.glEnd();

		GL11.glColor3f(0.5f,1.0f, 0.5f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(300,100);
		GL11.glVertex2f(300+300,100);
		GL11.glVertex2f(300+300,100+300);
		GL11.glVertex2f(300,100+300);
		GL11.glEnd();

		GL11.glColor3f(1.0f,0.5f, 0.5f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(150,150);
		GL11.glVertex2f(150+200,150);
		GL11.glVertex2f(150+200,150+200);
		GL11.glVertex2f(150,150+200);
		GL11.glEnd();
	}
}