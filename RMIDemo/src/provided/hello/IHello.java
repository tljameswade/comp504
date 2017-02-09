package provided.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Adapted from the <a href ="http://java.sun.com/javase/6/docs/technotes/guides/rmi/hello/hello-world.html">
 * "Hello World" example from Sun</a>.   
 * This is the interface that defines an object that will be presented out via RMI,
 * an RMI "Server" instance.
 * Note how this interface must extend Remote.
 */
public interface IHello extends Remote {
	/**
	 * This is a remote method that a client could call.   
	 * Note that it must throw RemoteException because of possible network and other errors.
	 * @return
	 * @throws RemoteException
	 */
	String sayHello() throws RemoteException;
}
