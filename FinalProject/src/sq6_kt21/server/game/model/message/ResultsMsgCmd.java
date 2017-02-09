package sq6_kt21.server.game.model.message;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;

import provided.mixedData.MixedDataKey;
import sq6_kt21.server.game.model.GameModel;

/**
 * The command to process a results msg
 * @author suozhiqi
 *
 */
public class ResultsMsgCmd extends AOurDataPacketAlgoCmd<ResultsMsg, IChatServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4490214924428764443L;
	
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;

	@Override
	public Void apply(Class<?> index, OurDataPacket<ResultsMsg, IChatServer> host, Object... params) {
		// TODO Auto-generated method stub
		System.out.println("Showing results!");
		MixedDataKey<GameModel> gameModelKey = new MixedDataKey<GameModel>(GameModel.uuid, "GameModel", GameModel.class);
		@SuppressWarnings("unused")
		GameModel model = cmd2ModelAdpt.getFromLocalDict(gameModelKey);
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
