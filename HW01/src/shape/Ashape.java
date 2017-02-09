package shape;

import java.awt.Graphics;

/**
 * This is an abstract class that other concrete shape classes
 * can extends on
 * @author Suozhi Qi, Yiqing Lu
 * @version 1.0
 * 
 */
public abstract class Ashape {
	/**
	 * The x coordinate is set to 50 as the default
	 */
	protected int xcord = 50;

	/**
	 * The y coordinate is set to 60 as the default
	 */
	protected int ycord = 60;

	/**
	 * This sets the x coordinate
	 * @param value is the value of x coordinate
	 */
	void setXcord(int value) {
		this.xcord = value;
	}

	/**
	 * This returns the x coordinate
	 * @return returns the value of the x coordinate
	 */
	int getXcord() {
		return this.xcord;
	}

	/**
	 * This sets the y coordinate
	 * @param value is the value of y coordinate
	 */
	void setYcord(int value) {
		this.ycord = value;
	}

	/**
	 * This returns the y coordinate
	 * @return returns the value of the y coordinate
	 */
	int getYcord() {
		return this.ycord;
	}

	/**
	 * This is an abstract painting method that can be overwritten
	 * in concrete sub classes
	 * @param g is an instance of the Graphics class
	 */
	public abstract void paint(Graphics g);

}
