package sq6_kt21.server.view;

/**
 * Interface for server view 2 server model
 * @author ktian
 *
 */
public interface IServerView2ModelAdpt {
	
	public void startGameView();
	
	public void broadcast(String msg);
	
	public void assignTeams();
	
	public void announceResults();
	
}
