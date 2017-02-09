package map;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.GlobeAnnotation;

/**
 * Map annotations that can be "toggled" with a mouse click.<br/>
 * <b>WARNING!  NOT ALL FIELDS ARE SERIALIZED!</b><br/>
 * Serialized fields are ONLY: _selectedText, _unselectedText, position, minActiveAltitude, maxActiveAltitude <br/>
 * Currently, the attributes are NOT serialized.
 * 
 * @author Scott Rixner, Stephen Wong
 */
public class ToggleAnnotation extends DummyGlobeAnnotation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1464937422963144193L;

	/**
	 * Default selected background color is a half-transparent green
	 */
	public static final Color DEFAULT_SELECTED_BACKGROUND_COLOR = new Color(0f, .5f, 0f, .5f);  

//	/**
//	 * Annotation attributes to be used by all ToggleAnnotations.
//	 */
//	private static final AnnotationAttributes DEFAULTATTRIBUTES = new AnnotationAttributes() {
//		{
//			setCornerRadius(10);
//			setInsets(new Insets(8, 8, 8, 8));
//			setBackgroundColor(new Color(0f, 0f, 0f, .5f));
//			setTextColor(Color.WHITE);
//			setDrawOffset(new Point(25, 25));
//			setDistanceMinScale(.5);
//			setDistanceMaxScale(2);
//			setDistanceMinOpacity(.5);
//			setLeaderGapWidth(14);
//			setDrawOffset(new Point(20, 40));
//		}
//		public String toString() {
//			return "DEFAULTATTRIBUTES"; // for testing purposes
//		}
//	};

	/**
	 * Text to display when the annotation is not selected.
	 */
	private String  _unselectedText = "";

	/**
	 * Text to display when the annotation is selected.
	 */
	private String  _selectedText = "";

	/**
	 * Indicates whether this annotation is currently selected.
	 */
	private boolean _selected = false;
	
	/**
	 * The background color that is used when the annotation is selected.
	 */
	private Color selectedBackgroundColor = DEFAULT_SELECTED_BACKGROUND_COLOR;

	/**
	 * Temporary storage of the original unselected background color when the annotation is selected.
	 */
	private Color unselectedBackgroundColor; 
	/**
	 * Build a new toggling annotation.
	 * 
	 * @param unselectedText  - text to display when annotation is not selected
	 * @param selectedText    - text to display when annotation is selected
	 * @param pos             - position of annotation
	 */
	public ToggleAnnotation(String unselectedText, String selectedText, Position pos) {
		this(unselectedText, selectedText, pos, new DefaultAnnotationAttributes());

	}
	
	/**
	 * Build a new toggling annotation with custom attributes
	 * @param unselectedText  - text to display when annotation is not selected
	 * @param selectedText    - text to display when annotation is selected
	 * @param pos             - position of annotation
	 * @param attr            - display attributes for the annotation.     
	 */
	public ToggleAnnotation(String unselectedText, String selectedText, Position pos, AnnotationAttributes attr) {
		super(unselectedText, pos, attr);		
		_unselectedText = unselectedText;
		_selectedText = selectedText;
		unselectedBackgroundColor = attr.getBackgroundColor();
	}

	/**
	 * Copy constructor
	 * @param ta ToggleAnnotation to copy
	 */
	public ToggleAnnotation(ToggleAnnotation ta){
		this(ta._unselectedText, ta._selectedText, ta.getPosition(), ta.getAttributes());
		this.setMinActiveAltitude(ta.getMinActiveAltitude());
		this.setMaxActiveAltitude(ta.getMaxActiveAltitude());
	}
	
	/**
	 * Converts a standard GlobeAnnotation to a ToggleAnnotation where the selected and unselected texts are the same.
	 * @param annotation The GlobeAnnotation to convert.
	 */
	public ToggleAnnotation(GlobeAnnotation annotation){
		this(annotation.getText(), annotation.getText(), annotation.getPosition(), annotation.getAttributes());
		this.setMinActiveAltitude(annotation.getMinActiveAltitude());
		this.setMaxActiveAltitude(annotation.getMaxActiveAltitude());		
	}
	
	/**
	 * Sets the background color to use when the annotation is selected.  To set the unselected background color, 
	 * use the inherited getAttributes().setBackgroundColor(Color c). The annotation must be unselected to set the 
	 * unselected background color.   The selected background color can be set at any time. 
	 * @param c The background color to use when selected.
	 */
	public void setSelectedBackgroundColor(Color c) {
		this.selectedBackgroundColor = c;
	}

	
	/**
	 * Sets the annotation to either the selected or non-selected state.
	 * Will call the protected setSelectedTrue or setSelectedFalse methods 
	 * @param selected Whether or not the annotation will be selected.
	 */
	public void setSelected(boolean selected) {
		_selected = selected;
		if (selected)
		{
			selectedTrueAction();
		}
		else
		{
			selectedFalseAction();
		}
	}
	
	/**
	 * Protected method that performs action when annotation is set to selected.  
	 * By default, saves the current background color as next unselected background color and sets text to 
	 * _selectedText.
	 * When overriding this method, be sure to call super.selectedTrueAction() to get the default behavior.
	 */
	protected void selectedTrueAction() {
		unselectedBackgroundColor = this.getAttributes().getBackgroundColor();
		setText(_selectedText);
        getAttributes().setBackgroundColor(selectedBackgroundColor);	
	}
	
	/**
	 * Protected method that performs action when annotation is set to unselected.
	 * By default, sets background color to unselectedBackgroundColor, and sets text to _unselectedText. 
	 * When overriding this method, be sure to call super.selectedFalseAction() to get the default behavior.
	 */
	protected void selectedFalseAction() {
		setText(_unselectedText);
        getAttributes().setBackgroundColor(unselectedBackgroundColor);
	}
	
	/**
	 * Accessor for the current selected state
	 * @return True is currently selected, false otherwise.
	 */
	public boolean isSelected(){
		return this._selected;
	}
	/**
	 * Toggle between selected and unselected.
	 */
	public void toggleText()
	{
		setSelected(!_selected);
	}	
	
	/**
	 * Custom serialization process to serialize non-serializable superclass
	 * @param out Output stream for serialization
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject(); //ToggleAnnotation-defined fields can use default serialization process
		out.writeObject(new DefaultAnnotationAttributes(this.getAttributes())); // convert the attributes to something serializable first
		out.writeUTF(this.getText());  // send out the superclass's annotation string
		Position p = this.getPosition(); // get the Position which is non-serializable 
		out.writeDouble(p.getLatitude().getDegrees()); // send out latitude
		out.writeDouble(p.getLongitude().getDegrees()); // send out longitude
		out.writeDouble(p.getElevation()); // send out elevation
		out.writeDouble(this.getMinActiveAltitude()); // send out min active altitude
		out.writeDouble(this.getMaxActiveAltitude()); // send out max active altitude
	}

	/**
	 * Custom deserialization process to deserialize non-serializable superclass.
	 * @param in Input stream for deserialization
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();//ToggleAnnotation-defined fields can use default deserialization process
		this.setAttributes((AnnotationAttributes) in.readObject());
		this.setText(in.readUTF()); // read in superclass's annotation string
		double lat = in.readDouble(); // read in latitude
		double lon = in.readDouble(); // read in longitude
		double elev = in.readDouble(); // read in elevation
		this.setPosition(Position.fromDegrees(lat, lon, elev));	// set Position	
		this.setMinActiveAltitude(in.readDouble()); // read in min active altitude
		this.setMaxActiveAltitude(in.readDouble()); // read in max active altitude
	}
	
	/**
	 * Overridden toString to show values
	 */
	@Override
	public String toString() {
		return "\""+this.getText()+"\"/\""+_selectedText+"\"@("+this.getPosition()+", "+this.getAttributes();
	}
	
	private UUID uuid = UUID.randomUUID();
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof ToggleAnnotation)) return false;
		return uuid.equals(((ToggleAnnotation)other).uuid);	
	}
	
	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}

/**
 * Dummy non-serializable superclass that provides a no-arg constructor that is needed by the serialization 
 * process of a serializable subclass in order to initialize a non-serializable superclass.
 * @author swong
 *
 */
class DummyGlobeAnnotation extends GlobeAnnotation {
	
	/**
	 * Constructor used by ToggleAnnotation
	 * @param unselectedText  - text to display when annotation is not selected
	 * @param pos             - position of annotation
	 * @param attr            - annotation attributes
	 */
	public DummyGlobeAnnotation(String unselectedText, Position pos, AnnotationAttributes attr) {
		super(unselectedText, pos, attr);
	}
	
	/**
	 * No-arg constructor used during serialization.  Assume no useful values are set here.
	 */
	protected DummyGlobeAnnotation(){
		super("", Position.fromDegrees(0.0, 0.0), new DefaultAnnotationAttributes());
	}	
}

