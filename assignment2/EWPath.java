/*

The EWPath class.  An EWPath object has two ends,
which must be opposite each other.

 */

public class EWPath extends StraightPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7535684661244464354L;

	public EWPath(GridLocation loc, Map map) {
		super(new Direction(DirEnum.EAST), new Direction(DirEnum.WEST), loc, map);
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
	}

	@Override
	public String toString() {
		return "EWPath";
	}

}
