/**
 * The following is example test code to show the usage of datapackets and associated visitors and commands.
 * At the bottom of this file are examples of the types of specialized datapacket and visitor commands that can be defined 
 * by an API to insure consistency and type-safety.
 */
package provided.datapacket.test;

import provided.datapacket.*;

import junit.framework.TestCase;
import java.util.Vector;

/**
 * JUnit tests for the datapacket package
 * @author Stephen Wong (c) 2010
 *
 */
public class DataPacketTest extends TestCase {

	ISender sender = new ISender(){};
	
	/**
	 * Tests of the datapacket
	 * sender stub is not tested
	 */
	public void testDataPacket() {
		ADataPacket ds = new MyDataPacket<String>(String.class, "Stuff", sender);
		ADataPacket di = new MyDataPacket<Integer>(Integer.class,  42, sender);
		ADataPacket dd = new MyDataPacket<Double>(Double.class, 3.1415926, sender);

		// make the vector of data packets
		VDataPacket vdp = new VDataPacket();
		vdp.add(ds);
		vdp.add(di);
		vdp.add(dd);
		
		// make the composite data packet
		ADataPacket vd = new MyDataPacket<VDataPacket>(VDataPacket.class, vdp, sender);
		
		System.out.println("class = "+ vd.getClass());
		
		final DataPacketAlgo<String, String> algo = new DataPacketAlgo<String, String>(new AMyDataPacketAlgoCmd<String, Object, String>(){

			private static final long serialVersionUID = -3838770346677745909L;

			@Override
			public String apply(Class<?> id, MyDataPacket<Object> host, String... params) {
				Object x = host.getData();
				return "Default case called. data = "+ x;
			}	

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				// not used.
			}
		});
				
		
		algo.setCmd(String.class, new AMyDataPacketAlgoCmd<String, String, String>(){

			private static final long serialVersionUID = 7417327345957770087L;

			@Override
			public String apply(Class<?> id, MyDataPacket<String> host, String... params) {
				String x = host.getData();
				return "String case called. data = "+ x;
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				// not used.
			}
		});
		
		algo.setCmd(Integer.class, new AMyDataPacketAlgoCmd<String, Integer, String>(){

			private static final long serialVersionUID = 5948981304362218691L;

			@Override
			public String apply(Class<?> id, MyDataPacket<Integer> host, String... params) {
				Integer x = host.getData();
				return "Integer case called. data = "+x;
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				// not used.
			}
		});
		
//		algo.setCmd(VDataPacket.class, VDataPacket.makeMapCmd(algo));
		
		algo.setCmd(VDataPacket.class, new AMyDataPacketAlgoCmd<String, VDataPacket, String>(){

			private static final long serialVersionUID = -5626422695894697578L;

			// Note that since the visitor itself does not know the specific datapacket type the commands coerce their hosts into, the static method, VDataPacket.makeMapCmd(algo) 
			// cannot return an AMyDataPacketAlgoCmd object.  Instead, it returns the more general ADataPacketAlgoCmd which is still compatible with the apply() method
			// input parameters.
			private ADataPacketAlgoCmd<Vector<String>, VDataPacket, String, ICmd2ModelAdapter, DataPacket<VDataPacket, ISender>> cmd = VDataPacket.makeMapCmd(algo);
			
			
			@Override
			public String apply(Class<?> id, MyDataPacket<VDataPacket> host, String... params) {
				Vector<String> results = cmd.apply(id, host, params);
				String result = "{\n";
				for(String s: results ){
					result += "  "+ s + "\n";
				}
				result += "}";
				return "Composite case called. result = "+result;
			}
			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				// not used.
			}
		});
		
		String s = ds.execute(algo);
		assertEquals("ds.execute(algo)","String case called. data = Stuff", s);
		System.out.println("ds.execute(algo) = "+s);
		s = di.execute(algo);
		assertEquals("di.execute(algo)","Integer case called. data = 42", s);
		System.out.println("di.execute(algo) = "+s);
		s = dd.execute(algo);
		assertEquals("dd.execute(algo)","Default case called. data = 3.1415926", s);
		System.out.println("dd.execute(algo) = "+s);
		s = vd.execute(algo);
		assertEquals("vd.execute(algo)", "Composite case called. result = {\n  String case called. data = Stuff\n  Integer case called. data = 42\n  Default case called. data = 3.1415926\n}", s);
		System.out.println("vd.execute(algo) = "+s);
		
		
	}

}

/***********************************************************************************************
 * THE FOLLOWING CLASSES WOULD TYPICALLY BE DEFINED BY THE A COMMON API
 ***********************************************************************************************/

/**
 * Dummy adapter to local model.   Never used explicitly above but needed for typing.
 * @author Stephen Wong
 *
 */
interface ICmd2ModelAdapter {
	// Normally has the methods the API specifies that the adapter to the local model must have.
}


/**
 * Dummy sender.   Never used explicitly above but needed for typing.
 * @author Stephen Wong
 *
 */
interface ISender {
	// Normally has the methods that the API specifies a sender must have.
}

/**
 * Specialized data packet that uses ISender type senders
 * @author Stephen Wong
 *
 * @param <D>  The type of data held by the data packet
 */
class MyDataPacket<D> extends DataPacket<D, ISender> {

	private static final long serialVersionUID = 6919860798916028881L;

	public MyDataPacket(Class<D> c, D data, ISender sender) {
		super(c, data, sender);
	}

}


/**
 * Specialized data packet processing command that uses MyDataPacket&lt;D&gt; and ICmd2ModelAdapters.
 * @author Stephen Wong
 *
 * @param <R>  The return type of the command
 * @param <D>  The data in the data packet
 * @param <P>  The parameter type
 */
abstract class AMyDataPacketAlgoCmd<R, D, P> extends ADataPacketAlgoCmd<R, D, P, ICmd2ModelAdapter, MyDataPacket<D>> {

	private static final long serialVersionUID = 3493108470390456052L;
	
}




