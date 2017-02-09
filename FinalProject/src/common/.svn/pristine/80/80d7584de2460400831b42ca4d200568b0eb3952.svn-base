package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.UUID;


import common.IChatroom;
import common.msg.IUserMsg;

/**
 * IUser interface which will maintain a set of chatrooms
 * 
 */
public interface IUser extends Remote {
	
	/**
	 * The name the IUser object will be bound to in the RMI Registry
	 */
	public static final String BOUND_NAME = "User";

	/**
	 * The port that the client will use to communicate with the IUser object
	 * Note that this port must be opened of inbound traffic on the server machine
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
	 * Receive message (and then call msg.execute(algo) to process it) as a remote IUser stub.
	 * 
	 * @param dp - A datapacket containing message that implements IUserMsg.
	 * @throws RemoteException
	 */
	public <S> void receive(OurDataPacket<? extends IUserMsg,S> dp) throws RemoteException;

	/**
	 * This method will give back its local IUser stub to the remote user being connected to. 
	 * It supports an "auto-connect-back" feature wherein an accepted request to chat is 
	 * automatically connected without the user having to know the requestor's address.
	 * @param userStub - The IUser stub being passed to the remote user object
	 * @throws RemoteException
	 */
	public void connectBack(IUser userStub) throws RemoteException;
	
	/**
	 * Get a unique ID of the user
	 * @return A unique identifier of the user
	 * @throws RemoteException 
	 */
	UUID getId() throws RemoteException;

	/**
	 * Get user name 
	 * @return the name for this user
	 * @throws RemoteException 
	 **/
	public String getName() throws RemoteException;

	/**
	 * Get IP address from this user 
	 * @return a String type IP address for this user
	 * @throws RemoteException 
	 */
	public String getIP() throws RemoteException;

	/**
	 * Get a set of chatrooms from this user
	 * @return a HashSet of chatrooms 
	 * @throws RemoteException 
	 * */
	public HashSet<IChatroom> getChatrooms() throws RemoteException;
	
}