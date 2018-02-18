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
		setLoc(loc);
		startAngle = 90;
	}

	public SNEPath(Map T) {
		super(new Direction("south"), new Direction("north"), new Direction(
				"east"), T);
		startAngle = 90;
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
			if (nextDir.equals("south") || nextDir.equals("east")) {
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
		y3 = 0.5;
	}

	public String toString() {
		return "SNEPath";
	};

}
