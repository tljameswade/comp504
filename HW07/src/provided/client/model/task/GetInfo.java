package provided.client.model.task;

import java.rmi.RemoteException;

import java.io.*;

import provided.compute.ITask;
import provided.compute.ILocalTaskViewAdapter;

/**
 * Task that gets the server's system properties plus demonstrates that it can carry 
 * internal data to a remote system when the entire object is serialized and sent
 * over. 
 * @author swong
 *
 */
public class GetInfo implements ITask<String> {

	/**
	 * Serialversion UID for well-defined serialization
	 */
	private static final long serialVersionUID = -3690660506537207490L;
	
    /**
     * Adapter to the local view.  Marked "transient" so that it is not serialized
     * and instead is reattached at the destination (the server).  
     */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * Input string given to the constructor
	 */
	private String input = "";

	/**
	 * An array carried along to prove that internal data is transmitted too.
	 */
	private double[] dArray = new double[] { 1.2, 2.3, 4.5 };

	/**
	 * Constructor for the class.
	 * 
	 * @param input  A string to carry around.
	 */
	public GetInfo(String input) {
		this.input = input;
	}

	/**
	 * Display the internal string on the server's console.
	 * Get the server's system properties and calculate the sum of the internal data.  
	 * @return the server's system properties and the sum of the internal array as a string.
	 */
	@Override
	public String execute() throws RemoteException {
		taskView.append("GetInfo task called with input = " + input + "\n");
		double sum = 0.0;
		for (double x : dArray)
			sum += x;
		return System.getProperties().toString() + "\n Sum = " + sum;
	}

	/**
	 * Reinitializes transient fields upon deserialization. See the <a href=
	 * "http://download.oracle.com/javase/6/docs/api/java/io/Serializable.html">
	 * Serializable</a> marker interface docs.
	 * taskView is set to a default value for now (ILocalTaskViewAdapter.DEFAULT_ADAPTER).
	 * 
	 * @param stream
	 *            The object stream with the serialized data
	 */
	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject(); // Deserialize the non-transient data
		taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER; // re-initialize the
															// transient field
	}

	/**
	 * Sets the task view adapter to a new value. Tests connection by sending a
	 * string representation of the dArray structure.  Called by the server to 
	 * attach the task to its view.
	 * 
	 * @param viewAdapter
	 *            the adapter to the view.
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		this.taskView = viewAdapter;
		viewAdapter.append("GetInfo installed!\n");
		String s = "";
		for (double x : dArray)
			s += x + " ";
		System.out.println("GetInfo.setTaskViewAdapter called.\ndArray = " + s
				+ "\n");
	}

}
