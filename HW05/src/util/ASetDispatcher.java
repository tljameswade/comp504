package util;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * A Collection-based Dispatcher that uses a CopyOnWriteArraySet.
 * 
 * @author Suozhi Qi, Zhaohan Jia
 *
 * @param <TDispMsg> The type of message sent to the registered IObservers
 */
public abstract class ASetDispatcher<TDispMsg> extends ACollectionDispatcher<TDispMsg> {

	/**
	 * The constructor for the class that supplies a CopyOnWriteArraySet instance to the superclass constructor.
	 */
	public ASetDispatcher() {
		super(new CopyOnWriteArraySet<>()); // Type of CopyOnWriteArraySet is inferred by compiler
	}

}
