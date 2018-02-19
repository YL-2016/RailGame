// A direction; one of 'north', 'south', 'east' and 'west'.

public class Direction {
	public static final Direction SOUTH = new Direction(DirEnum.SOUTH);
	public static final Direction NORTH = new Direction(DirEnum.NORTH);
	public static final Direction WEST = new Direction(DirEnum.WEST);
	public static final Direction EAST = new Direction(DirEnum.EAST);

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
			return Direction.SOUTH;
		case SOUTH:
			return Direction.NORTH;
		case EAST:
			return Direction.WEST;
		case WEST:
			return Direction.EAST;
		}

		return null;
	}

	@Override
	public String toString() {
		return direction.toString();
	}

}
