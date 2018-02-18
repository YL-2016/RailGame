/*

The SWPath class.  A SWPath object has ends at the south
and west.

 */

public class SWPath extends CornerPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2510001367098631817L;

	public SWPath(GridLoc loc, Map T) {
		super(new Direction("south"), new Direction("west"), loc, T);
		setLoc(loc);
		startAngle = 0;
	}

	public SWPath(Map T) {
		super(new Direction("south"), new Direction("west"), T);
		startAngle = 0;
	}

	public void setLoc(GridLoc loc) {
		super.setLoc(loc);
		x1 = -0.5;
		y1 = 0.5;
	}

	public String toString() {
		return "SWPath";
	};
}
