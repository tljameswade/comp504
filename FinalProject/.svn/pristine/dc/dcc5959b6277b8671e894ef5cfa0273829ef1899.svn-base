package sq6_kt21.client.chatroom.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IUser;
import common.OurDataPacket;
import common.msg.IChatMsg;
import common.msg.chat.IAddCmdMsg;
import common.msg.chat.IAddMeMsg;
import common.msg.chat.ILeaveMsg;
import common.msg.chat.INewCmdReqMsg;
import common.msg.chat.ITextMsg;
import provided.datapacket.DataPacketAlgo;
import provided.extvisitor.IExtVisitorCmd;
import provided.mixedData.IMixedDataDictionary;
import provided.mixedData.MixedDataDictionary;
import provided.mixedData.MixedDataKey;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;
import sq6_kt21.client.chatroom.view.IChatRoomViewAdpt;
import sq6_kt21.concrete.ChatServer;
import sq6_kt21.concrete.Chatroom;
import sq6_kt21.concrete.User;
import sq6_kt21.concrete.chat.AddCmdMsgCommand;
import sq6_kt21.concrete.chat.AddMeMsgCommand;
import sq6_kt21.concrete.chat.LeaveMsgCommand;
import sq6_kt21.concrete.chat.NewCmdReqCmd;
import sq6_kt21.concrete.chat.NewCmdReqMsg;
import sq6_kt21.concrete.chat.TextMsg;
import sq6_kt21.concrete.chat.TextMsgCmd;

/**
 * The chatroom model
 * @author Kun Tian, Suozhi Qi
 *
 */
