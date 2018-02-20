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
	/**
	 * Create a TwoEndPath with specified location, directions and Map
	 * @param loc the location of the Path
	 * @param map the Map
	 */
	public TwoEndPath(Direction dir1, Direction dir2, GridLocation loc, Map map) {
		super(loc, map);
		color = Color.orange;
		end1 = dir1;
		end2 = dir2;
	}
	/**
	 * Return true if d is a valid direction for me.
	 * @param direction a Direction
	 * @return Return true if d is a valid direction for me.
	 */
	@Override
	public boolean exitOK(Direction direction) {
		return direction.isSameDirection(end1)
				|| direction.isSameDirection(end2);
	}

	@Override
	public String getDirectionInfo() {
		return end1 + " " + end2 + " ";
	}

	/**
	 * Register that Path r is in Direction d.
	 * @param path the Path that to be register a certain direction
	 * @param direction a direction that is to be registered
	 */
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

	/**
	 * Register that there is no Path in Direction d.
	 * @param direction the direction that to be unregistered
	 */
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
	/***
	 * 	Given that d is the Direction from which a TreasureHunter entered
	 * 	report where the TreasureHunter will exit.
	 * @param direction the direction that currently entered
	 * @return the exit direction or null if do not have
	 */
	@Override
	public Direction exit(Direction direction) {
		if (validDir(direction)) {
			return direction.isSameDirection(end1) ? end2 : end1;
		}

		return null;
	}

	/**
	 * Given that d is the Direction from which a TreasureHunter entered,
	 * report which Path is next.
	 * @param d the direction of a TreasureHunter entered
	 * @return the next Path or null if do not have
	 */@Override
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
