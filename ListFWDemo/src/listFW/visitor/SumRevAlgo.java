package listFW.visitor;
import listFW.*;

/**
 * Returns the sum of a list of integers using reverse accumulation.
 * The input parameter is not used ("nu").
 */
public class SumRevAlgo implements IListAlgo {   
    /**
     * Returns zero always. 
     */
    public Object emptyCase(MTList host, Object... nu) {
        return 0;
    }
    
    /**
     * Returns first plus the sum of the rest of the list.
     */
    public Object nonEmptyCase(NEList host, Object... nu) {

        return  (int) host.getFirst() + (int) host.getRest().execute(this);
    }
    
}