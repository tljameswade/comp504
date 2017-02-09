package sq6_kt21.concrete;


import java.io.Serializable;
import java.rmi.RemoteException;


import common.IChatServer;
import common.IChatroom;
import common.IUser;
import common.OurDataPacket;
import common.msg.IChatMsg;
import provided.datapacket.DataPacketAlgo;
import sq6_kt21.client.chatroom.controller.ChatRoomController;

/**
 * The concrete chat server
 * @author Suozhi Qi, Kun Tian
 *
 */
public class ChatServer implements IChatServer, Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 524963569610023176L;

	/**
	 * The user in the chatserver stub
	 */
	private IUser user;

	/**
	 * The chatroom controller in the chatserver
	 */
	ChatRoomController crController;
	/**
	 * The chatroom in the chatserver stub
	 */
	private IChatroom chatroom;

	/**
	 * The datapacket algorithm to handle data packet
	 */
	DataPacketAlgo<Void, Object> dataPacketAlgo = new DataPacketAlgo<Void, Object>(null);

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
	public boolean leaveChatroom(IChatServer chatStub) throws RemoteException {
		// TODO Auto-generated method stub
		return chatroom.removeChatServer(chatStub);
	}

	/**
	 * Set the datapacket algorithm to handle the message
	 * @param dataPacketAlgo the datapacket algorithm to execute the message
	 * @throws RemoteException
	 */
	public void setDataPacketAlgo(DataPacketAlgo<Void, Object> dataPacketAlgo) throws RemoteException {
		// TODO Auto-generated method stub
		this.dataPacketAlgo = dataPacketAlgo;
	}

	/**
	 * The tostring method
	 * @return the user's user name
	 */
	@Override
	public String toString() {
		try {
			return user.getName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Set the controller of the mini controller
	 * @param crController the chat room controller
	 */
	public void setController(ChatRoomController crController) {
		this.crController = crController;
	}


	@Override
	public <S> void receive(OurDataPacket<? extends IChatMsg, S> dp) throws RemoteException {
		/*
		if (dataPacketAlgo.getCmd(((OurDataPacket<?>) message).getData().getClass()) == null) {
	
			IChatServer sender = (IChatServer) ((OurDataPacket<?>) message).getSender();
			Class<?> index = ((OurDataPacket<?>) message).getData().getClass();
			ADataPacketAlgoCmdGroupF<?> newCmd = sender.getNewCommand(index);
			
			newCmd.setCmd2ModelAdpt(new ICmd2ModelAdapter() {
				@Override
				public void buildComponentInScrollable(IComponentFactory fac) {
					JComponent comp = fac.make();
					crController.getGUI().getJPanel().add(comp);
				}

				@Override
				public void buildComponentInNonScrollable(IComponentFactory fac) {
					JComponent comp = fac.make();
					crController.getGUI().getMapPanel().add(comp);
				}

				@Override
				public <T> boolean putIntoLocalDict(MixedDataKey<T> key, T value) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public <T> T getFromLocalDict(MixedDataKey<T> key) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public <T extends IChatMsg> void sendMsg2LocalChatroom(Class<T> index, T msg) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public IChatServer getChatServer() {
					return sender;
				}

			});
			
			dataPacketAlgo.setCmd(index, (IExtVisitorCmd)newCmd);
		}
		*/
		//System.out.println(dataPacketAlgo);
//		System.out.println("hello");
		dp.execute(dataPacketAlgo);
//		System.out.println("hello");
	}

	
}
