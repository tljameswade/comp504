package sq6_kt21.server.game.view;

import java.io.Serializable;
import gov.nasa.worldwind.geom.Position;

/**
 * The game view to model adapter
 * @author Suozhi Qi, Kun Tian
 *
 */
public interface IGameView2ModelAdapter extends Serializable {

	public void confirm(Position place);

	public void sendQuitMessage();

	public String getIP();

	public void sendToGlobal(String string);

	public void sendToTeam(String string);

//	public void setPlaceMark(Position currentPosition);

	public void submitResults(int total);

	public int getCount();

	public int getCities();
}
