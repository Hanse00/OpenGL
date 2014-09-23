package dk.philiphansen.heart;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DisplayExample {
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		while (!Display.isCloseRequested()) {

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

			Display.update();
		}

		Display.destroy();
	}

	public static void main(String[] argv) {
		DisplayExample displayExample = new DisplayExample();
		displayExample.start();
	}
}