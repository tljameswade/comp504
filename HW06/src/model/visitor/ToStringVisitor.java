/**
 * 
 */
package model.visitor;

import provided.music.APhraseVisitor;
import provided.music.IPhrase;
import provided.music.IPhraseVisitorCmd;
import provided.music.NESeqList;

/**
 * The visitor that outputs the parsed music file to the view
 * @author Qianyi Wu, Suozhi Qi
 * @version 1.0
 * 
 */
public class ToStringVisitor extends APhraseVisitor {

	/**
	 * The constructor that adds two commands, one for empty sequences, and the other for non empty sequences
	 */
	public ToStringVisitor() {

		this.addCmd("NESeqList", new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				NESeqList list = (NESeqList) host;
				return list.getRest().execute(ToStringVisitor.this, params[0] + ", " + list.getFirst());
			}
		});

		this.addCmd("MTSeqList", new IPhraseVisitorCmd() {

			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				// TODO Auto-generated method stub
				return params[0] + "}";
			}
		});

	}
}
