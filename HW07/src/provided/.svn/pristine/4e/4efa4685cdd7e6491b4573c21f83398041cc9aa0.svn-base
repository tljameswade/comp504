package provided.compute;

import java.io.Serializable;
import java.rmi.*;
/**
 * A abstract task to be run on the computer engine
 * @param <T> The type of the returned result of the task
 */
public interface ITask<T> extends Serializable {
  /**
   * Executes the task and returns a result
   * @return The result of executing the task.
   * @throws RemoteException  thrown when a network error occurs.
   */
  public T execute() throws RemoteException;
  
  /**
   * Sets the adapter to the view properly for this object.
   * @param viewAdapter an adapter to the view.
   */
  public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter);
}
