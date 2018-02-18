/*

The NSWPath class.  An NSWPath object has three ends.

 */

public class NSWPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8296479325132751896L;

	public NSWPath(GridLoc loc, Map T) {
		super(new Direction("north"), new Direction("south"), new Direction(
				"west"), loc, T);
		startAngle = 270;
	}
	
	@Override
	protected void initCoordinates() {
		x1 = 0.5;
		y1 = 0.0;
		x2 = 0.5;
		y2 = 1.0;
		x3 = -0.5;
		y3 = -0.5;
	}

	@Override
	public boolean enter(TreasureHunter newTreasureHunter) {
		Direction nextDir = getNextDirection(newTreasureHunter);

		if (goingStraight) {
			if (nextDir.equals("north") || nextDir.equals("south")) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir.equals("north") || nextDir.equals("west")) {
				return super.enter(newTreasureHunter);
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "NSWPath";
	}
}
