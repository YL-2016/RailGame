/*

The EWNPath class.  An EWNPath object has three ends.

 */

public class EWNPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 421262872029126357L;

	/**
	 * Construct a EWNPath in a specified location
	 * @param loc the loctaion that this path should be placed
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */

	public EWNPath(GridLocation loc, Map map) {
		super(Direction.EAST, Direction.WEST, Direction.NORTH, loc, map);
		startAngle = 180;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
		x3 = 0.5;
		y3 = -0.5;
	}

	@Override
	public synchronized boolean enter(TreasureHunter newTreasureHunter) {
		DirEnum nextDir = getNextDirEnum(newTreasureHunter);

		if (goingStraight) {
			if (nextDir == DirEnum.WEST || nextDir == DirEnum.EAST) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir == DirEnum.NORTH || nextDir == DirEnum.EAST) {
				return super.enter(newTreasureHunter);
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "EWNPath";
	}
}
