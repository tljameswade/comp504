package common;

import provided.datapacket.ADataPacketAlgoCmd;

/**
 * Here we define a more specific command type for our API implementation
 * @param <D> - A generic type for data type held by the host
 */
public abstract class AOurDataPacketAlgoCmd<D,S> extends ADataPacketAlgoCmd<Void, D, Object, ICmd2ModelAdapter, OurDataPacket<D,S>>{

	private static final long serialVersionUID = 3668264773027949186L;

}
