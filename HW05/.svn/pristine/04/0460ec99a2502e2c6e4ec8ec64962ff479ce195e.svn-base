package view;

import java.awt.Graphics;

/**
 * The model paint adapter
 * @author Suozhi Qi, Zhou Liu
 *
 */
public interface IModelPaintAdapter {
	/**
	 * This method tells the model to paint the sprites on canvas
	 * @param g The graphics object g
	 */
	public void paint(Graphics g);

	/**
	 * No-op singleton implementation of the IModelPaintAdapter
	 */
	public static final IModelPaintAdapter NULL_OBJECT = new IModelPaintAdapter() {

		public void paint(Graphics g) {
		}
	};
}
