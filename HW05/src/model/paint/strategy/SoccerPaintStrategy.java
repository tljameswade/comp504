package model.paint.strategy;

import model.paint.UprightImagePaintStrategy;

/**
 * Paint strategy that paints a FIFA soccer ball as its image
 * @author Suozhi Qi, Zhou Liu
 */
public class SoccerPaintStrategy extends UprightImagePaintStrategy {

	/**
	 * Constructor that loads the soccer ball image
	 */
	public SoccerPaintStrategy() {
		super("images/FIFA_Soccer_Ball.png", 0.3);
	}

}