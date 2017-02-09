package model.paint.strategy;

import java.awt.geom.AffineTransform;

import model.paint.ShapePaintStrategy;
import model.paint.shape.RectangleShapeFactory;

/**
 * The paint strategy that paints a rectangle
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class RectanglePaintStrategy extends ShapePaintStrategy {

	/**
	 * The default constructor for a rectangle
	 */
	public RectanglePaintStrategy() {
		this(new AffineTransform(), 0, 0, 1, 1);
	}

	/**
	 * Constructor that takes an external AffineTransform for internal use 
	 * and a Rectangle centered at (x, y) with the given half-width and 
	 * half-height (= x-radius and y-radius).
	 * @param at The AffineTransform to use
	 * @param x The x-coordinate of the center of the prototype rectangle
	 * @param y The y-coordinate of the center of the prototype rectangle
	 * @param xScale The half-width of the rectangle, i.e. the width as measured from the center.
	 * @param yScale The half-height of the rectangle, i.e. the height as measured from the center.
	 */
	public RectanglePaintStrategy(AffineTransform at, double x, double y, double xScale, double yScale) {
		super(at, RectangleShapeFactory.Singleton.makeShape(x, y, xScale, yScale));
	}

}
