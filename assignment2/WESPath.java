/*

The WESPath class.  An WESPath object has three ends.

 */

public class WESPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4283767017166560122L;

	public WESPath(GridLoc loc, Map T) {
		super(new Direction("west"), new Direction("east"), new Direction(
				"south"), loc, T);
		startAngle = 0;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
		x3 = -0.5;
		y3 = 0.5;
	}

	@Override
	public boolean enter(TreasureHunter newTreasureHunter) {
		Direction nextDir = getNextDirection(newTreasureHunter);

		if (goingStraight) {
			if (nextDir.equals("west") || nextDir.equals("east")) {
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
		return "WESPath";
	}
}
