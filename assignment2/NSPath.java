/*

The NSPath class.  A NSPath object has two ends,
which must be opposite each other.

 */

public class NSPath extends StraightPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4809188185690066446L;
	/**
	 * Construct a NSPath in a specified location
	 * @param loc the loctaion that this path should be placed
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */
	public NSPath(GridLocation loc, Map map) {
		super(Direction.NORTH, Direction.SOUTH, loc, map);
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.5;
		y1 = 0.0;
		x2 = 0.5;
		y2 = 1.0;
	}

	@Override
	public String toString() {
		return "NSPath";
	}

}
