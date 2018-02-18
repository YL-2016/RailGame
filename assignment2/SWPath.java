/*

The SWPath class.  A SWPath object has ends at the south
and west.

 */

public class SWPath extends CornerPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2510001367098631817L;

	public SWPath(GridLoc loc, Map map) {
		super(new Direction(DirEnum.SOUTH), new Direction(DirEnum.WEST), loc, map);
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
