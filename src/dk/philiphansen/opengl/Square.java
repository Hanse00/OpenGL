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
	private int moveSpeed;

	public Square(int size, int startX, int startY, float red, float green, float blue, Directions direction, int moveSpeed) {
		this.size = size;
		xCoord = startX;
		yCoord = startY;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.direction = direction;
		this.moveSpeed = moveSpeed;
	}

	public void update() {
		move();
		if (outOfBounds()) {
			reset();
		}
	}

	private void move() {
		xCoord += direction.getX() * moveSpeed;
		yCoord += direction.getY() * moveSpeed;
	}

	private void reset() {
		switch (direction) {
			case UP:
				yCoord = -size;
				break;
			case DOWN:
				yCoord = Display.getHeight();
				break;
			case RIGHT:
				xCoord = -size;
				break;
			case LEFT:
				xCoord = Display.getWidth();
				break;
		}
	}

	private boolean outOfBounds() {
		return outLeft() || outRight() || outBottom() || outTop();
	}

	private boolean outLeft() {
		return xCoord + size < 0;
	}

	private boolean outRight() {
		return xCoord > Display.getWidth();
	}

	private boolean outBottom() {
		return yCoord + size < 0;
	}

	private boolean outTop() {
		return yCoord > Display.getHeight();
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
