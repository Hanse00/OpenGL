package dk.philiphansen.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {
	private static List<Square> squares;
	private static OptionsPanel panel;
	private static int timer;
	private static long lastTime;
	private static boolean running;
	private static Thread renderThread;

	public static void main(String[] argv) {
		running = true;

		squares = Collections.synchronizedList(new ArrayList<Square>());
		squares.add(new Square(100, 100, 0, 1.0f, 0.5f, 0.5f, Directions.UP, 20));

		panel = new OptionsPanel();

		renderThread = new Thread(new Render());
		renderThread.start();

		mainLoop();
	}

	private static void mainLoop() {
		while (running) {
			timer += getDelta();

			if (timer >= ((float) 1/60 * 1000)) {
				synchronized (squares) {
					Iterator<Square> iterator = squares.iterator();
					while (iterator.hasNext()) {
						iterator.next().update();
					}
				}
				timer = 0;
			}
			sleep();
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			stop();
		}
	}

	public static synchronized void stop() {
		running = false;
		renderThread.interrupt();
		System.exit(0);
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

	public static List<Square> getSquares() {
		return squares;
	}

	public static void addSquare(Square square) {
		squares.add(square);
	}

	public static void clearSquares() {
		squares.clear();
	}
}