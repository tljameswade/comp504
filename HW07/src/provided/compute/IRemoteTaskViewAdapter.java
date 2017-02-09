package provided.compute;

import java.rmi.*;

/**
 * A remote view adapter for a task.
 * @author swong
 *
 */
public interface IRemoteTaskViewAdapter extends Remote {
  /**
   * The port that the client will use to 
   * communicate with the IRemoteTaskViewAdapter object on the server.  
   * Note that this port must be opened for inbound traffic on the machine 
   * where the adapter actually resides (the Server).
   */
  public static final int BOUND_PORT_SERVER = 2101;
  
  /**
   * A secondary port that the server will use to 
   * communicate with the IRemoteTaskViewAdapter object on the client.  
   * Note that this port must be opened for inbound traffic on the machine 
   * where the adapter actually resides (the client).  
   * The client and server must use different ports in order to run on 
   * the same machine.
   */
  public static final int BOUND_PORT_CLIENT = 2102;
  
  /**
   * Append the given string to the remote view's display
   * @param s the string to display
   * @throws RemoteException thrown if a network error occurs
   */
  public void append(String s) throws RemoteException; 
}
