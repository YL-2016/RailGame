/*

The TwoEndPath class.  A TwoEndPath object has two ends,
which may or may be not be opposite each other.

 */
import java.awt.*;

public abstract class TwoEndPath extends Path {

	/**
	 * 
	 */
	private static final long serialVersionUID = 372366619444204374L;
	private Direction end1, end2;
	private Path neighbour1; // The Path in the direction end1.
	private Path neighbour2; // The Path in the direction end2.

	public TwoEndPath(Direction dir1, Direction dir2, GridLocation loc, Map map) {
		super(loc, map);
		color = Color.orange;
		end1 = dir1;
		end2 = dir2;
	}

	@Override
	public boolean exitOK(Direction direction) {
		return direction.isSameDirection(end1)
				|| direction.isSameDirection(end2);
	}

	@Override
	public String getDirectionInfo() {
		return end1 + " " + end2 + " ";
	}

	@Override
	public void register(Path path, Direction direction) {
		if (validDir(direction)) {
			if (direction.isSameDirection(end1)) {
				neighbour1 = path;
			} else {
				neighbour2 = path;
			}
		}
	}

	@Override
	public void unRegister(Direction direction) {
		if (validDir(direction)) {
			if (direction.isSameDirection(end1)) {
				neighbour1 = null;
			} else if (direction.isSameDirection(end2)) {
				neighbour2 = null;
			}
		}
	}

	@Override
	public Direction exit(Direction direction) {
		if (validDir(direction)) {
			return direction.isSameDirection(end1) ? end2 : end1;
		}

		return null;
	}

	@Override
	public Path nextPath(Direction d) {
		if (validDir(d)) {
			return d.isSameDirection(end1) ? neighbour2 : neighbour1;
		}

		return null;
	}

	@Override
	public String toString() {
		return "TwoEndPath";
	}

}
