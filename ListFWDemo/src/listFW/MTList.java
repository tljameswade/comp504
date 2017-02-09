package listFW;

/**
 * Represents the unique empty list using the singleton pattern.
 * Provides concrete code for the execute method: simply calls the emptyCase
 * method of the IListAlgo parameter, passing to this method itself as the host
 * and the given input Object as the input.
 * @author D. X. Nguyen
 * @author S. B. Wong
 */
public class MTList implements IList {    
    /**
     * Singleton Pattern
     */
    public final static MTList Singleton = new MTList();
    private MTList() {
    }
    
    /**
     * Calls the empty case of the algorithm algo, passing to it itself as
     * the host parameter and the given input inp as the input parameter.
     * This method is marked as final to prevent all subclasses from
     * overriding it.  Finalizing a method also allows the compiler to generate
     * more efficient calling code.
     */
    public final Object execute(IListAlgo algo, Object... inp) {
        return algo.emptyCase(this, inp);
    }
    
    public String toString() {
        return (String)ToStringAlgo.Singleton.emptyCase(this);
    }
}

