/*

The NWPath class.  A NWPath object has ends at the north
and west.

 */

public class NWPath extends CornerPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3148215773767407784L;

	public NWPath(GridLoc loc, Map T) {
		super(new Direction("north"), new Direction("west"), loc, T);
		setLoc(loc);
		startAngle = 270;
	}

	public NWPath(Map T) {
		super(new Direction("north"), new Direction("west"), T);
		startAngle = 270;
	}

	public void setLoc(GridLoc loc) {
		super.setLoc(loc);
		x1 = -0.5;
		y1 = -0.5;
	}

	public String toString() {
		return "NWPath";
	};
}
