package clientModel.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;

/**
 * A task that outputs the longest palindrome sequence in an input string
 * @author Xun Luan, Suozhi Qi
 *
 */
public class MaxPalinSubStr implements ITask<String> {

	/**
	 * Serialversion UID for well-defined serialization
	 */
	private static final long serialVersionUID = 3658187286392066101L;

	/**
	 * Adapter to the local view.  Marked "transient" so that it is not serialized
	 * and instead is reattached at the destination (the server).  
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	private final String inputString;

	public MaxPalinSubStr(String input) {
		// TODO Auto-generated constructor stub
		inputString = input;
		taskView.append("MaxPalinSubStr Constructing...\n");
	}

	/**
	 * The execute method for maxPalin class
	 * @return the longest palindrome sequence
	 */
	@Override
	public String execute() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Server: Executing client's LongestPalin task...");
		taskView.append("The longest palindrome sequence of the input String is: ");
		taskView.append(longestPalindrome(inputString) + "\n");
		return longestPalindrome(inputString);
	}

	/**
	 * Given a String, find the longest palindromic substring.
	 * @param s  The input text string.
	 * @return The found longest palindromic substring.
	 */
	private String longestPalindrome(String s) {
		if (s.length() == 0)
			return "";
		if (s.length() == 1)
			return s;
		int maximum = 0;
		int m_i = 0;
		int m_j = 0;
		boolean[][] p = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			p[i][i] = true;
		}
		for (int i = 0; i < s.length() - 1; i++) {
			p[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
			if (p[i][i + 1] && maximum < 2) {
				maximum = 2;
				m_i = i;
				m_j = i + 1;
			}
		}
		for (int i = 2; i < s.length(); i++) {
			for (int j = 0; j + i < s.length(); j++) {
				p[j][j + i] = p[j + 1][j + i - 1] && (s.charAt(j) == s.charAt(j + i));
				if (p[j][j + i]) {
					if (i + 1 > maximum) {
						maximum = i + 1;
						m_i = j;
						m_j = j + i;
					}
				}
			}
		}
		return s.substring(m_i, m_j + 1);
	}

	/**
	 * Reinitializes transient fields upon deserialization. See the <a href=
	 * "http://download.oracle.com/javase/6/docs/api/java/io/Serializable.html">
	 * Serializable</a> marker interface docs.
	 * taskView is set to a default value for now (ILocalTaskViewAdapter.DEFAULT_ADAPTER).
	 * 
	 * @param stream
	 *            The object stream with the serialized data
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject(); // Deserialize the non-transient data
		taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER; // re-initialize the
															// transient field
	}

	/**
	 * Sets the task view adapter to a new value.  Used by the server to attach
	 * the task to its view.
	 */
	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}
}