package util;

/**
 * A CopyOnWriteArraySet-based IDispatcher that dispatches to its IObservers in parallel.
 * 
 * @author Suozhi Qi, Zhanhan Jia
 *
 * @param <TDispMsg> The type of message sent to the registered IObservers
 */
public class SetDispatcherSequential<TDispMsg> extends ASetDispatcher<TDispMsg> {

	/**
	 * {@inheritDoc}<br/>
	 * Implementation: Attempts to perform parallel dispatching of the message to the collection of IObservers.  
	 * Note that parallel execution is not guaranteed.
	 */
	@Override
	public void dispatch(TDispMsg msg) {
		getCollection().forEach(o -> {
			o.update(this, msg);
		});
	}
}