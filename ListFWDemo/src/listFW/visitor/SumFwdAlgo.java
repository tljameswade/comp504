package listFW.visitor;

import listFW.IListAlgo;
import listFW.MTList;
import listFW.NEList;

/**
 *  Returns the sum of a list of integers using forward accumulation.
 *  The input parameter is not used ("nu").
 * @author swong
 *
 */
public class SumFwdAlgo implements IListAlgo {

	@Override
	/**
	 * The base case value is zero,
	 */
	public Object emptyCase(MTList host, Object... nu) {
		return 0;
	}

	@Override
	/**
	 * Pass the first value to the rest of the list, using a helper visitor, to be accumulated.
	 */
	public Object nonEmptyCase(NEList host, Object... nu) {

		// The accumulated value is just the first value.
		return host.getRest().execute(helper, host.getFirst());
	}

	/**
	 * Recursive helper that uses an accumulator as its input parameter.
	 */
	private IListAlgo helper  = new IListAlgo() {

		@Override
		/**
		 * The result is the current accumulated value.
		 */
		public Object emptyCase(MTList host, Object... accs) {
			return accs[0];
		}

		@Override
		/**
		 * Add first to the incoming accumulated value and pass the new value to the rest of the list, recursively.
		 */
		public Object nonEmptyCase(NEList host, Object... accs) {
			return host.getRest().execute(this, (int)accs[0] + (int)host.getFirst());
		}
		
	};
}
