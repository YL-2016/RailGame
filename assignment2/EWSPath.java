/*

The EWSPath class.  An EWSPath object has three ends.

 */

public class EWSPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2526401617954515938L;

	public EWSPath(GridLocation loc, Map map) {
		super(new Direction(DirEnum.EAST), new Direction(DirEnum.WEST),
				new Direction(DirEnum.SOUTH), loc, map);
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
	public synchronized boolean enter(TreasureHunter newTreasureHunter) {
		DirEnum nextDir = getNextDirEnum(newTreasureHunter);

		if (goingStraight) {
			if (nextDir == DirEnum.WEST || nextDir == DirEnum.EAST) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir == DirEnum.SOUTH || nextDir == DirEnum.EAST) {
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
