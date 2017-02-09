package map;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.render.Annotation;
import gov.nasa.worldwind.render.GlobeAnnotation;

/**
 * Annotation layer that can be used to add annotations to the map. <br/>
 * <b>WARNING!  NOT ALL FIELDS ARE SERIALIZED!</b><br/>
 * Only sub-classes of GlobeAnnotations (incl. ToggleAnnotation) will be serialized!   
 * All annotations will be converted to ToggleAnnotations during serialization as WWJ-supplied 
 * annotations are not Serializable.
 * @author Scott Rixner, Stephen Wong
 */
public class MapLayer extends AnnotationLayer implements Serializable {
	
	/**
	 * Unique class id
	 */
	private static final long serialVersionUID = 9124320022001821211L;

	/**
	 * Create a new annotation layer for a map.
	 */
	public MapLayer()
	{
		super();
	}
	
	/**
	 * Factory to build a toggling annotation.
	 * 
	 * @param unselectedText - text when annotation is not selected
	 * @param selectedText   - text when annotation is selected
	 * @param pos            - position of annotation
	 * @return               - annotation that can be added to the layer
	 */
	private ToggleAnnotation makeAnnotation(String unselectedText, String selectedText, Position pos)
	{
		ToggleAnnotation ta =  new ToggleAnnotation(unselectedText, selectedText, Position.fromDegrees(pos.getLatitude().degrees, pos.getLongitude().degrees, 0));
		return ta;
	}
	

	/**
	 * Convenience method that adds an annotation that is viewable from any altitude.   This method is equivalent to 
	 * directly calling addAnnotation() (inherited from AnnotationLayer) with an instance of ToggleAnnotation.
	 * 
	 * @param unselectedText - text when annotation is not selected
	 * @param selectedText   - text when annotation is selected
	 * @param pos            - position of annotation
	 */
	public void addToggleAnnotation(String unselectedText, String selectedText, Position pos)
	{
		addAnnotation(makeAnnotation(unselectedText, selectedText, pos));
	}

	/**
	 * Convenience method that adds an annotation that is viewable between a min and max altitude.  This method is equivalent to 
	 * directly calling addAnnotation() (inherited from AnnotationLayer) with an instance of ToggleAnnotation where setMinActiveAltitude()
	 * and setMaxActiveAltitude() have already been called.
	 * 
	 * @param unselectedText - text when annotation is not selected
	 * @param selectedText   - text when annotation is selected
	 * @param pos            - position of annotation
	 * @param minAltitude    - minimum viewable altitude
	 * @param maxAltitude    - maximum viewable altitude
	 */
	public void addToggleAnnotation(String unselectedText, String selectedText, Position pos, double minAltitude, double maxAltitude)
	{
		ToggleAnnotation annotation = makeAnnotation(unselectedText, selectedText, pos);
		annotation.setMinActiveAltitude(minAltitude);
		annotation.setMaxActiveAltitude(maxAltitude);
		addAnnotation(annotation);
	}
	
	
	/**
	 * Custom serialization process to serialize non-serializable superclass
	 * @param out Output stream for serialization
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject(); //MapLayer-defined fields can use default serialization process

		Vector<ToggleAnnotation> annotations = new Vector<ToggleAnnotation>();
 		for(Annotation annotation: this.getAnnotations()) {
			if(annotation instanceof ToggleAnnotation){  // must check this before GlobeAnnotation
				annotations.add((ToggleAnnotation)annotation);
			}
			else if(annotation instanceof GlobeAnnotation) {
				annotations.add(new ToggleAnnotation((GlobeAnnotation) annotation));
			}
			// ignore all other types of annotations
		}
 		out.writeObject(annotations);
	}

	/**
	 * Custom deserialization process to deserialize non-serializable superclass.
	 * @param in Input stream for deserialization
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();//MapLayer-defined fields can use default deserialization process
		@SuppressWarnings("unchecked")
		Vector<ToggleAnnotation> annotations = (Vector<ToggleAnnotation>) in.readObject();
		for(ToggleAnnotation ta: annotations) {
			addAnnotation(ta);
		}		
	}
	
	private UUID uuid = UUID.randomUUID();
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof MapLayer)) return false;
		return uuid.equals(((MapLayer)other).uuid);	
	}
	
	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}
