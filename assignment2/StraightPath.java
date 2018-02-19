/*

The StraightPath class.  A StraightPath object has two ends,
which must be opposite each other.

 */

import java.awt.*;

public abstract class StraightPath extends TwoEndPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3333023437517703580L;

	// The multipliers for the end points of the StraightPath.
	protected double x1, y1;
	protected double x2, y2;

	public StraightPath(Direction dir1, Direction dir2, GridLocation loc,
			Map map) {
		super(dir1, dir2, loc, map);
		color = Color.orange;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(12));
		g2.setColor(color);
		Rectangle b = getBounds();
		g2.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
				(int) (x2 * b.width), (int) (y2 * b.height));

		super.draw(g2);
	}

	@Override
	public String toString() {
		return "StraightPath";
	}

}
