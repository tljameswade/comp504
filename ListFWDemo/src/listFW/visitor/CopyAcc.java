package listFW.visitor;

import listFW.*;
/**
 * Accumulates the values into a list, starting with an empty list.
 * @author swong
 *
 */
public class CopyAcc extends AAccumulator {

	public CopyAcc() {
		super(MTList.Singleton);
	}


	@Override
	/**
	 * Add the given value to the front of the stored list.
	 */
	public void accumulate(Object x) {
		value = new NEList(x, (IList)value);

	}

}
