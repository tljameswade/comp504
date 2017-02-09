package listFW;

/**
 * Represents the abstract list structure with a hook method to call on the extrinsic 
 * behaviors represented as visitors.
 * @author D. X. Nguyen
 * @author S. B. Wong
 */
public interface IList {
 /**
  * Relegates to subclasses to call algo to perform the appropriate task with the given 
  * input and returns an Object representing the output of the computation.
  * @param algo an extrinsic operation (algorithm) on this IList.
  * @param inp a variable number of input parameters needed by algo to perform the computation.
  * @return an Object representing the output of the computation.
  */
 public abstract Object execute(IListAlgo algo, Object... inp);
}

