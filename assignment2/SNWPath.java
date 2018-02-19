/*

The SNWPath class.  An SNWPath object has three ends.

 */

public class SNWPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3693104809275455409L;

	public SNWPath(GridLocation loc, Map map) {
		super(Direction.SOUTH, Direction.NORTH, Direction.WEST, loc, map);
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
	public synchronized boolean enter(TreasureHunter newTreasureHunter) {
		DirEnum nextDir = getNextDirEnum(newTreasureHunter);

		if (goingStraight) {
			if (nextDir == DirEnum.SOUTH || nextDir == DirEnum.NORTH) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir == DirEnum.SOUTH || nextDir == DirEnum.WEST) {
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
