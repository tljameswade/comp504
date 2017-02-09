package listFW.visitor;

/**
 * Accumulates (saves) the last negative number given to it.
 * Base case is Double.NaN (Not a Number) 
 * @author swong
 *
 */
public class LastNegAcc extends AAccumulator {

	public LastNegAcc() {
		super(Double.NaN);
	}

	@Override
	public void accumulate(Object x) {
		if(0 > (int)x) {
			value = x;
		}

	}

}
