package listFW.visitor;

import listFW.IListAlgo;
import listFW.MTList;
import listFW.NEList;

/**
 * Performs the fold-right (reverse accumulation) algorithm using the given IAccumulator.
 * The general reverse accumulation algorithm would not take an accumulator as a top-level
 * input parameter, instead the accumulator would be hidden inside the algorithm.   
 * But since the fold process, makes the folding process invariant and the accumulator the
 * variant piece, the accumulator is thus necessarily an input parameter here.
 * @author swong
 *
 */
public class FoldRAlgo implements IListAlgo {

	@Override
	public Object emptyCase(MTList host, Object... acc) {
		// Typically, for a general reverse accumulation algorithm, the base case value is simply returned here.
		return acc[0];
	}

	@Override
	public Object nonEmptyCase(NEList host, Object... acc) {
		// Don't process the incoming accumulator, simply pass it along.
		IAccumulator returnAcc = (IAccumulator) host.getRest().execute(this, acc);
		//  Process the returned accumulator
		returnAcc.accumulate(host.getFirst());
		return returnAcc;
	}

}
