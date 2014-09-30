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
	public static boolean stop;

	public static void main(String[] argv) {
		squares = new ArrayList<Square>();

		squares.add(new Square(100, 0, 100, 0.5f, 1.0f, 0.5f, Directions.RIGHT, 28));
		squares.add(new Square(100, 200, 0, 1.0f, 0.5f, 0.5f, Directions.UP, 16));
		squares.add(new Square(100, 500, 0, 0.5f, 0.5f, 1.0f, Directions.UP, 6));
		squares.add(new Square(100, 700, 400, 1.0f, 1.0f, 0.5f, Directions.LEFT, 20));
		squares.add(new Square(100, 350, 500, 1.0f, 0.75f, 0.5f, Directions.DOWN, 26));

		panel = new OptionsPanel();
		render = new Render();

		render.init();

		update();
	}

	private static void update() {
		try {
			while (!stop) {
				System.out.println("");
				Thread.sleep(1000);
			}
			stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-2);
		}
	}

	private static void stop() {
		System.out.println("Calling stop");
		render.stop();
		panel.close();
		System.exit(-1);
	}

	public static ArrayList<Square> getSquares() {
		return squares;
	}
}