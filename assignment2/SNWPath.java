/*

The SNWPath class.  An SNWPath object has three ends.

 */

public class SNWPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3693104809275455409L;

	public SNWPath(GridLoc loc, Map T) {
		super(new Direction("south"), new Direction("north"), new Direction(
				"west"), loc, T);
		setLoc(loc);
		startAngle = 0;
	}

	public SNWPath(Map T) {
		super(new Direction("south"), new Direction("north"), new Direction(
				"west"), T);
		startAngle = 0;
	}

	@Override
	public boolean enter(TreasureHunter newTreasureHunter) {
		Direction dir = newTreasureHunter.getDirection();
		Path currentPath = newTreasureHunter.getCurrentPath();
		Direction nD = currentPath.exit(dir);
		Direction nextDir = nD.opposite();

		if (goingStraight) {
			if (nextDir.equals("south") || nextDir.equals("north")) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir.equals("south") || nextDir.equals("west")) {
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
		x3 = -0.5;
		y3 = 0.5;
	}

	public String toString() {
		return "SNWPath";
	};
}
