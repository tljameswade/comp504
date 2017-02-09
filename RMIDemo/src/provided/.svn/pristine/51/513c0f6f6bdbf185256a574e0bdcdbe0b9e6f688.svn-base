package provided.hello;

import java.rmi.registry.Registry;

/**
 * Adapted from the <a href ="http://java.sun.com/javase/6/docs/technotes/guides/rmi/hello/hello-world.html">
 * Hello World example from Sun</a>.   
 * 
 *
 * This is the client program that accesses the remote IHello object  (the RMI "Server" instance) being presented via RMI on the server process.
 */
public class Client {
	
	/**
	 * RMI utilities for starting RMI and for getting the Registry
	 */
	private RMIUtilsBasic rmiUtils = new RMIUtilsBasic();

	/**
	 * constructor for the client
	 */
	public Client() {}

	/**
	 * Run the client
	 * @param host The the network name (e.g. "myserver.rice.edu")  or IP address of the RMI server. null mean use localhost
	 */
	public void run(String host){
		try {
			rmiUtils.startRMI();   // Start the RMI system
			
			Registry registry = rmiUtils.getRemoteRegistry(host);
			
			IHello helloStub = (IHello) registry.lookup("Hello");   // get the stub for the Hello object
			System.out.println("stub: " + helloStub); // The toString of a stub object will tell you the IP address of the RMI Server
			String response = helloStub.sayHello(); // Run a method on the Hello object
			System.out.println("response: " + response);  
			
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}		
	}

	/**
	 * Start a client up, which will get the Registry from the host specified by 
	 * args[0], get a stub to the object bound to "Hello" and call its sayHello() method.
	 * 
	 * @param args  The address of the server
	 */
	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];  // see if any command line arguments are supplied
		(new Client()).run(host);
	}
}
