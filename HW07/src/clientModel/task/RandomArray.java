package clientModel.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;

/**
 * A task that generate a series of numbers and sort them.
 * @author Xun Luan, Suozhi Qi
 *
 */
public class RandomArray implements ITask<ArrayList<Integer>> {

	/**
	 * Serialversion UID for well-defined serialization
	 */
	private static final long serialVersionUID = 8449154476695173007L;

	/**
	 * Adapter to the local view.  Marked "transient" so that it is not serialized
	 * and instead is reattached at the destination (the server).  
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * The size of generated array.
	 */
	private final int size;

	/**
	 * Constructor.
	 * @param size The size of generated array. 
	 */
	public RandomArray(int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		taskView.append("RandomArray Constructing...\n");
	}

	/**
	 * Generate array with numbers from 0 to 100 and sort this array
	 * @return the arraylist of integers random chosen followed by sorting
	 */
	@Override
	public ArrayList<Integer> execute() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Server: Executing client's Random Array task...");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		if (size <= 0)
			return null;
		else {
			for (int i = 0; i < size; i++) {
				int r = (int) (Math.random() * 100);
				arrayList.add(r);
			}
			Collections.sort(arrayList);
			taskView.append("The sorted array with randomly generated number is: ");
			taskView.append(arrayList.toString() + "\n");
			return arrayList;
		}
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
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject(); // Deserialize the non-transient data
		taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER; // re-initialize the
															// transient field
	}

	/**
	 * Sets the task view adapter to a new value.  Used by the server to attach
	 * the task to its view.
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}
}
