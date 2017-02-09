package sq6_kt21.server.game.model.message;

import java.util.UUID;

import common.msg.IChatMsg;

/**
 * The team msg class
 * @author suozhiqi
 *
 */
public class TeamMsg implements IChatMsg{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4248209715585413986L;

	private String msg;
	
	private UUID uuid;
	
	public TeamMsg(String str, UUID id){
		msg = str;
		uuid = id;
	}
	
	public String getTeamMsg(){
		return msg;
	}
	
	public UUID getID(){
		return uuid;
	}
	
}
