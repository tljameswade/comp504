package sq6_kt21.server.game.model;

import java.io.Serializable;

import common.IChatServer;
import common.IChatroom;
import provided.datapacket.DataPacketAlgo;
import sq6_kt21.server.game.controller.GameController;

/**
 * The factory to start the game
 * @author Kun Tian
 *
 */
public class StartGameFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7526259125938007451L;

	private IChatServer serverStub;
	
	private DataPacketAlgo<Void, Object> dataPacketAlgo;
	
	/**
	 * The constructor
	 * @param server the server stub
	 * @param globalChatroom the global chatroom
	 * @param visitor the datapacket algo dictionary
	 */
	public StartGameFactory(IChatServer server, IChatroom globalChatroom, DataPacketAlgo<Void, Object> visitor) {
		this.serverStub = server;
		this.dataPacketAlgo = visitor;
	}
	
//	public GameGUI makeGame() {
//		GameController ctr = new GameController();
//		ctr.set(this.serverStub, this.dataPacketAlgo);
//		return ctr.start();
//	}
	
	/**
	 * The make game method
	 * @return
	 */
	public IServer2GameAdpt makeGame() {
		IServer2GameAdpt[] ret = new IServer2GameAdpt[1];
//		IServer2GameAdpt ret = null;
		GameController ctr = new GameController(ret);
		
		ctr.set(this.serverStub, this.dataPacketAlgo);
		ctr.start();
//		return ctr.start();
		return ret[0];
	}
}
