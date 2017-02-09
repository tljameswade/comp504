package common;

import java.io.Serializable;

import javax.swing.JComponent;

/**
 * The factory to make a JComponent
 * @author xz58
 *
 */
public interface IComponentFactory extends Serializable {
	
	/**
	 * Make a JComponent. Can be a JPanel, JButton or something else.
	 * @return
	 */
	public JComponent make();
}
