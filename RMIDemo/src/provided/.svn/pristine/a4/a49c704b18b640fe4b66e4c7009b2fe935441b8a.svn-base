package provided.hello;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;


/**
 * A stripped-down version of the RMIUtils utilities used in later assignments.  These utilities make for easier and
 * more robust RMI initializations.
 * 
 * @author swong
 * 
 */
public class RMIUtilsBasic  {
	
	/**
	 * Port used for Registry.  Port 1099 is standard port.
	 * Non-standard port for RMI Registry required if used with DrJava
	 * Note that this port must be opened of inbound traffic on the server machine
	 */
	public static final int REGISTRY_PORT = 2099;


	/**
	 * Constructor for the class. 
	 */
	public RMIUtilsBasic() {
	}


	/**
	 * Returns the Registry on the local machine on port
	 * REGISTRY_PORT, as a server would require. Creates the Registry
	 * if it isn't already running.   Throws an exception if unable to create
	 * or find a Registry.
	 * 
	 * @return the Registry
	 * @throws RemoteException if no Registry could be made or found.
	 */
	public Registry getLocalRegistry() throws RemoteException {

		try {
			// First try to create a registry
			return LocateRegistry.createRegistry(REGISTRY_PORT);
		} catch (RemoteException e) {
			System.out.println("RMIUtilsBasic: Could not create registry: " + e + "\n"+
					"Attempting to locate existing registry...\n");
			try {
				// Creating a new registry will fail if it already exists, so
				// try to find it.
				Registry registry = LocateRegistry
						.getRegistry(REGISTRY_PORT);
				System.out.println("RMIUtilsBasic.getLocalRegistry: Success! Found Registry: "
						+ registry+"\n");
				return registry;
			} catch (RemoteException e2) {
				System.err.println("RMIUtils.getLocalRegistry: Could not get registry on port "
						+ REGISTRY_PORT + ". \n" + e);
				throw(e2);
			}
		}
	}

	/**
	 * Returns the Registry on the given machine on port
	 * IRMI_Defs.REGISTRY_PORT, as a client would require.
	 * 
	 * @param host
	 *            the IP address or host name of the remote machine.  If null, will get registry on local host.
	 * @return The remote Registry or null if it could not be located.
	 */
	public Registry getRemoteRegistry(String host) {

		try {
			Registry registry = LocateRegistry.getRegistry(host,
					REGISTRY_PORT);
			System.out.println("RMIUtilsBasic.getRemoteRegistry: Success! Found Registry: "
					+ registry+"\n");
			return registry;
		} catch (RemoteException e) {
			System.err.println("RMIUtilsBasic.getRemoteRegistry: Could not get registry at "
					+ host + ":" + REGISTRY_PORT + ". \n" + e);
			return null;
		}
	}

	/**
	 * This method is designed to reliably return the actual local IP address
	 * across multiple platforms, particularly Linux. This method is a
	 * replacement for "java.net.InetAddress.getLocalHost().getHostAddress()"
	 * which will return the loopback address in Linux, not the actual IP
	 * address. Also, Java tends to report many virtual network adapters as 
	 * "non-virtual".  This method only returns IPv4 addresses, not IPv6 addresses,
	 * which are non-loopback, operational and non-virtual, as reported by Java.   
	 * By default, privateAddrOnly is set to true, so only private IP addresses are 
	 * detected.   If privateAddr = false (having used the auxiliary constructor)
	 * then all valid addresses are detected. If multiple potentially valid addresses are 
	 * detected, then a dialog box will pop up to ask the user to select the proper address.
	 * The selected address is cached so the next call to getLocalAddress() will simply return
	 * the cached address value.
	 * 
	 * @return A non-loopback, non-virtual IPv4 address found for the system.
	 * @throws SocketException
	 *             thrown when there is a problem retrieving the network
	 *             interfaces.
	 * @throws UnknownHostException
	 *             thrown when the local host address cannot be found.
	 */
	public String getLocalAddress() throws SocketException, UnknownHostException {

		// The code below is needed for Linux to find the host's real
		// (non-loopback) IP address.
		Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
		ArrayList<String> addr_choices = new ArrayList<String>();  // list of possible addresses
		
		while (nics.hasMoreElements()) {  // step through all the nics
			NetworkInterface nic = nics.nextElement();
			if (nic.isUp() && !nic.isVirtual()) {  // look only at real, operational nics
				Enumeration<InetAddress> addrs = nic.getInetAddresses();
				while (addrs.hasMoreElements()) {
					InetAddress addr = addrs.nextElement();
					if (!addr.isLoopbackAddress() && (addr instanceof Inet4Address)) {
						System.out.println("RMIUtilsBasic.getLocalAddress(): Found address = " + addr.getHostAddress() + "\n");
						// Only add the address if it is a private address
						addPrivateAddr(addr.getHostAddress(), addr_choices);
					}
				}
			}
		}
		switch(addr_choices.size()) {
		case 0:  // couldn't find an address in the above search process, so go with Java's default address result if there is one.
			System.out.println("RMIUtilsBasic.getLocalAddress(): The potentially filtered address search returned no results.  Defaulting to Java's default address, if it exists.\n");
			return java.net.InetAddress.getLocalHost().getHostAddress();

		case 1:  // only one address found
			return  addr_choices.get(0);

		default:  // Multiple potential addresses found
			String[] addr_array = addr_choices.toArray(new String[addr_choices.size()]);

			int addrIdx = JOptionPane.showOptionDialog(null, "Select the IP address of the physical network adapter:","Multiple IP Addresses Found!",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  addr_array, addr_array[0]); 
			if (addrIdx == JOptionPane.CLOSED_OPTION) {
				addrIdx = 0;   // Just take the first address in this case.
			}
			return addr_array[addrIdx];

		}

	}

