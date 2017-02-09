package view;

import java.awt.Graphics;

/**
 * The interface of the adapter from the view to the model that enables the view to talk to the model.
 * @author Suozhi Qi, Xiang Li
 * @version 1.0
 */
public interface IView2ModelAdapter {

	/**
	 * This method tells the model to paint the sprites on canvas
	 * @param g The graphics object g
	 */
	public void paint(Graphics g);

	/**
	 * This method tells the model to load a specific type of
	 * ball based on the className
	 * @param className The text input from the text frame
	 */
	public void loadBall(String className);

	/**
	 * This method tells the model to erase all of the balls
	 * on the canvas
	 */
	public void clearballs();

	/**
	 * No-op singleton implementation of IView2ModelAdapter 
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IView2ModelAdapter NULL_OBJECT = new IView2ModelAdapter() {
		public void paint(Graphics g) {
		}

		public void loadBall(String className) {
		}

		public void clearballs() {
		}

	};

}
