/*

The NWPath class.  A NWPath object has ends at the north
and west.

 */
public class NWPath extends CornerPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3148215773767407784L;
	/**
	 * Construct a NWPath in a specified location
	 * @param loc the loctaion that this path should be placed
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */
	public NWPath(GridLocation loc, Map map) {
		super(Direction.NORTH, Direction.WEST, loc, map);
		startAngle = 270;
	}

	@Override
	protected void initCoordinates() {
		x1 = -0.5;
		y1 = -0.5;
	}

	@Override
	public String toString() {
		return "NWPath";
	}

}
