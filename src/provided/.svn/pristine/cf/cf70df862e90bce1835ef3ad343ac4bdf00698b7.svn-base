package provided.datapacket;




import provided.extvisitor.*;



/**
 * Abstract data packet that defines the use of a Class object as the index type.  
 * The type of data held by the data packet defines its type and thus what case it
 * calls on its processing visitors.
 * Specifies use of Class&lt;?&gt; type for host ID
 * @author Stephen Wong (c) 2014
 */
public abstract class ADataPacket extends AExtVisitorHost<Class<?>, ADataPacket> {
	
	/**
	 * Version number for serialization
	 */
	private static final long serialVersionUID = 5990493490087888131L;
	
	/**
	 * The stub for the sender of the datapacket
	 */


	/**
	 * Constructor for this abstract superclass
	 * @param c A Class object to be used as the index value defining this type of data packet.
	 */
	public ADataPacket(Class<?> c){
		super(c);
	}
	
	
}


