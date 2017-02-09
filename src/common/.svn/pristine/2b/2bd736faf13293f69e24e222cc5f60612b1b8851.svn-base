package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacketAlgo;

/**
 * IChatServer interface is associated with a particular chatroom and used to receive the message targeted directly
 * to the chatroom.
 */
public interface IChatServer extends Remote {

	/**
	 * The name the IUser object will be bound to in the RMI Registry
	 */
	public static final String BOUND_NAME = "ChatServer";

	/**
	 * The port that the chatroom will use to communicate with the IChatServer object
	 * Note that this port must be opened of inbound traffic on the server machine
	 */
	public static final int BOUND_PORT = 2101;
	
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


	/**
	 * Add an IChatServer stub into the IChatServer list of its chatroom
	 * @param chatStub to be added into the IChatServer list of its chatroom
	 * @return True if chatStub does not belong to the chatroom and false if chatStub belongs to the chatroom.
	 * @throws RemoteException 
	 */
	public boolean joinChatroom(IChatServer chatStub) throws RemoteException;

	/**
	 * Remove an IChatServer stub from the IChatServer list of its chatroom
	 * @param chatStub to be added into the IChatServer list of its chatroom
	 * @return True if chatStub is removed from the chatroom and false if the chatStub is not in chatroom.
	 * @throws RemoteException 
	 */
	public boolean leaveChatroom(IChatServer chatStub) throws RemoteException;

	/**
	 * Receive and process the message sent by the remote application
	 * @param sender - the user who sent the message
	 * @param message - the data packet that they sent
	 * @throws RemoteException 
	 * */
	public void receive(ADataPacket message) throws RemoteException;
	
	/**
	 * This method is used to communicate back to the sender so we can get the new command to deal with 
	 * the unknown datapacket
	 * @param requestClass - A data type needed to be process
	 * @return a new command to deal with the unknown datapacket
	 * @throws RemoteException
	 */
	public ADataPacketAlgoCmdGroupF<?> getNewCommand(Class<?> requestClass) throws RemoteException;

	/**
	 * @param dataPacketAlgo
	 * @throws RemoteException
	 */
	void setDataPacketAlgo(DataPacketAlgo<String, IChatServer> dataPacketAlgo) throws RemoteException;
	
	/**
	 * This method is used to refresh the friend list of a chatroom. But note that this method will 
	 * seldom be used as all the refresh process are handled automatically after we add a user or 
	 * remove a user
	 * @throws RemoteException
	 */
	public void refresh() throws RemoteException;
}
