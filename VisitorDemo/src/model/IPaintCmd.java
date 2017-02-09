package model;

import java.awt.Graphics;

/**
 * A command used to paint on the given Graphics object
 * @author swong
 *
 */
public interface IPaintCmd {
	/**
	 * Run the command, using the given Graphics object
	 * @param g  The Graphics object used for custom painting.
	 */
	public void apply(Graphics g);
	
	
	/**
	 * A no-op null paint cmd
	 */
	public static final IPaintCmd NULL_OBJECT =  new IPaintCmd(){
		public void apply(Graphics g){
		}
	};
}
