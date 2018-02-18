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

	public TwoEndPath(Direction e1, Direction e2, GridLoc loc, Map T) {
		super(loc, T);
		color = Color.orange;
		end1 = e1;
		end2 = e2;
	}

	@Override
	public boolean exitOK(Direction d) {
		return d.equals(end1) || d.equals(end2);
	}

	@Override
	public String getDirectionInfo() {
		return end1.direction + " " + end2.direction + " ";
	}

	@Override
	public void register(Path r, Direction d) {
		if (validDir(d)) {
			if (d.equals(end1)) {
				neighbour1 = r;
			} else {
				neighbour2 = r;
			}
		}
	}

	@Override
	public void unRegister(Direction d) {
		if (validDir(d)) {
			if (d.equals(end1)) {
				neighbour1 = null;
			} else if (d.equals(end2)) {
				neighbour2 = null;
			}
		}
	}

	@Override
	public Direction exit(Direction d) {
		if (validDir(d)) {
			return d.equals(end1) ? end2 : end1;
		}

		return null;
	}

	@Override
	public Path nextPath(Direction d) {
		if (validDir(d)) {
			return d.equals(end1) ? neighbour2 : neighbour1;
		}

		return null;
	}

	@Override
	public String toString() {
		return "TwoEndPath";
	}
}
