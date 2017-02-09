package map;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
//import gov.nasa.worldwind.event.RenderingEvent;
import gov.nasa.worldwind.event.RenderingExceptionListener;
//import gov.nasa.worldwind.event.RenderingListener;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwindx.examples.ClickAndGoSelectListener;
import gov.nasa.worldwindx.examples.util.HighlightController;
import gov.nasa.worldwindx.examples.util.ToolTipController;
import gov.nasa.worldwind.exception.WWAbsentRequirementException;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.SkyColorLayer;
import gov.nasa.worldwind.layers.SkyGradientLayer;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;
import gov.nasa.worldwind.layers.WorldMapLayer;
import gov.nasa.worldwind.util.StatusBar;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwind.view.orbit.FlatOrbitView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Specialized JPanel that displays a WorldWind map of the world.
 * Specialized versions of MapPanel should be subclasses that use the protected accessor methods, providing 
 * encapsulated access to manipulations of those protected entities.   Do not expose internal data!
 * 
 * @author Scott Rixner, Stephen Wong
 */
public class MapPanel extends JPanel   {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5382439647127773669L;

	/**
	 * Actual Map Canvas
	 */
	private WorldWindowGLCanvas _wwd = new WorldWindowGLCanvas();

	/**
	 * Map to associate IRightClickActions that have been added with the actual MouseListener that is installed.
	 */
	private Map<IRightClickAction, MouseListener> rightClickActions = new Hashtable<IRightClickAction, MouseListener>();

	/**
	 * The current altitude of the map.  A hack to get around bug in returned position from _wwd
	 */
	private double currentAltitude;

