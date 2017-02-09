package sq6_kt21.server.game.view;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.globes.Earth;
import gov.nasa.worldwind.layers.Layer;
import map.MapPanel;

/**
 * The map view to inherit mapPanel
 * @author Kun Tian, Suozhi Qi
 *
 */
public class MapView extends MapPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3834584038298510884L;

	/**
	 * The constructor of the MapView
	 */
	public MapView(){
		super(Earth.class);
	}
	
	/**
	 * Get the WorldWindowGLCanvas
	 * 
	 * @return  The WorldWindowGLCanvas
	 */
	public WorldWindowGLCanvas getWWD() {
		return super.getWWD();
	}
	
	/**
	 * Insert layer just beneath the Compass layer.
	 * 
	 * @param layer
	 *            - layer to insert
	 */
	public void insertBeforeCompass(Layer layer) {
		super.insertBeforeCompass(layer);
	}
}
