package provided.engine.model;

import java.rmi.server.*;

import provided.compute.ICompute;
import provided.compute.ITask;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.IRemoteTaskViewAdapter;
import provided.util.IVoidLambda;


/**
 * A concrete implementation of an ICompute engine
 */
public class ComputeEngine implements ICompute {

	/**
	 * A command (lambda) that serves as an adapter to the view
	 */
	private IVoidLambda<String> viewCmd;

	/**
	 * A local task view adapter that delegates the to the main view adapter.
	 */
	private ILocalTaskViewAdapter taskView = new ILocalTaskViewAdapter() {
		public void append(String s) {
			viewCmd.apply(s);
		}
	};
	
	

	/**
	 * The constructor for the class
	 * 
	 * @param viewCmd
	 *            The adapter to the view
	 */
	public ComputeEngine(IVoidLambda<String> viewCmd) {
		this.viewCmd = viewCmd;
	}

	/**
	 * Executes the given task and returns the result.  This method is called by the remote client
	 * to execute its task. 
	 * 
	 * @param <T>
	 *            The type of the return value from executing the given ITask
	 *            object
	 * @param t
	 *            The ITask object to execute
	 * @return The result from executing the ITask object
	 */
	@Override
	public <T> T executeTask(ITask<T> t) {
		try {
			t.setTaskViewAdapter(taskView);
			viewCmd.apply("Executing task: " + t + "\n");
			T result = t.execute();
			viewCmd.apply("Task result = " + result + "\n");
			return result;
		} catch (Exception e) {
			viewCmd.apply("executeTask exception: " + e);
			return null;
		}
	}

	/**
	 * A remote view adapter connected to the client's view
	 */
	private IRemoteTaskViewAdapter clientTVA;

	/**
	 * Sets a remote view adapter for the server to use to display info on the client's
	 * view and returns a remote view adapter for the client to use to display 
	 * info on the server's view.
	 * @param clientTVA Remote task view adapter stub from the client
	 * @return A remote task view adapter stub for the client to use.
	 */
	@Override
	public IRemoteTaskViewAdapter setTextAdapter(IRemoteTaskViewAdapter clientTVA) {
		this.clientTVA = clientTVA;
		viewCmd.apply("Got text adapter: " + clientTVA + "\n");
		
		// Create a remote view adapter for the client to use to talk back to the server
		IRemoteTaskViewAdapter myTA = new IRemoteTaskViewAdapter() {
			public void append(String s) {
				viewCmd.apply("[Client] " + s);
			}
		};
		try {
			this.clientTVA.append("Hello from the server!\n");
			// Make the stub for the returned remote view adapter
			IRemoteTaskViewAdapter tvaStub = (IRemoteTaskViewAdapter) UnicastRemoteObject
					.exportObject(myTA, IRemoteTaskViewAdapter.BOUND_PORT_SERVER);
			return tvaStub;   // return the remote view adapter
		} catch (Exception e) {
			viewCmd.apply("ComputeEngine.setClient: Exception creating remote task view adapter: " + e);
			System.err.println("ComputeEngine.setClient: " + e);
			return null;
		}
	}

}
