package provided.engine.model;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import provided.compute.*;
import provided.rmiUtils.*;
//import provided.rmiUtils.classServer.ClassFileServer;
import provided.util.IVoidLambda;


public class EngineModel {
	/**
	 * The adapter to the view
	 */
	private IViewAdapter view;

	/**
	 * The RMI Registry
	 */
	private Registry registry;
	
	
	/**
	 * A command used as a wrapper around the view adapter for the IRMIUtils and the ComputeEngine.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String> (){
		public void apply(String... strs){
			for(String s: strs)view.append(s);
		}
	};
	
	/**
	 * Utility object used to get the Registry
	 */
	private IRMIUtils rmiUtils = new RMIUtils(outputCmd);


	/**
	 * The constructor for the class
	 * 
	 * @param view
	 *            The adapter to the view
	 */
	public EngineModel(IViewAdapter view) {
		this.view = view;
	}

	private ICompute engine; // Need to keep persistent reference to engine so it doesn't get garbage collected.
	/**
	 * Start the server by setting the necessary RMI system parameters, starting the security manager, 
	 * locating the local Registry and binding an instance of the ComputeEngine to it.  
	 * Also starts the class file server to enable remote dynamic class loading.
	 */
	public void start() {
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);

		try {
			// String name = "Compute";
			engine = new ComputeEngine(outputCmd);
			view.append("Instantiated new Compute engine: "+engine+"\n");
			// Use this technique rather than the simpler "registry.rebind(name, engine);"
			// because it enables us to specify a port number so we can open that port on the firewall
			ICompute stub =(ICompute) UnicastRemoteObject.exportObject(engine, IRemoteTaskViewAdapter.BOUND_PORT_SERVER);
			view.append("Looking for registry..."+"\n");

			registry = rmiUtils.getLocalRegistry();

			view.append("Found registry: "+ registry+ "\n");
			registry.rebind(ICompute.BOUND_NAME, stub);
			view.append("ComputeEngine bound to "+ICompute.BOUND_NAME+"\n");
		} 
		catch (Exception e) {
			System.err.println("ComputeEngine exception:"+"\n");
			e.printStackTrace();
			System.exit(-1);
		}

		view.append("Waiting..."+"\n");

	}

	/**
	 * Stops the EngineModel by unbinding the ComputeEngine from the Registry 
	 * and stopping class file server. 
	 */
	public void stop() {
		try {
			registry.unbind(ICompute.BOUND_NAME);
			System.out.println("EngineController: " + ICompute.BOUND_NAME
					+ " has been unbound.");
			
			rmiUtils.stopRMI();
			
			System.exit(0);
		} catch (Exception e) {
			System.err.println("EngineController: Error unbinding "
					+ ICompute.BOUND_NAME + ":\n" + e);
			System.exit(-1);
		}
	}
	
}
