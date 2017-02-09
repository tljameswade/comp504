package common;


import common.msg.IChatMsg;
import provided.mixedData.MixedDataKey;

/**
 * ICmd2ModelAdapter interface is used to handle unknown types
 * 
 */
public interface ICmd2ModelAdapter {
	
	/**
	 * Call fac.make() to get a component, like JPanel or JLabel, and put it somewhere in your view.
	 * For example, you can put it in a scrollable panel or a new pop-up JFrame.
	 * Usually, you need to implement this method to let it go all the way to the view to put the component that fac.make() returns.
	 * 
	 * @param fac Component factory.
	 */
	public void buildComponentInScrollable(IComponentFactory fac);
	
	public void buildComponentInNonScrollable(IComponentFactory fac);
	
	/**
	 * TIP: As a game dispatcher, you should probably generate your key when making an instance of this cmd in your local (mini)model.
	 *  
	 * Put the data you want to store locally in the machine of a game receiver, in the format of (key,value) pair.
	 * For example, you can store an adapter to the game MVC you create on other's machine, or the computation results you want to use again in a later cmd.
	 * 
	 * @param key The key for type T that you specify for the following value.
	 * @param value The thing, of type T, you wish to store in local MixedData dictionary.
	 * @return True if put successfully. Otherwise false.
	 */
	public <T> boolean putIntoLocalDict(MixedDataKey<T> key, T value);
	
	/**
	 * TIP: As a game dispatcher, you should probably generate your key when making an instance of this cmd in your local (mini)model.
	 *  
	 * Get the data you want, which is stored locally in the machine of a game receiver.
	 * For example, you can get an adapter to the game MVC you create on other's machine in some preceding cmd, or the computation results you created before in a preceding cmd.
	 * 
	 * @param key The key to get certain value of type T from the local MixedData dictionary.
	 * @return A value of type T.
	 */
	public <T> T getFromLocalDict(MixedDataKey<T> key);
	


	/**
	 * send a message to the local chatroom
	 * @param index Index value for the message given message type
	 * @param msg Message object
	 */
	public <T extends IChatMsg> void sendMsg2LocalChatroom(Class<T> index, T msg);
	
	
	
	
	/**
	 * @return The ChatServer who is processing this cmd.
	 */
	public IChatServer getChatServer();
	
	
	
	
//	/**
//	 * WARNING: This method is only for ease of use when coding prototypes or demos. You should not call this method in product version of your project.
//	 * 
//	 * @return The reference to the local MixedData dictionary.
//	 */
//	@Deprecated
//	public IMixedDataDictionary getDict();
	
	
	
//	/**
//	 *  Get a container that is on the GUI. This could be implemented as a factory method
//	 *  that creates a new Container (such as a JPanel or JFrame), and returns it here and puts it on the GUI
//	 *  Scrollable components that are added are considered by the system to be just fancy displays of message contents and in such, 
//	 *  just like text messages, are desired to be placed on some sort of scrolling display.
//	 *  @return the Container to modify
//	 *  */
//	@Deprecated
//	public Container getScrollable();
//	
//	/**
//	 *  Get a container that is on the GUI. This could be implemented as a factory method
//	 *  that creates a new Container (such as a JPanel or JFrame) The method will return this container and display it on the GUI.
//	 *  Non-scrollable components that are added are actually static modifications of the local GUI to present additional user interaction capabilities.  
//	 *  It would be undesireable for these components to scroll off the screen as other messages arrive; they should have a fixed location on the local GUI.
//	 *  @return the Container to modify
//	 *  */
//	@Deprecated
//	public Container getNonScrollable();
	
	
}
