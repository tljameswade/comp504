package util;

import java.util.Collection;

/**
 * An abstract Collection-based IDispatcher.
 * 
 * @author Suozhi Qi, Zhaohan Jia
 *
 * @param <TDispMsg> The type of message sent to the registered IObservers
 */
public abstract class ACollectionDispatcher<TDispMsg> implements IDispatcher<TDispMsg> {

	/**
	 * Stores the observers.  
	 */
	private final Collection<IObserver<TDispMsg>> observers;

	/**
	 * Constructor for the class.   The Collection that is used needs to be supplied, 
	 * generally by the implementing subclass.   This allows for different types of 
	 * Collections to be used for different purposes.  It is highly recommended that the 
	 * supplied Collection be completely thread-safe to enable the use of 
	 * multiple dispatching threads.
	 * @param observers  The Collection of IObserver<TDispMsg> to use.
	 */
	public ACollectionDispatcher(Collection<IObserver<TDispMsg>> observers) {
		this.observers = observers;
	}

	/**
	 * Accessor method for the internal Collection for use by implementing subclasses.
	 * @return The internal Collection of IObservers<TDispMsg>
	 */
	protected Collection<IObserver<TDispMsg>> getCollection() {
		return observers;
	}

	/**
	 * {@inheritDoc}<br/>
	 * Implementation: Add the given observer to the internal Collection.
	 */
	@Override
	public void addObserver(IObserver<TDispMsg> obs) {
		observers.add(obs);
	}

	/**
	 * {@inheritDoc}<br/>
	 * Implementation: Delete the given observer from the internal Collection.
	 */
	@Override
	public void deleteObserver(IObserver<TDispMsg> obs) {
		observers.remove(obs);
	}

	/**
	 * {@inheritDoc}<br/>
	 * Implementation: Delete all the observers from the internal Collection.
	 */
	@Override
	public void deleteObservers() {
		observers.clear();
	}
}
