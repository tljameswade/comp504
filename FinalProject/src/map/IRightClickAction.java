package map;

import gov.nasa.worldwind.geom.Position;

/**
 * Interface used by MapPanel to define right-click actions
 * @author Scott Rixner
 *
 */
public interface IRightClickAction {
	
	/**
	 * The action to perform
	 * @param p  MapPanel supplies a Position subclass that where getAltitude() returns the eye point altitude at the time of the click.
	 */
	public void apply(Position p);
}
