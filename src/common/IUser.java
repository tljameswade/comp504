package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.UUID;


import common.IChatroom;

/**
 * IUser interface which will maintain a set of chatrooms
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
	public static final int BOUND_PORT = 2100;
	
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
	 * Set user name
	 * @param name - The user name
	 * @throws RemoteException 
	 */
	public void setName(String name) throws RemoteException;

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

	/**
	 * Add a Chatroom class into its chatroom set
	 * @param chatroom - The chatroom need to be added into its list
	 * @return True if chatroom does not belong to the user and false if user belongs to the user.
	 * @throws RemoteException 
	 */
	public boolean addRoom(IChatroom chatroom) throws RemoteException;

	/**
	 * Remove a Chatroom class from its chatroom set
	 * @param chatroom - The chatroom need to be removed from its list
	 * @return True if chatroom is removed from the user and false if the chatroom is not in user.
	 * @throws RemoteException 
	 */
	public boolean removeRoom(IChatroom chatroom) throws RemoteException;


	/**
	 * This method will give back its local IUser stub to the remote user being connected to. 
	 * It supports an "auto-connect-back" feature wherein an accepted request to chat is 
	 * automatically connected without the user having to know the requestor's address.
	 * @param userStub - The IUser stub being passed to the remote user object
	 * @throws RemoteException
	 */
	public void connectBack(IUser userStub) throws RemoteException;
	

	/**
	 * This method is used to invite user.  When a local user invite somebody, the remote user 
	 * need to first create a chatserver and return that server to the local user 
	 * to enable local user to update
	 * @param chatroom - A chatRoom from remote user
	 * @return chatserver stub return a chat server mean this server this stub
	 * @throws RemoteException
	 */
	public IChatServer createNewRoomServer(IChatroom chatroom)throws RemoteException;

}