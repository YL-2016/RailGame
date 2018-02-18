/*

The EWSPath class.  An EWSPath object has three ends.

 */

public class EWSPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526401617954515938L;

	public EWSPath(GridLoc loc, Map T) {
		super(new Direction("east"), new Direction("west"), new Direction(
				"south"), loc, T);
		startAngle = 90;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
		x3 = 0.5;
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
			if (nextDir.equals("south") || nextDir.equals("east")) {
				return super.enter(newTreasureHunter);
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "EWSPath";
	}
}
