package listFW;

/**
 * Represents non-empty lists.
 * Contains two pieces of data:<ul>
 <li>first: an Object representing the first data element
 <li>rest: an IList object representing the rest of this non-emptylist.</ul>
 * When a class contains other objects that are isomorphic to itself,
 * this class is called a composite.</p>
 * Provides concrete code for <ul>
 <li>a constructor to initialize this NEList to a given first and rest,
 <li>the getFirst() method for accessing the first data element,
 <li>the getRest() method for accesssing the rest of the list,
 <li>the execute method: simply calls the nonEmptyCase method of the IListAlgo
 parameter, passing to this method itself as the host and the given input
 Object as the input.</ul>
 * @author D. X. Nguyen
 * @author S. B. Wong
 */
public class NEList implements IList {
    /**
     * The first data element.
     */
    private Object _first;

    /**
     * The rest or "tail" of this NEList.
     * Data Invariant: _rest != null;
     */
    private IList _rest;    
    /**
     * Initializes this NEList to a given first and a given rest.
     * @param f the first element of this NEList.
     * @param r != null, the rest of this NEList.
     */
    public NEList(Object f, IList r) {
        _first = f;
        _rest = r;
    }

    /**
     * Returns the first data element of this NEList.
     * This method is marked as final to prevent all subclasses from overriding it.
     * Finalizing a method also allows the compiler to generate more efficient calling code.
     */
    public final Object getFirst() {
        return _first;
    }
    
    /**
     * Returns the first data element of this NEList.
     * This method is marked as final to prevent all subclasses from overriding it.
     * Finalizing a method also allows the compiler to generate more efficient calling code.
     */
    public final IList getRest() {
        return _rest;
    }    
    
    /**
     * Calls the nonEmptyCase method of the IListAlgo parameter, passing to this
     * method itself as the host parameter and the given input as the input
     * parameter.
     * This method is marked as final to prevent all subclasses from
     * overriding it.  Finalizing a method also allows the compiler to generate
     * more efficient calling code.
     */
    public final Object execute(IListAlgo algo, Object... inp) {
        return algo.nonEmptyCase(this, inp);
    }

    public String toString() {
        return (String)ToStringAlgo.Singleton.nonEmptyCase(this);
    }
}

