package provided.datapacket;




/**
 * Concrete data packet that holds a generic type of data.
 * Adds internal data content of type T and host ID type Class&lt;T&gt;
 * @author Stephen Wong (c) 2010
 *
 * @param <T>  The type of the data being held.  T must be Serializable. 
 * @param <S>  The type of the sender object to which datapackets can be sent back to.
 */
public class DataPacket<T, S> extends ADataPacket{

	/**
	 * Version number for serialization
	 */
	private static final long serialVersionUID = -4384652128226661822L;
	
	/**
	 * The data being held
	 */
	private T data;
	
	/**
	 * The sender of this data packet
	 */
	private S sender;
	
	/**
	 * The constructor for a data packet. <br>
	 * Usage: <br>
	 * <pre>
	 * ADataPacket dp = new DataPacket&lt;MyData&gt;(MyData.class, aMyData)
	 * </pre>
	 * @param c Must be T.class where T is the data type being used.
	 * @param data  The data to be held in the data packet
	 * @param sender The sender of this data packet
	 */
	public DataPacket(Class<T> c, T data, S sender){
		super(c);
		this.data = data;
		this.sender = sender;
	}
	
	/**
	 * Accessor for the held data
	 * @return  The data being held
	 */
	public T getData(){
		return data;
	}	
	
	/**
	 * Accessor for this data packet's sender
	 * @return the sender
	 */
	public S getSender() {
		return sender;
	}
	
}
