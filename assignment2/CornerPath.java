import java.awt.*;

/*

 The CornerPath class.  A CornerPath object has two ends,
 which must be not be opposite each other.

 */

public abstract class CornerPath extends TwoEndPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5403618982330364538L;

	// The multipliers for the width and height.
	protected double x1, y1;
	protected int startAngle;

	private int arcAngle;

	/**
	 *Construct a CornerRail with specified begin and end directions,
	 * its own location in the Path and Specified Map.
	 *
	 * @param dir1 the original direction
	 * @param dir2 the direction that going to
	 * @param loc the location of this CornerPath on the Path
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */

	public CornerPath(Direction dir1, Direction dir2, GridLocation loc, Map map) {
		super(dir1, dir2, loc, map);
		arcAngle = 90;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(12));

		g2.setColor(color);
		Rectangle b = getBounds();
		g2.drawArc((int) (x1 * b.width), (int) (y1 * b.height), b.width,
				b.height, startAngle, arcAngle);

		super.draw(g);
	}

	@Override
	public String toString() {
		return "CornerPath";
	}
	
}
