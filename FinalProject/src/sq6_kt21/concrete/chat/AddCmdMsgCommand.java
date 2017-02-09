package sq6_kt21.concrete.chat;

import common.AOurDataPacketAlgoCmd;
import common.IChatServer;
import common.ICmd2ModelAdapter;
import common.OurDataPacket;
import common.msg.chat.IAddCmdMsg;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.IMixedDataDictionary;
import provided.mixedData.MixedDataKey;

/**
 * The command to process add msg
 * @author Kun Tian, Suozhi Qi
 *
 */
public class AddCmdMsgCommand extends AOurDataPacketAlgoCmd<IAddCmdMsg, IChatServer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466416543653084409L;
	transient private ICmd2ModelAdapter cmd2ModelAdpt = null;
	private transient DataPacketAlgo<Void, Object> dataPacketAlgo;
	private transient IMixedDataDictionary dictionary;

	/**
	 * The add command msg command constructor
	 * @param _cmd2ModelAdpt The local command to model adapter
	 * @param _dataPacketAlgo the local data packet algorithm set
	 * @param _dict the local mixed data dictionary
	 */
	public AddCmdMsgCommand(ICmd2ModelAdapter _cmd2ModelAdpt, DataPacketAlgo<Void, Object> _dataPacketAlgo, IMixedDataDictionary _dict) {
		cmd2ModelAdpt = _cmd2ModelAdpt;
		dataPacketAlgo = _dataPacketAlgo;
		dictionary = _dict;
	}


	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public Void apply(Class<?> index, OurDataPacket<IAddCmdMsg, IChatServer> host, Object... params) {
		AOurDataPacketAlgoCmd<?, IChatServer> newCmd = host.getData().getCmd();
		newCmd.setCmd2ModelAdpt(this.cmd2ModelAdpt);
		this.dataPacketAlgo.setCmd(host.getData().getClassIdx(), newCmd);
		MixedDataKey<Object> unknowMsgKey = new MixedDataKey<Object>(host.getData().getUUID(), "", Object.class);
		@SuppressWarnings("unchecked")
		OurDataPacket<?, IChatServer> dataPacket = (OurDataPacket<?, IChatServer>)dictionary.get(unknowMsgKey);
		dataPacket.execute(this.dataPacketAlgo);
		return null;
	}

}
