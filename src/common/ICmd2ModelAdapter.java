package common;

import java.awt.Container;

/**
 * ICmd2ModelAdapter interface is used to handle unknown types
 * */
public interface ICmd2ModelAdapter {
	/**
	 *  Get a container that is on the GUI. This could be implemented as a factory method
	 *  that creates a new Container (such as a JPanel or JFrame), and returns it here and puts it on the GUI
	 *  Scrollable components that are added are considered by the system to be just fancy displays of message contents and in such, 
	 *  just like text messages, are desired to be placed on some sort of scrolling display.
	 *  @return the Container to modify
	 *  */
	public Container getScrollable();
	
	/**
	 *  Get a container that is on the GUI. This could be implemented as a factory method
	 *  that creates a new Container (such as a JPanel or JFrame) The method will return this container and display it on the GUI.
	 *  Non-scrollable components that are added are actually static modifications of the local GUI to present additional user interaction capabilities.  
	 *  It would be undesireable for these components to scroll off the screen as other messages arrive; they should have a fixed location on the local GUI.
	 *  @return the Container to modify
	 *  */
	public Container getNonScrollable();
}
