/*

The SWPath class.  A SWPath object has ends at the south
and west.

 */

public class SWPath extends CornerPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2510001367098631817L;
	/**
	 * Construct a SWPath in a specified location
	 * @param loc the loctaion that this path should be placed
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */
	public SWPath(GridLocation loc, Map map) {
		super(Direction.SOUTH, Direction.WEST, loc, map);
		startAngle = 0;
	}

	@Override
	protected void initCoordinates() {
		x1 = -0.5;
		y1 = 0.5;
	}

	@Override
	public String toString() {
		return "SWPath";
	}

}
