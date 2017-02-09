package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is a concrete class Arcshape that extends the Ashape abstract class
 * @author Suozhi Qi, Yiqing Lu
 * @version 1.0
 * 
 */
public class Arcshape extends Ashape {
	/**
	 * The color of an Arcshape is set to Cyan as the default
	 */
	private Color colorRec = Color.cyan;

	/**
	 * The start angle of an Arcshape is set to 30 as the default
	 */
	private int sAngle = 30;

	/**
	 * The angle of an Arcshape is set to 60 as the default
	 */
	private int arcAngle = 60;

	/**
	 * The width of an Arcshape is set to 20 as the default
	 */
	private int width = 20;

	/**
	 * The height of an Arcshape is set to 30 as the default
	 */
	private int height = 30;

	/**
	 * This is a constructor implements an Arcshape that can be painted
	 * @param x x-coordinate of the upper left corner of the Arcshape
	 * @param y y-coordinate of the upper left corner of the Arcshape
	 * @param width the width of the Arcshape
	 * @param height the height of the Arcshape
	 */
	public Arcshape(int x, int y, int width, int height) {
		this.xcord = x;
		this.ycord = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * This paints an Arcshape
	 * @param g is an instance of the Graphics class
	 */
	public void paint(Graphics g) {
		g.setColor(colorRec);
		g.fillArc(xcord, ycord, width, height, sAngle, arcAngle);
		;
	}

}
