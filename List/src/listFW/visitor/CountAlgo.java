package listFW.visitor;
import listFW.*;

/**
 * Returns the count of the number of elements in the list
 * 
 */
public class CountAlgo implements IListAlgo {   
    /**
     * Returns zero always
     */
    public Object emptyCase(MTList host, Object... inp) {

        return 0;
    }
    
    /**
     * Returns 1 plus the count of the rest of the list.
     */
    public Object nonEmptyCase(NEList host, Object... inp) {

        return 1+(int)host.getRest().execute(this);
    }
    
}