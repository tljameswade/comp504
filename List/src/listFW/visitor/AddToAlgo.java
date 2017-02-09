package listFW.visitor;
import listFW.*;

/**
 * Returns a copy of the list where the single integer parameter is added to all elements
 */
public class AddToAlgo implements IListAlgo {   
    /**
     * Returns an empty list always
     * 
     */
    public Object emptyCase(MTList host, Object... inp) {
        return MTList.Singleton;
    }
    
    /**
     * Returns a new NEList with the original first added to the given integer parameter, where the rest of the 
     * list is the AddToAlgo applied to the rest of the original list.
     */
    public Object nonEmptyCase(NEList host, Object... inp) {

        return new NEList(Integer.parseInt((String)inp[0]) + (int) host.getFirst(), (IList) host.getRest().execute(this, inp));
    }
    
}