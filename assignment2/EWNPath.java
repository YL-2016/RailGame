/*

The EWNPath class.  An EWNPath object has three ends.

 */

public class EWNPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 421262872029126357L;

	public EWNPath(GridLoc loc, Map T) {
		super(new Direction("east"), new Direction("west"), new Direction(
				"north"), loc, T);
		setLoc(loc);
		startAngle = 180;
	}

	public EWNPath(Map T) {
		super(new Direction("east"), new Direction("west"), new Direction(
				"north"), T);
		startAngle = 180;
	}

	@Override
	public boolean enter(TreasureHunter newTreasureHunter) {
		Direction dir = newTreasureHunter.getDirection();
		Path currentPath = newTreasureHunter.getCurrentPath();
		Direction nD = currentPath.exit(dir);
		Direction nextDir = nD.opposite();

		if (goingStraight) {
			if (nextDir.equals("west") || nextDir.equals("east")) {
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
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
		x3 = 0.5;
		y3 = -0.5;
	}

	public String toString() {
		return "EWNPath";
	}
}
