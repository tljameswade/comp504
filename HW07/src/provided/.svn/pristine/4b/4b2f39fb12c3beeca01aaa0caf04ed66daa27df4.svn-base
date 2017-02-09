package provided.compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A generalized compute engine object that will execute a given ITask object and return the result
 */
public interface ICompute extends Remote {
  /**
   * The name the ICompute object will be bound to in the RMI Registry
   */
  public static final String BOUND_NAME = "Compute";
  
  /**
   * The port that the client will use to communicate with the ICompute object
   * Note that this port must be opened of inbound traffic on the server machine
   */
  public static final int BOUND_PORT = 2100;
  
  /**
   * Execute the given ITask object and return the result
   * @param <T> The type of the return value from executing the given ITask object
   * @param t The ITask object to execute
   * @return The result from executing the ITask object
   */
  public <T> T executeTask(ITask<T> t) throws RemoteException;
  
  /**
   * Sets this object's IRemoteTaskViewAdapter so that it can print strings out on the correct target.
   * @param clientTVA  the task view adapter to for the server to use.
   * @return An adapter to the ICompute server's view.
   * @throws RemoteException thrown if a network error occurs. 
   */
  public IRemoteTaskViewAdapter setTextAdapter(IRemoteTaskViewAdapter clientTVA) throws RemoteException;
}
