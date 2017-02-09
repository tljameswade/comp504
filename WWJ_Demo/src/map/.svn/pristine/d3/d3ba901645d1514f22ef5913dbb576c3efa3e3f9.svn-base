package map;

import gov.nasa.worldwind.render.AnnotationAttributes;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

/**
 * Serializable AnnotationAttributes that also defines default values for the attributes.
 * See the AnnotationAttributes documentation for descriptions of the static field values.
 * 
 * @author swong
 *
 */
public class DefaultAnnotationAttributes extends AnnotationAttributes implements Serializable {
	
	public static final int CORNER_RADIUS = 10;
	public static final Insets INSETS = new Insets(8, 8, 8, 8);
	public static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, .5f);
	public static final Color TEXT_COLOR = Color.WHITE;
	public static final double DISTANCE_MIN_SCALE = 0.5;
	public static final double DISTANCE_MAX_SCALE = 2.0;
	public static final double DISTANCE_MIN_OPACITY = 0.5;
	public static final int LEADER_GAP_WIDTH =  14;
	public static final Point DRAW_OFFSET = new Point(20, 40);
	
	/**
	 * Unique class id
	 */
	private static final long serialVersionUID = 872276645232083561L;

	/**
	 * Constructor that sets default values to the attributes
	 */
	public DefaultAnnotationAttributes() {
		super();
		setCornerRadius(CORNER_RADIUS);
		setInsets(INSETS);
		setBackgroundColor(BACKGROUND_COLOR);
		setTextColor(TEXT_COLOR);
		setDistanceMinScale(DISTANCE_MIN_SCALE);
		setDistanceMaxScale(DISTANCE_MAX_SCALE);
		setDistanceMinOpacity(DISTANCE_MIN_OPACITY);
		setLeaderGapWidth(LEADER_GAP_WIDTH);
		setDrawOffset(DRAW_OFFSET);
	}
	
	/**
	 * Copy constructor.   Useful for creating serializable versions of attributes.
	 * @param attr Attribute to copy
	 */
	public DefaultAnnotationAttributes(AnnotationAttributes attr){
		super();
		setCornerRadius(attr.getCornerRadius());
		setInsets(attr.getInsets());
		setBackgroundColor(attr.getBackgroundColor());
		setTextColor(attr.getTextColor());
		setDrawOffset(attr.getDrawOffset());
		setDistanceMinScale(attr.getDistanceMinScale());
		setDistanceMaxScale(attr.getDistanceMaxScale());
		setDistanceMinOpacity(attr.getDistanceMinOpacity());
		setLeaderGapWidth(attr.getLeaderGapWidth());	
	}
	
	/**
	 * Custom serialization process to serialize non-serializable superclass
	 * @param out Output stream for serialization
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject(); //DefaultAnnotationAttributes-defined fields can use default serialization process
		out.writeInt(getCornerRadius());		
		out.writeObject(getInsets());
		out.writeObject(getBackgroundColor());
		out.writeObject(getTextColor());
		out.writeObject(getDrawOffset());
		out.writeDouble(getDistanceMinScale());
		out.writeDouble(getDistanceMaxScale());
		out.writeDouble(getDistanceMinOpacity());
		out.writeInt(getLeaderGapWidth());
	}

	/**
	 * Custom deserialization process to deserialize non-serializable superclass.
	 * @param in Input stream for deserialization
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();//DefaultAnnotationAttributes-defined fields can use default deserialization process
		setCornerRadius(in.readInt());
		setInsets((Insets)in.readObject());
		setBackgroundColor((Color)in.readObject());
		setTextColor((Color)in.readObject());
		setDrawOffset((Point) in.readObject());
		setDistanceMinScale(in.readDouble());
		setDistanceMaxScale(in.readDouble());
		setDistanceMinOpacity(in.readDouble());
		setLeaderGapWidth(in.readInt());
	}
	
	private UUID uuid = UUID.randomUUID();
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof DefaultAnnotationAttributes)) return false;
		return uuid.equals(((DefaultAnnotationAttributes)other).uuid);	
	}
	
	@Override
	public int hashCode() {
		return uuid.hashCode();
	}		
}

