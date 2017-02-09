package provided.hello;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * Adapted from the <a href = "http://java.sun.com/javase/6/docs/technotes/guides/rmi/hello/hello-world.html">
 * Hello World" example from Sun</a>.  
 * 
 * This class is the process that is hosting the RMI system and serving out 
 * RMI "Server" objects.    This class is NOT an RMI Server instance!!
 * 
 * SERVER MACHINE MUST HAVE REGISTRY_PORT AND SERVER_PORT (see below and RMIUtilsBasic) OPEN
 * THROUGH FIREWALL!!
 */
public class ServerProcess  {

	/**
	 * Constructor for server
	 */
	public ServerProcess() {
	}


	/**
	 * The port used by the bound Hello instance
	 */
	public static final int SERVER_PORT = 2101;

	private RMIUtilsBasic rmiUtils = new RMIUtilsBasic();

	/**
	 * Reference to the Registry
	 */
	private Registry registry;

	/**
	 * The RMI Server implementation -- the actual object that will do the processing.
	 */
	private IHello helloServer = new HelloImpl();
	
	/**
	 * Run the Server, which will find the RMI Registry, creating it if
	 * necessary, and bind an instance of the Server to the "Hello" name in the
	 * Registry. The server then stays in an infinite loop waiting for someone
	 * to use the bound server.
	 */
	public void run() {
		try {
			// Start the RMI system and get the local Registry, making it if necessary.
			rmiUtils.startRMI();
			registry = rmiUtils.getLocalRegistry();
		}
		catch(Exception e) {
			System.err.println("Exception while intializing RMI: \n" + e);
			e.printStackTrace();
			System.exit(-1); // exit the program.
		}

		try {

			// Create a UnicastRemoteObject stub from the RMI Server implementation to be sent to the clients.
			IHello helloStub = (IHello) UnicastRemoteObject.exportObject(helloServer, SERVER_PORT);

			// Bind the remote object's stub in the registry at the specified
			// port use rebind instead of bind so the program can be run
			// multiple times with the same registry
			registry.rebind("Hello", helloStub);

			System.err.println("Server ready");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			System.exit(-1); // exit the program.
		}

		// The following loop is just to keep the program from terminating
		// This loop will not be necessary once the server has a GUI
		while (true) {
			try {
				// Sleep this thread for 100 ms intervals to keep it from
				// consuming resources
				Thread.sleep(100);
			} 
			catch (Exception e) {
				System.err.println("Exception during sleeping: "+e);
				// Continue on.  Can kill the process from the console if needed.
			}
		}
	}

	
	
	/**
	 * Start up the server
	 * 
	 * @param args
	 *            Not used.
	 */
	public static void main(String args[]) {
		(new ServerProcess()).run();

	}
}
