/*

The NSEPath class.  An NSEPath object has three exits.

 */

public class NSEPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8056777129646902726L;
	/**
	 * Construct a NSEPath in a specified location
	 * @param loc the loctaion that this path should be placed
	 * @param map A Map object is made up of Paths, and has zero or more people in it.
	 */
	public NSEPath(GridLocation loc, Map map) {
		super(Direction.NORTH, Direction.SOUTH, Direction.EAST, loc, map);
		startAngle = 180;
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.5;
		y1 = 0.0;
		x2 = 0.5;
		y2 = 1.0;
		x3 = 0.5;
		y3 = -0.5;
	}
	/**
	 * 	Return true if d is valid for a TreasureHunter to this Path, return false and
	 * 	print an error otherwise.
	 * @param newTreasureHunter a TreasureHunter
	 * @return if the exit exists at a certain direction
	 */
	@Override
	public synchronized boolean enter(TreasureHunter newTreasureHunter) {
		DirEnum nextDir = getNextDirEnum(newTreasureHunter);

		if (goingStraight) {
			if (nextDir == DirEnum.NORTH || nextDir == DirEnum.SOUTH) {
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
		return "NSEPath";
	}

}
