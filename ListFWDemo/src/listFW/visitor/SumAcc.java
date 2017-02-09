package listFW.visitor;

/**
 * Accumulates the sum of the values, starting with a total of 1.
 * @author swong
 *
 */
public class SumAcc extends AAccumulator {

	public SumAcc() {
		super(0);
	}

	@Override
	/**
	 * Add the given value to the stored value.
	 */
	public void accumulate(Object x) {
		value = (int)value + (int)x;
	}

}
