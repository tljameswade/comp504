package common;

import provided.datapacket.DataPacket;

/**
 * 
 * @author calfaschen
 *
 * @param <D>
 * @param <S> - Currently there is no msg should be sent by IUser. S should always be IChatServer.
 */
public class OurDataPacket<D,S> extends DataPacket<D,S> {

	private static final long serialVersionUID = -286404109403988099L;
	
	/**
	 * Constructor of OurDataPacket
	 * @param c - An index for msg type
	 * @param data - msg
	 * @param sender - IChatServer
	 */
	public OurDataPacket(Class<D> c, D data, S sender) {
		super(c, data, sender);
	}

}