public class ChatRoomModel {

	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (String s : params) {
				try {
					chatroomViewadpt.append(chatserver.getUser().getName(), s + "\n");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	/**
	 * Factory for the Registry and other uses.
	 */
	IRMIUtils rmiUtils = new RMIUtils(outputCmd);
	
	/**
	 * The command to model adapter for the chatroom
	 */
	private transient ICmd2ModelAdapter cmd2ModelAdapter = new ICmd2ModelAdapter() {
		
		@Override
		public <T extends IChatMsg> void sendMsg2LocalChatroom(Class<T> index, T msg) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public <T> boolean putIntoLocalDict(MixedDataKey<T> key, T value) {
			cacheDict.put(key, value);
			return false;
		}
		
		@Override
		public <T> T getFromLocalDict(MixedDataKey<T> key) {
			// TODO Auto-generated method stub
			return cacheDict.get(key);
		}
		
		@Override
		public IChatServer getChatServer() {
			return chatserver;
		}
		
		@Override
		public void buildComponentInScrollable(IComponentFactory fac) {
			JComponent component = fac.make();
			chatroomViewadpt.getMiniGUI().add(component);
			chatroomViewadpt.getMiniGUI().revalidate();
		}
		
		@Override
		public void buildComponentInNonScrollable(IComponentFactory fac) {
			JComponent component = fac.make();
			chatroomViewadpt.getmapPanel().add(component);
			chatroomViewadpt.getmapPanel().revalidate();
		}
	};

	/**
	 * The chatserver stub in the mini-model
	 */
	private IChatServer chatserver;

	/**
	 * The chatroom
	 */
	private IChatroom chatroom;

	/**
	 * The chatroom view adapter
	 */
	private IChatRoomViewAdpt chatroomViewadpt;
	
	/**
	 * The local dictionary to store data
	 */
	private IMixedDataDictionary cacheDict;

	/**
	 * The dataPacketAlgo
	 */
	private DataPacketAlgo<Void, Object> dataPacketAlgo = new DataPacketAlgo<>(null);

	/**
	 * The constructor
	 */
	public ChatRoomModel(IChatRoomViewAdpt chatroomViewadpt) {
		this.chatroomViewadpt = chatroomViewadpt;
	}

	/**
	 * Start the mini-model
	 * @param chatServer 
	 * @throws RemoteException 
	 */
	public void start(IChatServer chatServer) throws RemoteException {
		this.chatserver = chatServer;
		this.chatroom = chatserver.getChatroom();
		this.cacheDict = new MixedDataDictionary();
		initDataPacketAlgo();
		try {
			((ChatServer) chatserver).setDataPacketAlgo(dataPacketAlgo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private class ProxyIChatServer implements IChatServer, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7179282090628974973L;
		/**
		 * The decoree
		 */
		private IChatServer stub;

		/**
		 * Constructor for the class
		 * @param stub The decoree
		 */
		public ProxyIChatServer(IChatServer stub) {
			this.stub = stub;
		}

		/**
		 * Get the decoree
		 * @return the decoree
		 */
		@SuppressWarnings("unused")
		public IChatServer getStub() {
			return stub;
		}
		// --- Other methods and fields elided -----------

		/**
		 * Overridden equals() method to compare UUID's
		 * @return  Equal if UUID's are equal.  False otherwise.
		 */
		@Override
		public boolean equals(Object other) {
			if (other instanceof IChatServer) { // make sure that other object is an IUser
				try {
					// Equality of IUsers is same as equality of UUID's.
					return stub.getUser().getId().equals(((IChatServer) other).getUser().getId());
				} catch (RemoteException e) {
					// Deal with the exception without throwing a RemoteException.
					System.err.println("ProxyUser.equals(): error getting UUID: " + e);
					e.printStackTrace();
					// Fall through and return false
				}
			}
			return false;
		}

		/**
		 * Overridden hashCode() method to create a hashCode from that is hashCode of the UUID since
		 * equality is based on equality of UUID.
		 * @return a hashCode of the UUID.	
		 */
		@Override
		public int hashCode() {
			try {
				// hashCode is shorter than UUID, but Java spec says that if two objects are equal then
				// their hashCodes must also be equal, which will be true here since equals() is based on 
				// UUID equality.  Java does NOT require that unequal entities have unequal hashCodes. 
				return stub.getUser().getId().hashCode();
			} catch (RemoteException e) {
				// Deal with the exception without throwing a RemoteException.
				System.err.println("ProxyStub.hashCode(): Error calling remote method on IUser stub: " + e);
				e.printStackTrace();
				return super.hashCode(); // return some sort of hashCode
			}
		}

		@Override
		public IUser getUser() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getUser();
		}

		@Override
		public IChatroom getChatroom() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getChatroom();
		}
/*
		@Override
		public boolean joinChatroom(IChatServer chatStub) throws RemoteException {
			// TODO Auto-generated method stub
			return stub.joinChatroom(chatStub);
		}

		@Override
		public boolean leaveChatroom(IChatServer chatStub) throws RemoteException {
			// TODO Auto-generated method stub
			return stub.leaveChatroom(chatStub);
		}


		public void setDataPacketAlgo(DataPacketAlgo<String, IChatServer> dataPacketAlgo) throws RemoteException {
			// TODO Auto-generated method stub
			stub.setDataPacketAlgo(dataPacketAlgo);
		}
*/
		@Override
		public String toString() {
			try {
				String s = stub.getUser().getName() + "@" + stub.getUser().getIP();
				return s;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public <S> void receive(OurDataPacket<? extends IChatMsg, S> dp) throws RemoteException {
			// TODO Auto-generated method stub
			stub.receive(dp);
		}
	}

	/**
	 * The method to initiate data packet algorithm
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initDataPacketAlgo() {

		/**
		 * Set default command. Handle Unknown data TYPE!!!!
		 */
		
		dataPacketAlgo = new DataPacketAlgo<Void, Object>(new AOurDataPacketAlgoCmd<Object, IChatServer>() {

			private static final long serialVersionUID = 8L;
			@SuppressWarnings("unused")
			private transient ICmd2ModelAdapter cmd2ModelAdapter;

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				cmd2ModelAdapter = cmd2ModelAdpt;
			}

			@Override
			public Void apply(Class<?> index, OurDataPacket<Object, IChatServer> host, Object... params) {
				System.out.println("Meets unknown data packet!");

				(new Thread() {
					public void run() {
						super.run();
						UUID uuid = UUID.randomUUID();
						MixedDataKey<Object> unknownMsgKey = new MixedDataKey<Object>(uuid, "", Object.class);
						cacheDict.put(unknownMsgKey, (Object)host);
						OurDataPacket<INewCmdReqMsg, IChatServer> dpDataPacket = new OurDataPacket<INewCmdReqMsg, IChatServer>
							(INewCmdReqMsg.class, new NewCmdReqMsg(host.getData().getClass(), uuid), chatserver);
						//System.out.println("aaaaaaaa");
						// put in dict
						
						//OurDataPacket<INewCmdReqMsg, IChatServer> dataPacket = new OurDataPacket<INewCmdReqMsg, IChatServer>(INewCmdReqMsg.class, new NewCmdReqMsg(host.getData().getClass(),uuid), chatserver);
						//AOurDataPacketAlgoCmd<?, IChatServer> unknownCmd = null;
						try {
							
							host.getSender().receive(dpDataPacket);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}).start();
				return null;
			}
		});
		
				/**
				 * construct a image command
				 */
				ImageCommand imageCommand = new ImageCommand();
				
				/**
				 * A new string command
				 */
				StringCommand stringCommand = new StringCommand();
				
				/**
				 * A new game command
				 */
				GameCommand gameCommand = new GameCommand();
				
				/**
				 * A new text command
				 */
				TextMsgCmd txtCmd = new TextMsgCmd();
				
				/**
				 * Newly constructed image command set the command to model adapter
				 */
				imageCommand.setCmd2ModelAdpt(cmd2ModelAdapter);
				
				/**
				 * Newly constructed string command set the command to model adapter
				 */
				stringCommand.setCmd2ModelAdpt(cmd2ModelAdapter);
				
				/**
				 * Newly constructed game command set the command to model adapter
				 */
				gameCommand.setCmd2ModelAdpt(cmd2ModelAdapter);
				
				/**
				 * Newly constructed text command set the command to model adapter
				 */
				txtCmd.setCmd2ModelAdpt(cmd2ModelAdapter);
				
				/**
				 * Add the newly constructed image command to the local data packet
				 */
				dataPacketAlgo.setCmd(ImageIcon.class, (IExtVisitorCmd)imageCommand);
				
				/**
				 * Add the newly constructed string command to the local data packet
				 */
				dataPacketAlgo.setCmd(String.class, (IExtVisitorCmd)stringCommand);
				
				/**
				 * Add the newly constructed game command to the local data packet
				 */
				dataPacketAlgo.setCmd(GameInfo.class, (IExtVisitorCmd)gameCommand);
				
				/**
				 * Add the newly constructed leave message command to the local data packet
				 */
				dataPacketAlgo.setCmd(ILeaveMsg.class, new LeaveMsgCommand(cmd2ModelAdapter));
				
				/**
				 * Add the newly constructed add me command to the local data packet
				 */
				dataPacketAlgo.setCmd(IAddMeMsg.class, new AddMeMsgCommand(cmd2ModelAdapter));
				
				/**
				 * Add the newly constructed text msg command to the local data packet
				 */
				dataPacketAlgo.setCmd(ITextMsg.class, txtCmd);
				
				/**
				 * Construct a new command request command
				 */
				
				NewCmdReqCmd newCmd = new NewCmdReqCmd(cmd2ModelAdapter, dataPacketAlgo);
				
				/**
				 * Add the newly constructed new command request command to the local data packet
				 */
				dataPacketAlgo.setCmd(INewCmdReqMsg.class, newCmd);
				
				/**
				 * Construct a new add command command
				 */
				AddCmdMsgCommand addCmdCmd = new AddCmdMsgCommand(cmd2ModelAdapter, dataPacketAlgo, cacheDict);
				
				/**
				 * Add the newly constructed add command command to the local data packet
				 */
				dataPacketAlgo.setCmd(IAddCmdMsg.class, addCmdCmd);
				
//				dataPacketAlgo.setCmd(TeamMsg.class, new TeamMsgCmd(cmd2ModelAdapter));
	}
	

	/**
	 * Add user 
	 * @param IPremote the IP address of the user to be invited
	 */

	/**
	 * The add user method
	 * @param friend the string representation of the IP address of a remote user
	 */
	public void addUser(String friend) {

		Registry registry = rmiUtils.getRemoteRegistry(friend);
		try {
			IUser user = (IUser) registry.lookup(IUser.BOUND_NAME);
			System.out.println("add user (name): " + user.getName());
			IChatServer chatstub = ((User)user).createNewRoomServer(chatroom);

			for (IChatServer members : chatroom.getChatServers()) {
				if (((ChatServer) members).joinChatroom(chatstub)) {
					//					members.refresh();
					chatroomViewadpt.append(members.getUser().getName(),
							"Successfully add " + members.getUser().getName() + " to chatroom!\n");
				} else {
					chatroomViewadpt.append(members.getUser().getName(),
							"Failed to add this member:" + members.getUser().getName() + "to chatroom!\n");
				}
			}
			for (IChatServer server : chatroom.getChatServers()) {
				System.out.println(server.getUser().getIP());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Leave self from the chatroom
	 * 1. remove the chatroom from the user;
	 * 2. remove the user chatserver from other's chatroom
	 * @throws RemoteException 
	 */
	public void leaveRoom() throws RemoteException {
		chatroomViewadpt.closeRoom();
	}

	/**
	 * Remove the selected user from the chatroom
	 * @param selectedUser the selected user
	 */
	public void removeUser(IChatServer selectedUser) {
	};

	/**
	 * Send a string text
	 * @param text the string text
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void send(String text) {
		try {
			HashSet<IChatServer> users= ((Chatroom)chatserver.getChatroom()).getChatServers();
			TextMsg txt = new TextMsg(text);
			for (IChatServer user: users) {
				OurDataPacket<ITextMsg,IChatServer> datapacket = new OurDataPacket<ITextMsg,IChatServer>(ITextMsg.class, txt, chatserver);
				user.receive((OurDataPacket)datapacket);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sendImg() {
		ImageIcon image = new ImageIcon("sq6_kt21/client/chatroom/images/girl.jpg");
		try {
			HashSet<IChatServer> users= chatserver.getChatroom().getChatServers();
			for (IChatServer user: users) {
				OurDataPacket<ImageIcon, IChatServer> datapacket = new OurDataPacket<ImageIcon, IChatServer>(ImageIcon.class, image, chatserver);
				user.receive((OurDataPacket)datapacket);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all chat stubs in the chatroom
	 * @return a hashset of all the chat stubs in the chatrooms
	 */
	public HashSet<IChatServer> getChatStubs() {
		HashSet<IChatServer> proxyChatServer = new HashSet<>();
		HashSet<IChatServer> chatStubs;
		try {
			chatStubs = chatserver.getChatroom().getChatServers();
			for (IChatServer stub : chatStubs) {
				ProxyIChatServer proxyChatter = new ProxyIChatServer(stub);
				proxyChatServer.add(proxyChatter);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxyChatServer;
	}


	/**
	 * The method to start the game in the chatroom
	 * @param g the gameInfo
	 */
	public void startGame(GameInfo g) {
		try {
			HashSet<IChatServer> users = chatroom.getChatServers();

			for (IChatServer user: users) {
				new Thread() {
					@SuppressWarnings({ "rawtypes", "unchecked" })
					public void run() {
						super.run();
						try {
							OurDataPacket<GameInfo, IChatServer> datapacket = new OurDataPacket<GameInfo, IChatServer>(GameInfo.class, new GameInfo(), chatserver);
							user.receive((OurDataPacket)datapacket);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
