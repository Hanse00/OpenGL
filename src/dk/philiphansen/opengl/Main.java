package dk.philiphansen.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main {
	private static ArrayList<Square> squares;
	private static OptionsPanel panel;
	private static Render render;
	private static int timer;
	private static long lastTime;
	public static boolean running;

	public static void main(String[] argv) {
		running = true;

		squares = new ArrayList<Square>();

		panel = new OptionsPanel();
		render = new Render();

		render.init();

		update();
	}

	private static void update() {
		while (running) {
			timer += getDelta();
			System.out.println(timer);

			if (timer >= 20) {
				for (Square square : squares) {
					square.update();
				}
				timer = 0;
			}
		}
	}

	private static int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastTime);
		lastTime = time;

		return delta;
	}

	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static ArrayList<Square> getSquares() {
		return squares;
	}
}