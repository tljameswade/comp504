package common;

import provided.datapacket.ADataPacketAlgoCmd;

/**
 * Here we define a more specific command type for our API implementation
 * @param <D> - A generic type for data type held by the host
 */
public abstract class ADataPacketAlgoCmdGroupF<D> extends ADataPacketAlgoCmd<String, D, IChatServer, ICmd2ModelAdapter,  DataPacketGroupF<D>>{

	/**
	 * A generated serial version ID
	 */
	private static final long serialVersionUID = 8389658380261933109L;

}
