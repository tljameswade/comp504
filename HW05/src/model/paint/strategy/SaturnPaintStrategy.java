package model.paint.strategy;

import model.paint.UprightImagePaintStrategy;

/**
 * Paint strategy that paints a Saturn as its image
 * @author Suozhi Qi, Zhou Liu
 *
 */
public class SaturnPaintStrategy extends UprightImagePaintStrategy {

	/**
	 * Constructor that loads the Saturn image
	 */
	public SaturnPaintStrategy() {
		super("images/Saturn.png", 0.8);
	}

}