	/**
	 * Initialize configuration options for WorldWind to operate correctly.
	 * @param globeTypeClass 
	 */
	protected void initconfig(Class<? extends Globe> globeTypeClass) {
		// Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
		Configuration.setValue(AVKey.GLOBE_CLASS_NAME, globeTypeClass.getName());
		Configuration.setValue(AVKey.VIEW_CLASS_NAME,
				FlatOrbitView.class.getName());
		Configuration
				.setValue(
						AVKey.LAYERS_CLASS_NAMES,
						"gov.nasa.worldwind.layers.CompassLayer,"
								+ "gov.nasa.worldwind.layers.Earth.NASAWFSPlaceNameLayer,"
								+ "gov.nasa.worldwind.layers.Earth.BMNGOneImage,"
								+ "gov.nasa.worldwind.layers.Earth.BMNGWMSLayer,"
								+ "gov.nasa.worldwind.layers.Earth.LandsatI3WMSLayer,"
								+ "gov.nasa.worldwind.layers.Earth.USGSUrbanAreaOrtho,"
								+ "gov.nasa.worldwind.layers.Earth.MSVirtualEarthLayer,"
								+ "gov.nasa.worldwind.layers.Earth.CountryBoundariesLayer,"   // Careful, this layer can get hidden by the other layers.
								);

		System.setProperty("java.net.useSystemProxies", "true");
		if (Configuration.isMacOS()) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					"World Wind Application");
			System.setProperty("com.apple.mrj.application.growbox.intrudes",
					"false");
			System.setProperty("apple.awt.brushMetalLook", "true");
		} else if (Configuration.isWindowsOS()) {
			// Prevent flashing during window resizing
			System.setProperty("sun.awt.noerasebackground", "true");
		}
	}

	/**
	 * Setup the WorldWindow.
	 */
	protected void setupWWD() {
		// Create the default model as described in the current worldwind
		// properties.
		Model worldModel = (Model) WorldWind
				.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
		getWWD().setModel(worldModel);

		// Deal with rendering exceptions - graphics not good enough
		getWWD().addRenderingExceptionListener(new RenderingExceptionListener() {
			public void exceptionThrown(Throwable t) {
				if (t instanceof WWAbsentRequirementException) {
					String message = "Computer does not meet minimum graphics requirements.\n";
					message += "Please install up-to-date graphics driver and try again.\n";
					message += "Reason: " + t.getMessage() + "\n";
					message += "This program will end when you press OK.";

					JOptionPane.showMessageDialog(MapPanel.this, message,
							"Unable to Start Program",
							JOptionPane.ERROR_MESSAGE);
					System.exit(-1);
				}
			}
		});
		
//		getWWD().addRenderingListener(new RenderingListener() {
//
//			@Override
//			public void stageChanged(RenderingEvent arg0) {
//				System.out.println("MapPanel.renderingListener.stateChanged(): "+ arg0);
//				
//			}
//			
//		});

		// Setup a select listener for the worldmap click-and-go feature
		getWWD().addSelectListener(new ClickAndGoSelectListener(getWWD(),
				WorldMapLayer.class));

		// Put the world window in the center
		add(getWWD(), BorderLayout.CENTER);
	}

	/**
	 * Set up the status bar, which shows latitude, longitude, altitude, etc.
	 */
	protected void setupStatusBar() {
		StatusBar statusBar = new StatusBar() {
			private static final long serialVersionUID = -8334549384792867496L;

			@Override
			protected String makeAngleDescription(String label, Angle angle) {
				String s;
				if (Angle.ANGLE_FORMAT_DMS.equals(getAngleFormat()))
					s = String.format("%s %s", label, angle.toDMSString());
				else
					s = String.format("%s %,.6f\u00B0", label, angle.degrees);
				return s;
			}
			
			/**
			 * Overridden to capture the current altitude.
			 */
			@Override
			protected String makeEyeAltitudeDescription(double metersAltitude){
				//System.out.println("metersAltitude: "+ metersAltitude);
				setCurrentAltitude(metersAltitude);
				return super.makeEyeAltitudeDescription(metersAltitude);
			}
		};
		add(statusBar, BorderLayout.PAGE_END);
		statusBar.setEventSource(getWWD());
	}

	/**
	 * Setup the controllers in the view which allow navigation around the map.
	 */
	protected void setupViewControllers() {
		ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
		@SuppressWarnings("unused")
		ToolTipController toolTipController = new ToolTipController(getWWD(),
				AVKey.DISPLAY_NAME, null);
		@SuppressWarnings("unused")
		HighlightController highlightController = new HighlightController(getWWD(),
				SelectEvent.ROLLOVER);

		insertBeforeCompass(viewControlsLayer);
		getWWD().addSelectListener(new ViewControlsSelectListener(getWWD(),
				viewControlsLayer));
	}

	/**
	 * Convert gradient sky to color sky.
	 */
	protected void setupSky() {
		// Change atmosphere SkyGradientLayer for SkyColorLayer
		LayerList layers = getWWD().getModel().getLayers();
		for (int i = 0; i < layers.size(); i++) {
			if (layers.get(i) instanceof SkyGradientLayer)
				layers.set(i, new SkyColorLayer());
		}
	}

	/**
	 * Add a select listener to toggle ToggleAnnotation objects
	 */
	protected void setupAnnotationToggling() {
		getWWD().addSelectListener(new SelectListener() {
			public void selected(SelectEvent event) {
				if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
					if (event.hasObjects()) {
						Object obj = event.getTopObject();
						if (obj instanceof ToggleAnnotation) {
							ToggleAnnotation annotation = (ToggleAnnotation) obj;
							annotation.toggleText();
						}
					}
				}
			}
		});
	}
	
	
	
	/**
	 * Construct a MapPanel with a flat Earth view.
	 * Equivalent to MapPanel(EarthFlat.class) 
	 */
	public MapPanel() {
		this(EarthFlat.class);
	}

	/**
	 * Construct a new MapPanel using the given type of Globe view.
	 * Possible values for globeTypeClass:
	 * Earth.class, EarthFlat.class, EllipsoidalGlobe.class, FlatGlobe.class
	 * @param globeTypeClass The Class file for a class/interface extending/implementing Globe which determines the type of view of the Earth. 
	 */
	public MapPanel(Class<? extends Globe> globeTypeClass) {
		super(new BorderLayout());
		
		/**
		 * The following setPreferredSize(new Dimension(0,0)) is a work around
		 * to a known JOGL problem when working with resizeable elements, e.g. JScrollPanes.
		 * See http://jogamp.org/jogl/doc/userguide/ and look under the 
		 * "Heavyweight and Lightweight Issues" section.
		 * Without this line, if this panel is put into a JScrollPane, when the scroll pane
		 * size is _reduced_ and the scroll bars appear, the map will not scroll 
		 * properly; simply shifting in space on top of other elements.   The displayed
		 * map is also incorrect, an objects that should be visible on the edges may not 
		 * be visible.   The problem does not appear when the scroll pane size is increased.   With the
		 * following line, the map will always be the same size of the scroll pane's 
		 * viewport and thus the scroll bars will never appear.
		 */
		setPreferredSize(new Dimension(0,0)); 
		initconfig(globeTypeClass);
		
		
		setupAnnotationToggling();
	}
	

	/**
	 * Starts the MapPanel by creating the WWJ Map and showing it.
	 * 
	 */
	public void start() {
		setupWWD();
		setupStatusBar();
		setupViewControllers();
		setupSky();
		//setupAnnotationToggling();

		// Center the application on the screen.
		WWUtil.alignComponent(null, this, AVKey.CENTER);
		

	}

	/**
	 * Insert layer just beneath the Compass layer.
	 * 
	 * @param layer
	 *            - layer to insert
	 */
	protected void insertBeforeCompass(Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = getWWD().getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	/**
	 * Add an action to be performed upon a right click on the map. Attempting
	 * to add the same action object more than once will have no effect. A
	 * previously removed action can be re-added.  
	 * The Point object that is given to the IRightClickAction has been modified 
	 * so that its getAltitude() method returns the eye point altitude at the time
	 * of the right click.   getElevation() still returns the vertical position of 
	 * the ground at that location.
	 * 
	 * @param rightClick
	 *            - desired action
	 */
	public void addRightClickAction(final IRightClickAction rightClick) {
		if (rightClickActions.containsKey(rightClick)) {
			return; // disallow multiple registrations of same listener.
		} else {
			// Handle right button clicks
			MouseListener listener = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 3) {
						Position p = getWWD().getCurrentPosition();
						// HACK required  to enable Position.getAltitude() to actually return the current eye point altitude
						// originally, Position.getAltitude() and Position.getElevation() both return the elevation.
						rightClick.apply(new PositionWithAltitude(p, getCurrentAltitude()));
					}
				}
			};
			getWWD().addMouseListener(listener);
			rightClickActions.put(rightClick, listener);
		}
	}

	/**
	 * Remove an action that is being performed upon a right click on the map.
	 * No-op if the given IRightClickAction was not previously added. The action
	 * object can be re-added later if desired.
	 * 
	 * @param rightClickAction
	 *            - a IRightClickAction that was previously added by
	 *            addRightClickAction
	 */
	public void removeRightClickAction(IRightClickAction rightClickAction) {
		MouseListener listener = rightClickActions.remove(rightClickAction);
		if (null != listener) {
			getWWD().removeMouseListener(listener);
		}
	}

	/**
	 * Set the position of the map to the indicated position. If "fly" is true,
	 * the map will animate from the current position to the indicated position,
	 * otherwise it will jump there immediately.
	 * 
	 * @param pos
	 *            - desired destination position
	 * @param fly
	 *            - fly if true, jump if false
	 */
	public void setPosition(Position pos, boolean fly) {
		View mapView = getWWD().getView();
		if (mapView.isAnimating()) {
			mapView.stopAnimations();
		}

		if (fly) {
			mapView.goTo(pos, pos.getAltitude());
		} else {
			mapView.setEyePosition(pos);
			getWWD().redraw();
		}
	}

	/**
	 * Add a layer of annotations to the map.
	 * Layer is added below the compass layer.
	 * 
	 * @param layer layer to add to the map
	 */
	public void addLayer(Layer layer) {
		insertBeforeCompass(layer);
	}

	/**
	 * Remove a layer from the map
	 * 
	 * @param layer layer to remove from the map
	 */
	public void removeLayer(Layer layer) {
		LayerList layers = getWWD().getModel().getLayers();
		layers.remove(layer);
	}
	
	
	/**
	 * Insert the given Layer before or after the first occurrence of the given layer type.
	 * @param layer  The layer to insert
	 * @param layerType  The type of layer to insert before or after
	 * @param insertBefore Boolean that if true, will all insertion before the given type and if false, will insert after the type has been encountered. 
	 */
	public void insertAtLayer(Layer layer, Class<?> layerType, boolean insertBefore){
		LayerList layers = getWWD().getModel().getLayers();
		int offset = insertBefore ? 0 : 1;
		for(Layer aLayer: layers) {
			if (aLayer.getClass().isInstance(layerType) ) {
				layers.add(layers.indexOf(aLayer)+offset, layer);
				break;
			}
		}	
	}
	
	/**
	 * Insert the given layer before or after the first occurrence of the given layer object 
	 * @param layer	The layer to insert
	 * @param targetLayer	The layer before or after which the given layer will be inserted 
	 * @param insertBefore 	Boolean that if true, will all insertion before the given type and if false, will insert after the type has been encountered.
	 */
	public void insertAtLayer(Layer layer, Layer targetLayer, boolean insertBefore){
		LayerList layers = getWWD().getModel().getLayers();
		int offset = insertBefore ? 0 : 1;
		layers.add(layers.indexOf(targetLayer)+offset, layer);

	}

	/**
	 * @return the internal WorldWindowGLCanvas object
	 */
	protected WorldWindowGLCanvas getWWD() {
		return _wwd;
	}

	/**
	 * Accessor for the current altitude (eye point)
	 * @return the currentAltitude
	 */
	protected double getCurrentAltitude() {
		return currentAltitude;
	}

	/**
	 * Accessor for the current altitude (eye point)
	 * @param currentAltitude the currentAltitude to set
	 */
	protected void setCurrentAltitude(double currentAltitude) {
		this.currentAltitude = currentAltitude;
	}




	
}

/**
 * Modified Position class that can report an altitude that differs from its elevation.
 * @author swong
 *
 */
class PositionWithAltitude extends Position {
	
	/**
	 * The eye point vertical position.
	 */
	private double altitude;
	
	/**
	 * Constructor for the class
	 * @param p  A Position object to supply the latitude, longitude and elevation.
	 * @param alt The eye point vertical position to save.
	 */
	public PositionWithAltitude(Position p, double alt) {
		super(p.getLatitude(), p.getLongitude(), p.getElevation());
		altitude = alt;
	}
	
	/**
	 * Returns the eye point altitude, not the elevation.
	 * @return the eye point altitude at the time of the creation of this Position.
	 */
	@Override
	public double getAltitude(){
		return altitude;
	}
	

		
}