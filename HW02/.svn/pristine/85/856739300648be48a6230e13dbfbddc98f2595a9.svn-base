package util;

import java.util.*;

/**
 * A simplified Observable class that immediately notifies its Observers when its notifyAll() method is called.   The changed state of the Dispatcher does not need to be separately set.
 */
public class Dispatcher extends Observable {
	/**
	 * Immediately notifies all the Observers held.
	 * @param param An input parameter that is passed on to all the Observers.
	 */
	public void notifyAll(Object param) {
		setChanged();
		notifyObservers(param);
	}
}
