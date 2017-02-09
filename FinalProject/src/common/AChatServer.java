package common;

import java.rmi.RemoteException;
import java.util.UUID;

 
/**
 * An abstract class to override toString() and equals() method
 */
public abstract class AChatServer implements IChatServer {

	/**
	 * Overriden equals() method to simply compare UUID
	 * @return  Equal if the UUID are the same.  False otherwise.
	 */
	@Override
	public boolean equals(Object other){
		try {
			return this.getUser().getId() == ((IChatServer) other).getUser().getId();
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Show the name and IP address of the user
	 */
	@Override
	public String toString() {
		try {
			return this.getUser().getName() + "@" + this.getUser().getIP();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Use this when necessary. Recommended by Dr. Wong.
	 */
	@Override
	public int hashCode() {
		UUID id=null;
		try {
			id = this.getUser().getId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return id.hashCode();
		
	}

}
