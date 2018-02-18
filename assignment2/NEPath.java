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
		setLoc(loc);
		startAngle = 180;
	}

	public NEPath(Map T) {
		super(new Direction("north"), new Direction("east"), T);
		startAngle = 180;
	}

	public void setLoc(GridLoc loc) {
		super.setLoc(loc);
		x1 = 0.5;
		y1 = -0.5;
	}

	public String toString() {
		return "NEPath";
	};
}
