package sq6_kt21.server.controller;

import java.awt.EventQueue;

import common.IChatServer;
import sq6_kt21.server.game.model.IServer2GameAdpt;
import sq6_kt21.server.game.model.message.CityMsg;
import sq6_kt21.server.model.IGame2ServerAdpt;
import sq6_kt21.server.model.IServerModel2ViewAdpt;
import sq6_kt21.server.model.ServerModel;
import sq6_kt21.server.view.IServerView2ModelAdpt;
import sq6_kt21.server.view.ServerView;

/**
 * The controller of the server
 * @author Kun Tian, Suozhi Qi
 *
 */
public class ServerController {
	
	private ServerModel serverModel;
	
	private ServerView serverView;
	
	/**
	 * The server constructor
	 */
	public ServerController(){
		serverView = new ServerView(new IServerView2ModelAdpt() {
			
			@Override
			public void startGameView() {
				serverModel.startGameView();
				
			}
			
			@Override
			public void broadcast(String msg) {
				serverModel.broadcast(msg);
				
			}
			
			@Override
			public void assignTeams() {
				serverModel.assignTeams();
				
			}

			@Override
			public void announceResults() {
//				serverModel.announceResults();
			}
		});
		
		serverModel = new ServerModel(new IServerModel2ViewAdpt() {
			
			@Override
			public void appendView(String msg) {
				serverView.appendView(msg);
				
			}

			@Override
			public void setRemoteHost(String hostAddress) {
				serverView.setGUITitle("IP:" + hostAddress);
				
			}
		});
		
	}
	
	public ServerController(IGame2ServerAdpt[] ret) {
		this();
		ret[0] = new IGame2ServerAdpt() {


			@Override
			public void processTreasure(int data, IChatServer sender) {
				serverModel.processTreasure(data, sender);
			}
			
		};
	}
	
	/**
	 * start the whole chatApp
	 */
	public void start() {
		serverModel.start();
		serverView.start();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				(new ServerController()).start();
			}
		});
	}
}
