package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is a concrete class Circle that extends the Ashape abstract class
 * @author Suozhi Qi, Yiqing Lu
 * @version 1.0
 * 
 */
public class Circle extends Ashape {
	/**
	 * The radius of a circle is set to 10 as the default
	 */
	private int radius = 10;

	/**
	 * The color of a circle is set to MAGENTA as the default
	 */
	private Color colorCircle = Color.MAGENTA;

	/**
	 * This is a constructor implements a circle that can be painted
	 * @param x x-coordinate of the upper left corner of the circle
	 * @param y y-coordinate of the upper left corner of the circle
	 * @param radius radius of the circle
	 */
	public Circle(int x, int y, int radius) {
		this.xcord = x;
		this.ycord = y;
		this.radius = radius;
	}

	/**
	 * This paints a circle
	 * @param g is an instance of the Graphics class
	 */
	public void paint(Graphics g) {
		g.setColor(colorCircle);
		g.fillOval(xcord, ycord, radius, radius);
	}

}
