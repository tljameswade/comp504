package provided.datapacket;

import provided.extvisitor.*;

/**
 * Concrete visitor for processing an abstract data packet.
 * For convenience and increased type safety, the commands used by this visitor should be subclasses of
 * ADataPacketAlgoCmd&lt;R, D, P&gt;, where D is the type of the data that particular command processes,
 * i.e. the defining type of its associated DataPacket&lt;D&gt; host.
 * @author Stephen Wong (c) 2010
 *
 * @param <R>  The return type of the visitor
 * @param <P>  The vararg input parameter type of the visitor * ----------------------------------------------
 * Restricts visitor to hosts of type ADataPacket 
 */
public class DataPacketAlgo<R,P> extends AExtVisitor<R, Class<?>, P, ADataPacket> {

	/**
	 * Version number for serialization
	 */
	private static final long serialVersionUID = 2045919428614770686L;

	/**
	 * Constructor for the class.
	 * @param defaultCmd  The default command to be used.  See the main class documentation for usage suggestions.
	 * @param <A> The type of the adapter for the command to the local model.
	 * @param <H> The specific type of ADataPacket subclass that is being used
	 */
	public <A, H extends ADataPacket> DataPacketAlgo(ADataPacketAlgoCmd<R, ?, P, A, H> defaultCmd){
		super(defaultCmd);
	}
}
