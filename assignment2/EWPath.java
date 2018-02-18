/*

The EWPath class.  An EWPath object has two ends,
which must be opposite each other.

 */

public class EWPath extends StraightPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7535684661244464354L;

	public EWPath(GridLoc loc, Map T) {
		super(new Direction("east"), new Direction("west"), loc, T);
		setLoc(loc);
	}

	public EWPath(Map T) {
		super(new Direction("east"), new Direction("west"), T);
	}

	public void setLoc(GridLoc loc) {
		super.setLoc(loc);
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
	}

	public String toString() {
		return "EWPath";
	};
}
