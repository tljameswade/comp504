package model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Concrete IShapeFactory that instantiates Rectangle2d.Double shapes.
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class RectangleShapeFactory implements IShapeFactory {

	/**
	 * Singleton pattern
	 */
	public static RectangleShapeFactory Singleton = new RectangleShapeFactory();

	private RectangleShapeFactory() {
	}

	@Override
	/**
	 * Instantiates a Rectangle2D.Double object at the specified orign and size.
	 * @param x The x-coordinate of the center of the prototype rectangle
	 * @param y The y-coordinate of the center of the prototype rectangle
	 * @param xScale The half-width of the rectangle, i.e. the width as measured from the center.
	 * @param yScale The half-hieght of the rectangle, i.e. the height as measured from the center.
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Rectangle2D.Double(x - xScale, y - yScale, 2 * xScale, 2 * yScale);
	}

}
