/*

The NEPath class.  A NEPath object has ends at the north
and east.

 */

public class NEPath extends CornerPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1574682404470395514L;

	public NEPath(GridLoc loc, Map T) {
		super(new Direction("north"), new Direction("east"), loc, T);
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
