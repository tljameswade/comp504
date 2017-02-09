package shape;

import java.util.Set;
import java.util.HashSet;
import java.awt.Graphics;

/**
 * This is a composite class CompoundShape that extends and has Ashape instances
 * @author Suozhi Qi, Yiqing Lu
 * @version 1.0
 *  *
 */
public class CompoundShape extends Ashape {
	private Ashape[] binaryShape = new Ashape[2];
	/**
	* <pre>
	*           0..1     0..*
	* CompoundShape ------------------------> Ashape
	*           compoundShape        &gt;       ashape
	* </pre>
	*/
	private Set<Ashape> ashape;

	public Set<Ashape> getAshape() {
		if (this.ashape == null) {
			this.ashape = new HashSet<Ashape>();
		}
		return this.ashape;
	}

	/**
	* This is the CompoundShape constructor implementing
	* 	 * a CompoundShape that can be painted
	* 	 * @param firstShape is an instance of an Ashape
	* 	 * @param secondShape is another instance of an Ashape
	*/
	public CompoundShape(Ashape firstShape, Ashape secondShape) {
		this.binaryShape[0] = firstShape;
		this.binaryShape[1] = secondShape;
	}

	@Override
	public void paint(Graphics g) {
		for (Ashape shape : binaryShape) {
			shape.paint(g);
		}
	}
}
