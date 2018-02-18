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
		startAngle = 0;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.5;
		y1 = 0.0;
		x2 = 0.5;
		y2 = 1.0;
		x3 = -0.5;
		y3 = 0.5;
	}

	@Override
	public boolean enter(TreasureHunter newTreasureHunter) {
		Direction nextDir = getNextDirection(newTreasureHunter);

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

	@Override
	public String toString() {
		return "SNWPath";
	}
}
