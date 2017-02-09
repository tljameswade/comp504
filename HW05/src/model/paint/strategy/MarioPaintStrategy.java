package model.paint.strategy;

import model.paint.UprightImagePaintStrategy;

/**
 * Paint strategy that paints a Mario as its image
 * @author Suozhi Qi, Zhou Liu
 */
public class MarioPaintStrategy extends UprightImagePaintStrategy {

	/**
	 * Constructor that loads the mario image
	 */
	public MarioPaintStrategy() {
		super("images/Mario_animate.gif", 0.8);
	}

}
