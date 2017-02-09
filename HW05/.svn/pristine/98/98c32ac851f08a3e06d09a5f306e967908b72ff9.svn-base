package model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class BallShapeFactory implements IShapeFactory {

	/**
	 * Singleton pattern
	 */
	public static BallShapeFactory Singleton = new BallShapeFactory();

	private BallShapeFactory() {
	}

	/**
	 * Instantiates an ellipse
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Ellipse2D.Double(x - xScale, y - yScale, 2 * xScale, 2 * yScale);
	}

}