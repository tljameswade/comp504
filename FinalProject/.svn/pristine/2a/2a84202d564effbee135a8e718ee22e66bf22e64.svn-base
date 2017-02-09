package sq6_kt21.server.game.model;

import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import common.IChatServer;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IUser;
import common.OurDataPacket;

import common.msg.IChatMsg;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Annotation;
import map.MapLayer;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.IMixedDataDictionary;
import provided.mixedData.MixedDataDictionary;
import provided.mixedData.MixedDataKey;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;
import sq6_kt21.server.game.model.message.CityMsg;
import map.ToggleAnnotation;




public class GameModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3210917738510721660L;
	
	String ip;
	
	/**
	 * The lambda to print the message out on the server view
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String> (){
		public void apply(String... strs){
			for(String s: strs) viewAdapter.appendToGlobal(s);
		}
	};
	/**
	 * Utility object used to get the Registry
	 */
	private IRMIUtils rmiUtils = new RMIUtils(outputCmd);
	
	/**
	 * The model to view adapter
	 */
	private IGameModel2ViewAdapter viewAdapter;
	
	/**
	 * The server stub
	 */
	private IChatServer serverStub;
	
	/**
	 * The client stub
	 */
	private IChatServer clientStub;
	
	/**
	 * The local data packet algo dictionary
	 */
	private DataPacketAlgo<Void, Object> dataPacketAlgo;
	
	/**
	 * The map layer to hold annotations
	 */
	MapLayer _layer = new MapLayer();
	
	/**
	 * The number of selected cities
	 */
	int numberOfSelected;
	
	//public static final UUID uuid = UUID.randomUUID();
	
	/**
	 * The list to hold selected cities
	 */
	ArrayList<String> cityList;
	
	/**
	 * The map to store the amount of treasure each city holds
	 */
	HashMap<String, Integer> valueMap = new HashMap<>();
	
	/**
	 * The local game dictionary
	 */
	private IMixedDataDictionary gameDict = new MixedDataDictionary();

	/**
	 * The local command to model adapter
	 */
	@SuppressWarnings("unused")
	private ICmd2ModelAdapter iCmd2ModelAdapter;

	public static final UUID uuid = UUID.randomUUID();
	
	/**
	 * The game model constructor
	 * @param model2ViewAdpt
	 */
	public GameModel(IGameModel2ViewAdapter model2ViewAdpt) {
		this.viewAdapter = model2ViewAdpt;
		
	}
	
	/**
	 * The method to start the game
	 */
	public void start() {
		try {
			this.ip = rmiUtils.getLocalAddress();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		
		this.viewAdapter.appendToGlobal("My IP address is " + ip);
		
		this.clientStub = new IChatServer() {
			
			@Override
			public <S> void receive(OurDataPacket<? extends IChatMsg, S> dp) throws RemoteException {
				receiveMessage(dp);
			}
			
			@Override
			public IUser getUser() throws RemoteException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public IChatroom getChatroom() throws RemoteException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		try {
			serverStub = (IChatServer) UnicastRemoteObject.exportObject(clientStub, IUser.BOUND_PORT_SERVER);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Position willy = Position.fromDegrees(29.718550, -95.399068);
		  Position epcot = Position.fromDegrees(28.374454, -81.549363);
		  Position nyc   = Position.fromDegrees(40.748974, -73.990288);
		  Position london = Position.fromDegrees(51.503367, -0.119968);
		  Position losAngeles = Position.fromDegrees(34.0522, -118.2437);
		  Position hongKong = Position.fromDegrees(22.3964, 114.1095);
		  Position paris = Position.fromDegrees(48.8566, 2.3522);
		  Position auckland = Position.fromDegrees(-36.8485, 174.7633);
		  
		  
		  _layer.addToggleAnnotation("Willy", "Willy Selected!", willy);
		  _layer.addToggleAnnotation("Epcot Center", "Epcot Selected!", epcot);
		  _layer.addToggleAnnotation("New York", "New York Selected", nyc);
		  _layer.addToggleAnnotation("London", "London Selected", london);
		  _layer.addToggleAnnotation("LosAngeles", "LosAngeles Selected", losAngeles);
		  _layer.addToggleAnnotation("Hong Kong", "Hong Kong Selected", hongKong);
		  _layer.addToggleAnnotation("Paris", "Paris Selected", paris);
		  _layer.addToggleAnnotation("Auckland", "Auckland Selected", auckland);
		  
		  
		  valueMap.put("Willy", 100);
		  valueMap.put("Epcot", 50);
		  valueMap.put("New York", 200);
		  valueMap.put("London", 80);
		  valueMap.put("LosAngeles", 180);
		  valueMap.put("Hongkong", 10);
		  valueMap.put("Paris", 60);
		  valueMap.put("Auckland", 70);
		
		viewAdapter.show(_layer);
		


	}


	/**
	 * The method to get the IP
	 * @return a string representation of the IP
	 */
	public String getIP() {
		return this.ip;
	}
	
	public <S> void receiveMessage(OurDataPacket<? extends IChatMsg, S> dp) {
		dp.execute(dataPacketAlgo);
	}

	/**
	 * Set the properties of the server stub and the local dataPacketAlgo dictionary
	 * @param server the server's stub
	 * @param _dataPacketAlgo the local datapacket dictionary
	 */
	public void setProperties(IChatServer server, DataPacketAlgo<Void, Object> _dataPacketAlgo){
		this.serverStub = server;
		this.dataPacketAlgo = _dataPacketAlgo;
		
		iCmd2ModelAdapter = new ICmd2ModelAdapter() {
			
			@Override
			public <T extends IChatMsg> void sendMsg2LocalChatroom(Class<T> index, T msg) {
				
			}
			
			@Override
			public <T> boolean putIntoLocalDict(MixedDataKey<T> key, T value) {
				gameDict.put(key, value);
				return true;
			}
			
			@Override
			public <T> T getFromLocalDict(MixedDataKey<T> key) {
				return gameDict.get(key);
			}
			
			@Override
			public IChatServer getChatServer() {
				return null;
			}
			
			@Override
			public void buildComponentInScrollable(IComponentFactory fac) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void buildComponentInNonScrollable(IComponentFactory fac) {
				// TODO Auto-generated method stub
				
			}
		};
		
//		MixedDataKey<GameModel> gameModelKey = new MixedDataKey<GameModel>(uuid, "gameModel", GameModel.class);
//		gameDict.put(gameModelKey, this);
		
//		(AOurDataPacketAlgoCmd<TeamMsg, IChatServer>) dataPacketAlgo.getCmd(TeamMsg.class);
//		cmd.setCmd2ModelAdpt(iCmd2ModelAdapter);
		
	}

	/**
	 * The method to count selected cities
	 * @return the number of cities selected
	 */
	public int countSelected() {
		numberOfSelected = 0;
		for (Annotation annotation: _layer.getAnnotations()) {
			if(annotation instanceof ToggleAnnotation){  // must check this before GlobeAnnotation
				if (((ToggleAnnotation) annotation).isSelected()) numberOfSelected++;
			}
		}
		// TODO Auto-generated method stub
		return numberOfSelected;
	}
	

	/**
	 * The method to get the number of selected cities
	 * @return The number of selected cities
	 */
	public int getSelected() {
		cityList = new ArrayList<String>();
		for (Annotation annotation: _layer.getAnnotations()) {
			if(annotation instanceof ToggleAnnotation){
				if (((ToggleAnnotation) annotation).isSelected()) {
					String s = annotation.toString();
					int num = s.indexOf("Selected");
					cityList.add(s.substring(1, num - 1));
				}
			}
		}
		
		int num = 0;
		
		for (String string : cityList) {
			num += valueMap.get(string);
		}
		return num;
	}

	/**
	 * The method to submit the game results to the server
	 * @param total the total amount of treasure each user get from three cities
	 */
	public void submitToServer(int total) {
		CityMsg msg = new CityMsg(uuid , total);
		if (this.serverStub != null) {
			try {
				this.serverStub.receive(new OurDataPacket<CityMsg, IChatServer>(CityMsg.class, msg, this.clientStub));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Append msg to the global chatroom
	 * @param str the text to be appended to the global chatroom
	 */
	public void appendToGlobal(String str){
		viewAdapter.appendToGlobal(str);
	}
	
	
	
}
