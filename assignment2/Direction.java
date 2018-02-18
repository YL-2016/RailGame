// A direction; one of 'north', 'south', 'east' and 'west'.

public class Direction {

	private DirEnum direction;

	public Direction(DirEnum dirEnum) {
		direction = dirEnum;
	}

	public DirEnum getDirection() {
		return direction;
	}

	public boolean isSameDirection(Direction dir) {
		return direction == dir.getDirection();
	}

	public Direction getOpposite() {
		switch (direction) {
		case NORTH:
			return new Direction(DirEnum.SOUTH);
		case SOUTH:
			return new Direction(DirEnum.NORTH);
		case EAST:
			return new Direction(DirEnum.WEST);
		case WEST:
			return new Direction(DirEnum.EAST);
		}

		return null;
	}

	@Override
	public String toString() {
		return direction.toString();
	}

}