	/**
	 * Adds the given host address to the addr_choices ArrayList if the host address is a private address, i.e.
	 * 10.x.x.x, 192.188.x.x or 172.16.x.x-172.31.x.x
	 * @param hostAddress  The host address to check and possibly add
	 * @param addr_choices  The ArrayList to add the host address to if it is a private address
	 */
	private void addPrivateAddr(String hostAddress,	ArrayList<String> addr_choices) {
		if(hostAddress.startsWith("10.") || hostAddress.startsWith("192.168.")){
			addr_choices.add(hostAddress);
		}
		else if (hostAddress.startsWith("172.")) {
			String[] digits = hostAddress.split(".");
			int d2 = Integer.parseInt(digits[1]);
			if ((16 <= d2) && (d2<32)){
				addr_choices.add(hostAddress);
			}
		}
	}


	/**
	 * Start up the RMI system.   This method should be called 
	 * before any other activity with that involves RMI.   This method
	 * configures the system properties RMI needs and starts the security
	 * manager. <br/>
	 * @throws Exception if unable to initialize RMI
	 */
	public void startRMI() throws Exception{
		configSecurityManager();
		configRMIProperties();
	}

	/**
	 * Sets the java.rmi.server.hostname
	 * system property 
	 * @throws Exception if unable to configure RMI
	 */
	private void configRMIProperties() throws Exception {
		// Logs all RMI activity to System.err
		System.setProperty("java.rmi.server.logCalls", "true"); 

		try {
			// Try to get figure out this server's IP address and save it as the
			// RMI server hostname.
			System.setProperty("java.rmi.server.hostname", getLocalAddress());

			System.out.println(
					"RMIUtilsBasic: java.rmi.server.hostname = "
					+ System.getProperty("java.rmi.server.hostname") + "\n");

		} catch (Exception e) {
			System.err.println("RMIUtilsBasic: Error getting local host address: " + e + "\n");
			throw(e);
		}

	}

	/**
	 * Sets the java.security.policy system property to point at the location 
	 * of the security policy file, which is assumed to be at 
	 * "provided\rmiUtils\server.policy"  (file separators adjusted to 
	 * match operating system).   the security manager is then started.
	 * This method must be called before starting the
	 * class server.
	 */
	private void configSecurityManager() {
		// file.separator is "\" in Windows and "/" in Unix/Linux/Mac.
		String sep = System.getProperty("file.separator");

        String policyFilePath = "provided" + sep + "hello" + sep + "server.policy";

        File policyFile = new File(policyFilePath);
        if (!policyFile.isFile()) {
            System.err.printf("<><><> !!! Security policy FILE NOT FOUND !!! <><><>%n" +
                "Expected file at %s%nJava security exceptions are likely.%n",
                policyFile.getAbsolutePath());
        }

        System.setProperty("java.security.policy", policyFilePath);
		System.out.println("RMIUtilsBasic: java.security.policy = "
				+ System.getProperty("java.security.policy"));

		// Start the security manager
		if (System.getSecurityManager() == null) {
			System.out.println("RMIUtilsBasic: Installing new Security Manager...");
			System.setSecurityManager(new SecurityManager());
		}
		System.out.println("RMIUtilsBasic: Security Manager = " + System.getSecurityManager()+"\n");
	}


}
