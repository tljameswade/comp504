package sq6_kt21.concrete.chat;

import common.msg.chat.ITextMsg;

/**
 * The textMsg class
 * @author Kun Tian
 *
 */
public class TextMsg implements ITextMsg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	
	/**
	 * The text msg constructor
	 * @param text
	 */
	public TextMsg(String text) {
		this.text = text;
	}
	@Override
	public String getContent() {
		return text;
	}

}
