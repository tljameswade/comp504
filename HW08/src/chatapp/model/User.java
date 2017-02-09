package chatapp.model;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.UUID;

import chatapp.model.ChatAppModel;
import chatroom.model.Chatroom;

import common.IChatServer;
import common.IChatroom;
import common.IUser;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;

/**
 * 
 * @author Suozhi Qi, kejunliu
 *
 */
public class User implements IUser {

	/**
	 * The UUID of the user
	 */
	private UUID id;

	/**
	 * the user name
	 */
	private String name;

	/**
	 * The IP of the user
	 */
	private String ip;

	/**
	 * the chatrooms that the user has access to
	 */
	private HashSet<IChatroom> chatrooms;

	/**
	 * The main model
	 */
	private ChatAppModel model;
	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (@SuppressWarnings("unused")
			String s : params) {

			}
		}
	};

	/**
	 * Constructor for User
	 */
	public User(String name, String ip, ChatAppModel model) {
		id = UUID.randomUUID();
		this.name = name;
		this.ip = ip;
		this.chatrooms = new HashSet<IChatroom>();
		this.model = model;
	}

	/**
	 * Get ID of the user
	 */
	@Override
	public UUID getId() throws RemoteException {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * Get the name of the user
	 */
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * Set the name of the user
	 */
	@Override
	public void setName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
	}

	/**
	 * Get the IP of the user
	 */
	@Override
	public String getIP() throws RemoteException {
		// TODO Auto-generated method stub
		return this.ip;
	}

	/**
	 * Get the chatrooms that the user access to
	 */
	@Override
	public HashSet<IChatroom> getChatrooms() throws RemoteException {
		// TODO Auto-generated method stub
		return this.chatrooms;
	}

	/**
	 * Add a chatroom
	 */
	@Override
	public boolean addRoom(IChatroom chatroom) throws RemoteException {
		// TODO Auto-generated method stub
		return this.chatrooms.add(chatroom);
	}

	/**
	 * Remove a chatroom
	 */
	@Override
	public boolean removeRoom(IChatroom chatroom) throws RemoteException {
		// TODO Auto-generated method stub
		return this.chatrooms.remove(chatroom);
	}

	/**
	 * Create a newroom server
	 */
	@Override
	public IChatServer createNewRoomServer(IChatroom chatroom) throws RemoteException {

		IRMIUtils rmiUtils = new RMIUtils(outputCmd);

		Chatroom roomLocal = new Chatroom(chatroom.getName());
		IChatServer chatServer = new ChatServer(this, roomLocal);
		roomLocal.addChatServer(chatServer);

		for (IChatServer server : chatroom.getChatServers()) {
			roomLocal.addChatServer(server);
		}
		Registry registry = rmiUtils.getLocalRegistry();

		try {
			addRoom(roomLocal);
			registry.rebind(IChatServer.BOUND_NAME + chatroom.hashCode(), chatServer);
			IChatServer chatStub = (IChatServer) UnicastRemoteObject.exportObject(chatServer, IChatServer.BOUND_PORT);
			chatroom.addChatServer(chatServer);
			model.popChatRoom(roomLocal, chatServer);
			return chatStub;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toString() {
		return this.name;
	}

	ChatAppModel getmodel() {
		return this.model;
	}

	@Override
	public void connectBack(IUser userStub) throws RemoteException {
		// TODO Auto-generated method stub

	};

}