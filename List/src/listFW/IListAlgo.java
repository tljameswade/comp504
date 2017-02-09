package listFW;

/**
 * Represents an abstract algorithm to be executed by a host IList.
 * Plays the role of a "visitor" in the design pattern language.
 <p>
 * In non OO programming paradigms, an algorithm on lists is expressed as a
 * function or procedure that takes in a list as parameter. 
 * In our OO formulation, an alogrithm on lists is an interface that has two
 * methods, each takes in a list as a parameter: <ul>
 <li>One method, called emptyCase, operates specifically on an empty list
    (MTList) and is to be called exclusively by an MTList.
 <li>The other method, called nonEmptyCase, operates specifically on a
    non-empty list (NEList) and is to be called exclusively by an NEList.
 </ul>
 * When we want to perform an alogrithm (IListAlgo) on a list (IList), we asks
 * the IList to 'execute' the algorithm with a given input.  An MTList knows
 * instrinsically that is empty and calls the emptyCase method of the IListAlgo.
 * Analogously, a non-empty list (NEList) will correctly call the nonEmptyCase
 * method of the IListAlgo.
 <p>
 * One need not (and should not) check for the type of a list before applying
 * an algorithm to it.  THERE IS NO if!
 </p>
 * @author Dung X. Nguyen
 * @author Stephen B. Wong
 * @since Copyright 2005 - DXN, SBW All rights reserved
 */
public interface IListAlgo {
    /**
     * Operates on <code>MTList</code>, the empty list.  Since
     * <code>IEmptyList</code> has only one method, <code>execute()</code>,
     * there is not much the host list can do here.  So the code for the empty
     * case usually involves:<ul>
     <li><code>inp</code>, and perhaps
     <li><code>host.execute(..., ...)</code>.</ul>
     * @param host the <code>MTList</code> that is executing this algorithm.
     * @param inp a variable number of input parameters that can be used for any purpose.
     * @return result from calling this method.  The type of the result is
     * problem-specific and may be null.
     */
    public abstract Object emptyCase(MTList host, Object... inp);

    /**
     * Operates on <code>NEList</code>, a non-empty list. The host list now has
     * a first and a rest.  So the code here usually involves what
     * <code>host.getFirst()</code> and <code>host.getRest()</code> can do to
     * accomplish the task at hand.<ul>
       <li><code>host.getFirst()</code> is simply a data <code>Object</code>
       that the host list holds.  It is problem-specific, and thus what it can
       do depends on the problem the current algorithm is trying to solve.
       <li><code>host.getRest()</code> in contrast is an <code>IList</code>!
       What can an <code>IList</code> do? <code>execute</code> an alogrithm
       <code>IListAlgo</code> with some input. What <code>IListAlgo</code> can
       that be? The current algorithm that is being executed is as good a
       candidate as any other <code>IListAlgo</code>. In Java, we use the key
       word <code><b>this</b></code> to reference the current receiver of the
       method call.  Having the rest of host (recursively) execute the current
       algorithm is expressed in Java as:
       <code> host.getRest().execute(<b>this</b>, ...)</code>.</ul><p>
     * To summarize, the code for the non-empty case usually involves:<ul>
       <li><code>host.getFirst()</code>, and the <i>recursive</i> call
       <li><code>host.getRest().execute(<b>this</b>, <i>something involving</i> inp)</code>.</ul>
     * @param host the <code>NEList</code> that is executing this algorithm
     * @param inp a variable number of input parameters that can be used for any purpose.
     * @return result from calling this method.  The type of the result is
     * problem-specific and may be null.
     */
    public abstract Object nonEmptyCase(NEList host, Object... inp);
}

