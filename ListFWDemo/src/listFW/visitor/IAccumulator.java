package listFW.visitor;

/**
 * An accumulator 
 * @author swong
 *
 */
public interface IAccumulator {
	
	/**
	 * Accumulates the given value into the internally stored total
	 * @param x
	 */
	void accumulate(Object x);
}