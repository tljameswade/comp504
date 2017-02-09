package util;

/**
 * An observer for IDispatcher<TDispMsg>.  When registered with a dispatcher,
 * an observer will receive the TDispMsg-type message that the dispatcher is sends 
 * to its registered observers.
 * 
 * @author Suozhi Qi, Zhaohan Jia
 *
 * @param <TDispMsg> The type of message that this observer can process
 */
public interface IObserver<TDispMsg> {
	/**
	 * The method that the dispatcher will call
	 * to process the supplied message.
	 * @param dispatcher The dispatcher sending the message
	 * @param msg The message for this observer to process.
	 */
	public void update(IDispatcher<TDispMsg> dispatcher, TDispMsg msg);
}
