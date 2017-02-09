package sq6_kt21.server.game.model.message;

import javax.swing.SwingUtilities;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import provided.mixedData.MixedDataKey;
import sq6_kt21.server.game.model.IServer2GameAdpt;

/**
 * The start game command class to process a start game msg
 * @author Kun Tian
 *
 */
public class StartGameCommand extends AOurDataPacketAlgoCmd<StartGame, IChatServer>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2136293897221988232L;
	
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;
	
	public StartGameCommand(ICmd2ModelAdapter _cmd2ModelAdpt) {
		cmd2ModelAdpt = _cmd2ModelAdpt;
	}
	
	@Override
	public Void apply(Class<?> index, OurDataPacket<StartGame, IChatServer> host, Object... params) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("In apply method of start game cmd");
				IServer2GameAdpt makeGame = host.getData().getFactory().makeGame();
				
				MixedDataKey<IServer2GameAdpt> key = new MixedDataKey<>(host.getData().getUUID(), "gameModel", IServer2GameAdpt.class);
				cmd2ModelAdpt.putIntoLocalDict(key, makeGame);
				//_modelAdapter.installPersistentComponent(gameGUI.contentPane, "Game View");
			}
		});
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}
	
}
