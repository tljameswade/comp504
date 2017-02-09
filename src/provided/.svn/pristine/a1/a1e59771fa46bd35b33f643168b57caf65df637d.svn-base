package provided.mixedData;

import java.io.Serializable;
import java.util.UUID;
/**
 * A key for an IMixedDataDictionary
 * @author swong
 *
 * @param <T>   The type of value to which this key will refer.
 */
public class MixedDataKey<T> implements Serializable {
	
	private static final long serialVersionUID = -954450371358260921L;
	
	/**
	 * Unique identifier for key
	 */
	private UUID id;
	
	/**
	 * Description of key
	 */
	private String desc;
	
	
	/**
	 * Type of associated value for this key 
	 */
	private Class<T> type;

	/**
	 * Only the combinaton of id+desc+type must be unique to uniquely reference any value.
	 * @param id   Unique id common to a *set* of keys -- all keys belonging to an isolated sub-system using the IMixedDataDictionary would generally use the same id value.   This prevents name (description) clashes between sub-systems sharing a common dictionary.  
	 * @param desc A String description/name of the key. 
	 * @param type  A Class object representing the type of the value that this key references.
	 */
	public MixedDataKey(UUID id, String desc, Class<T> type) {
		this.id = id;
		this.desc = desc;
		this.type = type;
	}

	
	/**
	 * Overridden equals() method.  Requires that the 
	 * @return true if id, desc, and type are ALL be equal.
	 */
	public boolean equals(Object other) {
		if(other instanceof MixedDataKey<?>) {
			MixedDataKey<?> o = (MixedDataKey<?>) other;
			return this.id.equals(o.id) &&  this.desc.equals(o.desc) && this.type.equals(o.type);
		}
		return false;
	}
	
	/**
	 * Overridden hashCode() method.   JAva requires that if two objects are equal, 
	 * their hashCodes must also be equal.
	 * @return The sum of the hashCodes of id+desc+type.
	 */
	public int hashCode() {
		return id.hashCode() + desc.hashCode() + type.hashCode();
	}
}
