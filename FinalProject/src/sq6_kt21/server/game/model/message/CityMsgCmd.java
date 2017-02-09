package sq6_kt21.server.game.model.message;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import provided.mixedData.MixedDataKey;

import sq6_kt21.server.model.IGame2ServerAdpt;



/**
 * The command class to process cityMsg
 * @author suozhiqi
 *
 */
public class CityMsgCmd extends AOurDataPacketAlgoCmd<CityMsg, IChatServer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7993010233925284798L;
	
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;
	
	/**
	 * The cityMsg command constructor
	 * @param cmd2ModelAdpt the local command to model adapter
	 */
	public CityMsgCmd(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<CityMsg, IChatServer> host, Object... params) {
		// TODO Auto-generated method stub
		System.out.println("Processing city list!");
		MixedDataKey<IGame2ServerAdpt> key = new MixedDataKey<>(host.getData().getId(), "gameModel", IGame2ServerAdpt.class);
		IGame2ServerAdpt model = cmd2ModelAdpt.getFromLocalDict(key);
		System.out.println(model);
		System.out.println(host.getData());
		model.processTreasure(host.getData().getTotal(), host.getSender());
		System.out.println(host.getData().getTotal());
//		model.processCityList(host.getData().getCityList(), host.getSender());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
