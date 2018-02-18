/*

The SNEPath class.  An SNEPath object has two ends,
which must be opposite each other.

 */

public class SNEPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1880372384285140537L;

	public SNEPath(GridLoc loc, Map T) {
		super(new Direction("south"), new Direction("north"), new Direction(
				"east"), loc, T);
		startAngle = 90;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.5;
		y1 = 0.0;
		x2 = 0.5;
		y2 = 1.0;
		x3 = 0.5;
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
			if (nextDir.equals("south") || nextDir.equals("east")) {
				return super.enter(newTreasureHunter);
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "SNEPath";
	}

}
