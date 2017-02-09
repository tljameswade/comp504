package common;

import provided.datapacket.DataPacket;

/**
 * We define a specific datapacket for our API implementation
 * @param <D> - A data type held by the host
 */
public class DataPacketGroupF<D> extends DataPacket<D, IChatServer>{

	/**
	 * The generated serial version ID
	 */
	private static final long serialVersionUID = -2352992924589305709L;

	/**
	 * The constructor of DataPacketGroupF
	 * @param c - An index for data type
	 * @param data - a data type held by the host
	 * @param sender - An IChatServer type
	 */
	public DataPacketGroupF(Class<D> c, D data, IChatServer sender) {
		super(c, data, sender);
	}

}
