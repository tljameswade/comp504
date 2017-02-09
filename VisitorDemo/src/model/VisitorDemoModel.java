package model;

import java.awt.Graphics;
import visitors.*;

/**
 * The main model object for the Visitor Demo system.
 * @author swong
 *
 */
public class VisitorDemoModel {

	/**
	 * The adapter out to the view
	 */
	private IViewAdapter view;
	
	/**
	 * The installed IPaintCmd instance.   Initially set to a no-op cmd.
	 */
	private IPaintCmd paintCmd = IPaintCmd.NULL_OBJECT;
	

	/**
	 * Teh cosntructor for the class
	 * @param view the adapter to the view
	 */
	public VisitorDemoModel(IViewAdapter view) {
		this.view = view;
	}

	/**
	 * Get an array of the available host types, i.e. IHost subclasses.
	 * @return
	 */
	public IHost[] getHosts() {
		return new IHost[]{new HostA(), new HostB(), new HostC()};
	}

	/**
	 * Return the results of having the given host object execute the visitor corresponding to the given class name.  A reference 
	 * to this model object is given as the visitor's input parameter.
	 * @param host  An IHost object
	 * @param visitorClassname The name of the visitor class, without the leading "visitors.impl."
	 * @return  The results of the visitor execution on the given host.
	 */
	public Object run(IHost host, String visitorClassname) {
		return  host.execute(loadVisitor(visitorClassname), this);
	}


	/**
	 * Utility method to load a visitor given its classname, without the leading "visitors.impl.".
	 * @param classname  the visitor's classname
	 * @return An IVisitor object
	 */
	private IVisitor loadVisitor(String classname){
		try {
			Object[] args = new Object[]{}; 
			java.lang.reflect.Constructor<?> cs[] = Class.forName("visitors.impl."+classname).getConstructors();  // get all the constructors
			java.lang.reflect.Constructor<?> c = null; 
			for(int i=0;i < cs.length; i++) {  // find the first constructor with the right number of input parameters
				if(args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IVisitor) c.newInstance(args);
		}
		catch(Exception ex) {
			System.err.println("Class "+classname+" failed to load. \nException = \n"+ ex);
			ex.printStackTrace();  // print the stack trace to help in debugging.
			return null;
		}


	}
	
	/**
	 * Set the model's IPaintCmd instance to the given instance.
	 * The view is called to repaint after setting the paint cmd so the 
	 * results on the viwe will show up.
	 * @param cmd  The new IPaintCmd
	 */
	public void setPaintCmd(IPaintCmd cmd){
		paintCmd = cmd;
		view.repaint();
	}

	/**
	 * Paint on the given Graphics object using the current IPaintCmd object in the model.
	 * @param g  The Graphics context on which to draw.
	 */
	public void paint(Graphics g) {
		paintCmd.apply(g);
	}

}
