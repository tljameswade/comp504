package listFW;

/**
 * Computes a String reprsentation of IList showing  a left parenthesis followed
 * by elements of the IList separated by commas, ending with with a right parenthesis.
 */
public class ToStringAlgo implements IListAlgo {
    public static final ToStringAlgo Singleton = new ToStringAlgo();
    private ToStringAlgo() {
    }
    
    /**
     * Returns "()".
     * @param nu not used
     * @return String
     */
    public Object emptyCase(MTList host, Object... nu) {
        return "()";
    }
    
    
    
    /**
     * Passes "(" + first to the rest of IList and asks for help to complete the computation.
     * @param nu not used
     * @return String
     */
    public Object nonEmptyCase(NEList host, Object... nu) {
        return host.getRest().execute(ToStringHelper.Singleton, "(" + host.getFirst());
    }
}

/**
 * Helps ToStringAlgo compute the String representation of the rest of the list.
 */
class ToStringHelper implements IListAlgo {
    public static final ToStringHelper Singleton = new ToStringHelper();
    private ToStringHelper() {
    }
    
    /**
     * Returns the accumulated String + ")".
     * At end of list: done!  
     * @param acc acc[0] is the accumulated String representation of the preceding list.
     * @return String
     */
    public Object emptyCase(MTList host, Object... acc) {
        return  acc[0] + ")";
    }
    
    /**
     * Continues accumulating the String representation by appending ", " + first to acc
     * and recurse!
     * @param acc acc[0] is the accumulated String representation of the preceding list.
     * @return the String representation of the list.
     */
    public Object nonEmptyCase(NEList host, Object... acc) {
        return host.getRest().execute(this, acc[0] + ", " + host.getFirst());    
    }
}



