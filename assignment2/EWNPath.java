/*

The EWNPath class.  An EWNPath object has three ends.

 */

public class EWNPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 421262872029126357L;

	public EWNPath(GridLocation loc, Map map) {
		super(new Direction(DirEnum.EAST), new Direction(DirEnum.WEST),
				new Direction(DirEnum.NORTH), loc, map);
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
	public boolean enter(TreasureHunter newTreasureHunter) {
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
