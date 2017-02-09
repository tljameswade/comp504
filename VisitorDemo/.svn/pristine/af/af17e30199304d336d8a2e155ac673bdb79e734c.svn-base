package view;

import java.awt.Graphics;

/**
 * The view's adapter to the model
 * @author swong
 *
 * @param <CBoxItem>  The type of objects being put in the host JComboBox
 */
public interface IModelAdapter<CBoxItem> {

	/**
	 * Get an array of CBoxItems to place in the JComboBox
	 * @return
	 */
	CBoxItem[] getHosts();
	
	/**
	 * Ask the model to process the given host object with the given a visitor
	 * @param host  A host item
	 * @param visitorClassname  The name of the visitor to use, though the leading "visitors.impl." part is missing.
	 * @return  The results of this particular algorithm.
	 */
	String run(CBoxItem host, String visitorClassname);
	
	/**
	 * Ask the model to paint something on this Graphics object.
	 * @param g
	 */
	void paint(Graphics g);
	
}
