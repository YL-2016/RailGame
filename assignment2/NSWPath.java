/*

The NSWPath class.  An NSWPath object has three ends.

 */

public class NSWPath extends SwitchPath {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8296479325132751896L;

	public NSWPath(GridLoc loc, Map map) {
		super(new Direction(DirEnum.NORTH), new Direction(DirEnum.SOUTH),
				new Direction(DirEnum.WEST), loc, map);
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
		DirEnum nextDir = getNextDirEnum(newTreasureHunter);

		if (goingStraight) {
			if (nextDir == DirEnum.NORTH || nextDir == DirEnum.SOUTH) {
				return super.enter(newTreasureHunter);
			}
		} else {
			if (nextDir == DirEnum.NORTH || nextDir == DirEnum.WEST) {
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
