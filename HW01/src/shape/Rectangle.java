package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is a concrete class Rectangle that extends
 * the abstract class Ashape
 * @author Suozhi Qi, Yiqing Lu
 * @version 1.0
 * 
 */
public class Rectangle extends Ashape {
	/**
	 * The color of a rectangle is set to orange as the default
	 */
	private Color colorRec = Color.orange;

	/**
	 * The height of a rectangle set to 20 as the default
	 */
	private int height = 20;

	/**
	 * The width of a rectangle set to 30 as the default
	 */
	private int width = 30;

	/**
	 * This is a constructor implements a Rectangle that
	 * can be painted
	 * @param x is the x coordinate of the upper left corner of a Rectangle
	 * @param y is the y coordinate of the upper left corner of a Rectangle
	 * @param width is the width of a Rectangle
	 * @param height is the height of a Rectangle
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.xcord = x;
		this.ycord = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * This paints a Rectangle
	 * @param g is an instance of the Graphics class
	 */
	public void paint(Graphics g) {
		g.setColor(colorRec);
		g.fillRect(xcord, ycord, width, height);
	}

}
