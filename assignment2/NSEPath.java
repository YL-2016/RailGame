/*

The NSEPath class.  An NSEPath object has three exits.

 */

public class NSEPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8056777129646902726L;

	public NSEPath(GridLocation loc, Map map) {
		super(new Direction(DirEnum.NORTH), new Direction(DirEnum.SOUTH),
				new Direction(DirEnum.EAST), loc, map);
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
