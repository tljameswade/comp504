package listFW.visitor;
import listFW.*;

/**
 * Returns a copy of the list
 * 
 */
public class CopyAlgo implements IListAlgo {   
    /**
     * Returns an MTList always.
     */
    public Object emptyCase(MTList host, Object... inp) {
        return MTList.Singleton;
    }
    
    /**
     * Returns a new NEList where first is the original first and 
     * rest is a copy of the original rest.
     */
    public Object nonEmptyCase(NEList host, Object... inp) {

        return new NEList(host.getFirst(), (IList) host.getRest().execute(this, inp));
    }
    
}



