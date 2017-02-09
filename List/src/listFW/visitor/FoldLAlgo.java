package listFW.visitor;

import listFW.IListAlgo;
import listFW.MTList;
import listFW.NEList;

/**
 * Performs the fold-left (forward accumulation) algorithm using the given IAccumulator
 * The general forward accumulation algorithm would not take an accumulator as a top-level
 * input parameter, instead the accumulator would be hidden inside the algorithm.   
 * But since the fold process, makes the folding process invariant and the accumulator the
 * variant piece, the accumulator is thus necessarily an input parameter here.
 * @author swong
 *
 */
public class FoldLAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... acc) {
		return acc[0];
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... acc) {
		// Typically, the base case value of the accumulator is passed to the 
		// helper here and the input parameter above is ignored. 
		// But since the IAccumulator is constructed already holding its 
		// base case value and given as the above input parameter, we 
		// simply do the first accumulation and then pass it along. 
		((IAccumulator)acc[0]).accumulate(host.getFirst());
		return host.getRest().execute(helper, acc[0]);
	}
	
	/**
	 * Helper visitor 
	 * Arguable, this visitor above is unnecessary, but idea here was 
	 * to stick to the general architecture of any forward accumulation 
	 * algorithm, which foldl represents.
	 */
	private IListAlgo helper = new IListAlgo() {

		@Override
		public Object emptyCase(MTList host, Object... acc) {
			return acc[0];
		}

		@Override
		public Object nonEmptyCase(NEList host, Object... acc) {
			// Process the incoming accumulator
			((IAccumulator)acc[0]).accumulate(host.getFirst());
			// Pass the processed accumulator along.
			return host.getRest().execute(this, acc);   // Tail-call -- can be optimized into a fast loop!
		}
		
	};

}
