package sq6_kt21.server.game.model.message;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import provided.mixedData.MixedDataKey;
import sq6_kt21.server.game.model.IServer2GameAdpt;

/**
 * The team msg command to process a team msg
 * @author suozhiqi
 *
 */
public class TeamMsgCmd extends AOurDataPacketAlgoCmd<TeamMsg, IChatServer> {

	private static final long serialVersionUID = -6481044008721511961L;
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;
	
	public TeamMsgCmd(ICmd2ModelAdapter cmd2ModelAdapter) {
		cmd2ModelAdpt = cmd2ModelAdapter;
	}
	
	@Override
	public Void apply(Class<?> index, OurDataPacket<TeamMsg, IChatServer> host, Object... params) {
		System.out.println("[teamMsgmd] apply");
		MixedDataKey<IServer2GameAdpt> key = new MixedDataKey<>(host.getData().getID(), "gameModel", IServer2GameAdpt.class);
		IServer2GameAdpt model = cmd2ModelAdpt.getFromLocalDict(key);
		System.out.println(model);
		System.out.println(host.getData());
		model.sentTextToGame(host.getData().getTeamMsg());
//		model.appendToGlobal(host.getData().getTeamMsg());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
		
	}

}
