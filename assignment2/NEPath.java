/*

The NEPath class.  A NEPath object has ends at the north
and east.

 */

public class NEPath extends CornerPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1574682404470395514L;
	/**
	 * Construct a NEPath in a specified location
	 * @param loc the loctaion that this path should be placed
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */
	public NEPath(GridLocation loc, Map map) {
		super(Direction.NORTH, Direction.EAST, loc, map);
		startAngle = 180;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.5;
		y1 = -0.5;
	}

	@Override
	public String toString() {
		return "NEPath";
	}

}
