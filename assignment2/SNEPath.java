/*

The SNEPath class.  An SNEPath object has two ends,
which must be opposite each other.

 */
public class SNEPath extends SwitchPath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1880372384285140537L;

	public SNEPath(GridLocation loc, Map T) {
		super(Direction.SOUTH, Direction.NORTH, Direction.EAST, loc, T);
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
	public synchronized boolean enter(TreasureHunter newTreasureHunter) {
		DirEnum nextDir = getNextDirEnum(newTreasureHunter);

		if (goingStraight) {
			if (nextDir == DirEnum.SOUTH || nextDir == DirEnum.NORTH) {
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
		return "SNEPath";
	}

}
