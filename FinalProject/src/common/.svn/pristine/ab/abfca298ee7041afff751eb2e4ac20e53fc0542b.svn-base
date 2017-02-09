package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.msg.IChatMsg;

/**
 * IChatServer interface is associated with a particular chatroom and used to receive the message targeted directly
 * to the chatroom.
 */
public interface IChatServer extends Remote {

	/**
	 * for server app instances
	 */
	public static final int BOUND_PORT_SERVER = 2100;
	/**
	 * for client app instances
	 */
	public static final int BOUND_PORT_CLIENT = 2101;
	/**
	 * for 2nd client app for test on a single computer. you probably need not use this port.
	 */
	public static final int BOUND_PORT_CLIENT_SECONDARY = 2102;
	
	/**
	 * Receive message (and then call msg.execute(algo) to process it) as a remote IChatServer stub.
	 * 
	 * @param dp -  A message that implements IChatMsg.
	 * @throws RemoteException
	 */
	public <S> void receive(OurDataPacket<? extends IChatMsg, S> dp) throws RemoteException;
	
	/**
	 * Get the user associated with this chatserver
	 * @return the user object
	 * @throws RemoteException 
	 **/
	public IUser getUser() throws RemoteException;

	/**
	 * Get the chatroom that it associated with
	 * @return an IChatroom type
	 * @throws RemoteException 
	 * */
	public IChatroom getChatroom() throws RemoteException;

}
