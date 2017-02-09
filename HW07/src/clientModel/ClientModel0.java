package clientModel;

import java.math.BigDecimal;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import clientModel.task.Fibo;
import clientModel.task.GetInfo0;
import clientModel.task.MaxPalinSubStr;
import clientModel.task.Pi20;
import clientModel.task.RandomArray;
import provided.compute.*;
import provided.rmiUtils.*;
import provided.util.IVoidLambda;

/**
 * The model of the client system.
 * 
 * @author Xun Luan, Suozhi Qi
 * 
 */
public class ClientModel0 {

	/**
	 * output command used to put multiple strings up onto the view.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {

		@Override
		public void apply(String... params) {
			for (String s : params) {
				view.append(s + "\n");
			}
		}
	};

	/**
	 * Factory for the Registry and other uses.
	 */
	IRMIUtils rmiUtils = new RMIUtils(outputCmd);

	/**
	 * Adapter to the view
	 */
	private IViewAdapter0 view;

	/**
	 * A reference to the proxy stub of the remote ICompute object.
	 */
	private ICompute comp;

	/**
	 * The adapter that connects all the way back to the remote system's view
	 * enabling this client to append messages to the server's view.
	 */
	private IRemoteTaskViewAdapter serverTA;

	/**
	 * The view adapter that the server can use to append messages to this
	 * client's view.
	 */
	private IRemoteTaskViewAdapter clientTA = new IRemoteTaskViewAdapter() {
		public void append(String s) {
			view.append("[Server] " + s);
		}
	};

	/**
	 * RMI stub for clientTA.  null=> clientTA needs to be exported, do not re-export otherwise. 
	 */
	private IRemoteTaskViewAdapter clientTAstub = null;

	/**
	 * Constructor for the class
	 * 
	 * @param iViewAdapter0
	 *            The adapter to the view.
	 */
	public ClientModel0(IViewAdapter0 iViewAdapter0) {
		this.view = iViewAdapter0;
	}

	/**
	 * Starts the model by setting all the required RMI system properties,
	 * starts up the class server and installs the security manager.
	 */
	public void start() {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);

		try {
			view.setRemoteHost(rmiUtils.getLocalAddress()); //TODO Is this stored somewhere?
		} catch (Exception e) {
			System.err.println("Error getting local address: " + e);
		}
	}

	/**
	 * Stops the client by shutting down the class server.
	 */
	public void stop() {
		System.out.println("ClientModel.stop(): client is quitting.");
		try {
			rmiUtils.stopRMI();

		} catch (Exception e) {
			System.err.println("ClientModel.stop(): Error stopping class server: " + e);
		}
		System.exit(0);
	}

	/**
	 * Connects to the given remote host and retrieves the stub to the ICompute object bound 
	 * to the ICompute.BOUND_NAME name in the remote Registry on port 
	 * IRMI_Defs.REGISTRY_PORT.  
	 * 
	 * @param remoteHost The IP address or host name of the remote server.
	 * @return  A status string on the connection.
	 */
	public String connectTo(String remoteHost) {
		try {
			view.append("Locating registry at " + remoteHost + "...\n");
			//Registry registry = registryFac.getRemoteRegistry(remoteHost);
			Registry registry = rmiUtils.getRemoteRegistry(remoteHost);
			view.append("Found registry: " + registry + "\n");
			comp = (ICompute) registry.lookup(ICompute.BOUND_NAME);
			view.append("Found remote Compute object: " + comp + "\n");
			if (null == clientTAstub) { // Don't re-export clientTA if already done.
				clientTAstub = (IRemoteTaskViewAdapter) UnicastRemoteObject.exportObject(clientTA,
						IRemoteTaskViewAdapter.BOUND_PORT_CLIENT);
			}
			serverTA = comp.setTextAdapter(clientTAstub);
			view.append("Got text adapter: " + serverTA + "\n");
			serverTA.append("Hello from the client!\n");
		} catch (Exception e) {
			view.append("Exception connecting to " + remoteHost + ": " + e + "\n");
			e.printStackTrace();
			return "No connection established!";
		}
		return "Connection to " + remoteHost + " established!";
	}

	/**
	 * Calculates PI to the given number of decimal places by 
	 * sending an entire Pi2 object to the remote ICompute object. 
	 * @param numDigits  The number of digits to compute PI to.
	 * @return A status string on the computation.
	 */
	public String calcPi(int numDigits) {
		String result = "";
		if (null == comp)
			return "No ICompute object yet!";
		Pi20 task = new Pi20(numDigits);

		try {
			BigDecimal pi = comp.executeTask(task);
			result = pi.toString();
		} catch (Exception e) {
			view.append("\nComputePi exception: " + e + "\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets the system information from the remote server by sending an entire GetInfo
	 * object to the remote server.   
	 * @param p   A string passed to the GetInfo object to use as it pleases.
	 * @return A status string on the GetInfo task.
	 */
	public String calcGetInfo(String p) {
		String result = "";
		if (null == comp)
			return "No ICompute object yet!";
		GetInfo0 task = new GetInfo0(p);

		try {
			result = comp.executeTask(task);
		} catch (Exception e) {
			view.append("\nCompute GetInfo exception: " + e + "\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Call the compute engine to execute the Fibo task.
	 * @param parseInt The parameter used by Fibo task.
	 * @return the return value of Fibo task.
	 */
	public String calcFibo(int parseInt) {
		// TODO Auto-generated method stub
		String result = "";
		if (null == comp)
			return "No ICompute object yet!";
		Fibo task = new Fibo(parseInt);

		try {
			Integer fibo = comp.executeTask(task);
			result = fibo.toString();
		} catch (Exception e) {
			view.append("\nComputeFibo exception: " + e + "\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Call the compute engine to execute the MaxPalinSubStr task.
	 * @param text The parameter used by MaxPalinSubStr task.
	 * @return The return value of MaxPalinSubStr task.
	 */
	public String longestPalin(String text) {
		// TODO Auto-generated method stub
		String result = "";
		if (null == comp)
			return "No ICompute object yet!";
		MaxPalinSubStr task = new MaxPalinSubStr(text);

		try {
			result = comp.executeTask(task);
		} catch (Exception e) {
			view.append("\nExecuteTask MaxPalinSubStr exception: " + e + "\n");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Generate an input size random Array of numbers from 0 to 100  and sort it
	 * @param size The size of generated array.
	 * @return The toString() value of array.
	 */
	public String randomArray(int size) {
		// TODO Auto-generated method stub
		String result = "";
		if (null == comp)
			return "No ICompute object yet!";
		RandomArray task = new RandomArray(size);

		try {
			result = comp.executeTask(task).toString();
		} catch (Exception e) {
			view.append("\nExecuteTask randomArray exception: " + e + "\n");
			e.printStackTrace();
		}
		return result;
	}

}
