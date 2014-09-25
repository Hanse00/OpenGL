package dk.philiphansen.opengl;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Square {
	private int size;
	private int xCoord;
	private int yCoord;
	private float red;
	private float green;
	private float blue;
	private Directions direction;
	private int vertialMoveSpeed;
	private int horizontalMoveSpeed;

	public Square(int size, int startX, int startY, float red, float green, float blue, Directions direction, int vertialMoveSpeed, int horizontalMoveSpeed) {
		this.size = size;
		xCoord = startX;
		yCoord = startY;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.direction = direction;
		this.vertialMoveSpeed = vertialMoveSpeed;
		this.horizontalMoveSpeed = horizontalMoveSpeed;
	}

	public void update() {
		switch (direction) {
			case UP:
				if (yCoord < Display.getHeight()) {
					yCoord += vertialMoveSpeed;
				} else {
					yCoord = -size;
				}
				break;
			case DOWN:
				if (yCoord > 0) {
					yCoord -= vertialMoveSpeed;
				} else {
					yCoord = Display.getHeight() + size;
				}
				break;
			case RIGHT:
				if (xCoord < Display.getWidth()) {
					xCoord += horizontalMoveSpeed;
				} else {
					xCoord = -size;
				}
				break;
			case LEFT:
				if (xCoord > 0) {
					xCoord -= horizontalMoveSpeed;
				} else {
					xCoord = Display.getWidth() + size;
				}
				break;
		}
	}

	public void render() {
		GL11.glColor3f(red, green, blue);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(xCoord, yCoord);
		GL11.glVertex2f(xCoord + size, yCoord);
		GL11.glVertex2f(xCoord + size, yCoord + size);
		GL11.glVertex2f(xCoord, yCoord + size);
		GL11.glEnd();
	}
}
