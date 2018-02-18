/*

The NWPath class.  A NWPath object has ends at the north
and west.

 */

public class NWPath extends CornerPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3148215773767407784L;

	public NWPath(GridLoc loc, Map map) {
		super(new Direction(DirEnum.NORTH), new Direction(DirEnum.WEST), loc, map);
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
