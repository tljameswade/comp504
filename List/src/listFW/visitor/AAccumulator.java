package listFW.visitor;

/**
 * An IAccumulator that holds an Object as its internal value
 * and who's string representation is the string representation of 
 * that value.
 * @author swong
 *
 */
public abstract class AAccumulator implements IAccumulator {
	/**
	 * The stored value
	 */
	protected Object value;
	
	/**
	 * Constructor for the class
	 * @param value The initial (base case) value 
	 */
	public AAccumulator(Object value) {
		this.value = value;
	}
	
	
	public String toString() {
		return ""+value;
	}
}
