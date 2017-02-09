package sq6_kt21.server.game.model;

import map.MapLayer;

/**
 * The game model to view adapter
 * @author Kun Tian
 *
 */
public interface IGameModel2ViewAdapter {

	public void show(MapLayer _layer);
	
	public void appendToGlobal(String str);

}
