package sq6_kt21.server.game.controller;

import java.awt.EventQueue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.IChatServer;
import common.IUser;
import gov.nasa.worldwind.geom.Position;
import map.MapLayer;
import provided.datapacket.DataPacketAlgo;
import sq6_kt21.server.game.model.GameModel;
import sq6_kt21.server.game.model.IGameModel2ViewAdapter;
import sq6_kt21.server.game.model.IServer2GameAdpt;
import sq6_kt21.server.game.view.GameGUI;
import sq6_kt21.server.game.view.IGameView2ModelAdapter;

/**
 * The game controller class
 * @author Kun Tian, Suozhi Qi
 *
 */
public class GameController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5517732755732212625L;
	
	private GameModel model;
	private GameGUI view;
	
	/**
	 * The game controller constructor
	 */
	public GameController() {
		model = new GameModel(new IGameModel2ViewAdapter() {

			@Override
			public void show(MapLayer _layer) {
				view.addMapLayer(_layer);
			}

			@Override
			public void appendToGlobal(String str) {
				view.appendToGlobal(str);
			}
			
		});
		
		view = new GameGUI(new IGameView2ModelAdapter() {

			@Override
			public void confirm(Position place) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void sendQuitMessage() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getIP() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void sendToGlobal(String string) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void sendToTeam(String string) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void submitResults(int total) {
				// TODO Auto-generated method stub
				model.submitToServer(total);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return model.countSelected();
			}

			@Override
			public int getCities() {
				// TODO Auto-generated method stub
				return model.getSelected();
			}
			
		});
	}
	
	public GameController(IServer2GameAdpt[] ret) {
		this();
		ret[0] = new IServer2GameAdpt() {
			@Override
			public void sentTextToGame(String text) {
				model.appendToGlobal(text);
			}
		};
	}

	public GameGUI start() {
		view.start();
		model.start();
		return view;
	}
	
	public void set(IChatServer server, DataPacketAlgo<Void, Object> visitor) {
		model.setProperties(server, visitor);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					GameController controller = new GameController(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
