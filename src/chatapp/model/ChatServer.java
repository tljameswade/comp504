package chatapp.model;

import java.io.Serializable;
import java.rmi.RemoteException;

import common.ADataPacketAlgoCmdGroupF;
import common.IChatServer;
import common.IChatroom;
import common.IUser;
import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacketAlgo;
/**
 * concrete chatserver
 * @author Suozhi Qi, kejunliu
 *
 */
public class ChatServer implements IChatServer,Serializable{
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = 524963569610023176L;

	/**
	 * The user in the chatserver stub
	 */
	private IUser user;
	
	/**
	 * The chatroom in the chatserver stub
	 */
	private IChatroom chatroom;
	
	/**
	 * The datapacket algorithm to handle data packet
	 */
	DataPacketAlgo<String, IChatServer> dataPacketAlgo = new DataPacketAlgo<String, IChatServer> (null); 
	
	/**
	 * Constructor
	 * @param user the user in the stub
	 * @param chatroom the chatroom in the stub
	 */
	public ChatServer(IUser user, IChatroom chatroom) {
		this.user = user;
		this.chatroom = chatroom;
	}
	
	/**
	 * Get the user
	 * @return the user in the stub
	 * @throws RemoteException
	 */
	@Override
	public IUser getUser() throws RemoteException {
		// TODO Auto-generated method stub
		return this.user;
	}
	
	/**
	 * Get the chatroom
	 * @return the chatroom of the stub
	 * @throws RemoteException
	 */
	@Override
	public IChatroom getChatroom() throws RemoteException {
		// TODO Auto-generated method stub
		return this.chatroom;
	}

	/**
	 * Add another member to the chatroom
	 * @param chatStub the chatStub of the member to join this chatroom
	 * @return boolean value
	 * @throws RemoteException
	 */
	@Override
	public boolean joinChatroom(IChatServer chatStub) throws RemoteException {
		// TODO Auto-generated method stub
		return chatroom.addChatServer(chatStub);
	}

	/**
	 * Executes when a member leaves the chatroom
	 * @param chatStub the chatstub of the member to leave this chatroom
	 * @return boolean value
	 * @throws RemoteException
	 */
	@Override
	public boolean leaveChatroom(IChatServer chatStub) throws RemoteException {
		// TODO Auto-generated method stub
		return chatroom.removeChatServer(chatStub);
	}

	/**
	 * Receive a message using the chatserver stub
	 * @param message the message to be received
	 * @throws RemoteException
	 */
	@Override
	public void receive(ADataPacket message) throws RemoteException {
		// TODO Auto-generated method stub
		message.execute(dataPacketAlgo);
	}

	/**
	 * Set the datapacket algorithm to handle the message
	 * @param dataPacketAlgo the datapacket algorithm to execute the message
	 * @throws RemoteException
	 */
	@Override
	public void setDataPacketAlgo(DataPacketAlgo<String, IChatServer> dataPacketAlgo) throws RemoteException {
		// TODO Auto-generated method stub
		this.dataPacketAlgo = dataPacketAlgo;
	}

	@Override
	public void refresh() throws RemoteException {

	}
	
	public String toString() {
		try {
			return user.getName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ADataPacketAlgoCmdGroupF<?> getNewCommand(Class<?> requestClass) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
