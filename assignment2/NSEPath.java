/*

The NSEPath class.  An NSEPath object has three exits.

 */

public class NSEPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8056777129646902726L;

	public NSEPath(GridLoc loc, Map T) {
		super(new Direction("north"), new Direction("south"), new Direction(
				"east"), loc, T);
		setLoc(loc);
		startAngle = 180;
	}

	public NSEPath(Map T) {
		super(new Direction("north"), new Direction("south"), new Direction(
				"east"), T);
		startAngle = 180;
	}

	@Override
	public boolean enter(TreasureHunter newTreasureHunter) {
		Direction dir = newTreasureHunter.getDirection();
		Path currentPath = newTreasureHunter.getCurrentPath();
		Direction nD = currentPath.exit(dir);
		Direction nextDir = nD.opposite();

		if (goingStraight) {
			if (nextDir.equals("north") || nextDir.equals("south")) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir.equals("north") || nextDir.equals("east")) {
				return super.enter(newTreasureHunter);
			}
		}

		return false;
	}

	public void setLoc(GridLoc loc) {
		super.setLoc(loc);
		x1 = 0.5;
		y1 = 0.0;
		x2 = 0.5;
		y2 = 1.0;
		x3 = 0.5;
		y3 = -0.5;
	}

	public String toString() {
		return "NSEPath";
	};
}
