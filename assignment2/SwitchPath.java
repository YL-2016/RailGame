/*

The SwitchPath class.  A SwitchPath object has three ends, and a controller
which determines which ends are used.  If a TreasureHunter moves on from the first end,
the switch determines which of the other two ends it leaves from.  If it moves
on from one of the other two ends, it automatically leaves by the first end.

 */

import java.awt.*;

public abstract class SwitchPath extends Path {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5823743842007274628L;

	// My line coordinates for drawing myself.
	protected double x1, y1;
	protected double x2, y2;
	protected double x3, y3;

	// Info for my corner portion.
	protected int startAngle;
	private int arcAngle;

	// (end1,end2) and (end1,end3) are the two pairs.
	// end1 and end2 are the straight directions (i.e., they are
	// opposite each other), and end1 and end3 form the corner.
	protected Direction end1, end2, end3;

	private Path neighbour1; // The Path in the direction end1.
	private Path neighbour2; // The Path in the direction end2.
	private Path neighbour3; // The Path in the direction end3.

	// Whether I am aligned to go straight.
	protected boolean goingStraight;
	/**
	 * Create a SwitchPath with specified location, directions and Map
	 * @param loc the location of the SwitchPath
	 * @param map the Map
	 */
	public SwitchPath(Direction dir1, Direction dir2, Direction dir3,
			GridLocation loc, Map map) {
		super(loc, map);
		color = Color.magenta;
		arcAngle = 90;
		end1 = dir1;
		end2 = dir2;
		end3 = dir3;
	}
	/**
	 * Return true if d is a valid direction for me.
	 * @param direction a Direction
	 * @return Return true if d is a valid direction for me.
	 */
	@Override
	public boolean exitOK(Direction direction) {
		return direction.isSameDirection(end1)
				|| direction.isSameDirection(end2)
				|| direction.isSameDirection(end3);
	}

	@Override
	public String getDirectionInfo() {
		return end1 + " " + end2 + " " + end3 + " ";
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
			} else if (direction.isSameDirection(end2)) {
				neighbour2 = path;
			} else {
				neighbour3 = path;
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
			} else {
				neighbour3 = null;
			}
		}
	}
	/***
	 * 	Given that d is the Direction from which a TreasureHunter entered
	 * 	report where the TreasureHunter will exit.
	 * @param direction the direction that currently entered
	 * @return the exit direction
	 */
	@Override
	public Direction exit(Direction direction) {
		if (validDir(direction)) {
			if (goingStraight) {
				return direction.isSameDirection(end1) ? end2 : end1;
			} else {
				return direction.isSameDirection(end1) ? end3 : end1;
			}
		}

		return null;
	}
	/**
	 * Given that d is the Direction from which a TreasureHunter entered,
	 * report which Path is next.
	 * @param direction the direction of a TreasureHunter entered
	 * @return the next Path
	 */
	@Override
	public Path nextPath(Direction direction) {
		if (validDir(direction)) {
			if (goingStraight) {
				return direction.isSameDirection(end1) ? neighbour2
						: neighbour1;
			} else {
				return direction.isSameDirection(end1) ? neighbour3
						: neighbour1;
			}
		}

		return null;
	}

	@Override
	public boolean handleEvent(Event e) {
		if (e.id == Event.MOUSE_DOWN && !isOccupied()) {
			goingStraight = !goingStraight;
			repaint();
			return true;
		}

		// If we get this far, I couldn't handle the event
		return false;
	}
	/**
	 * Redraw myself.
	 * @param g the Graphics
	 */
	@Override
	public void draw(Graphics g) {
		Rectangle b = getBounds();

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(12));

		// Draw current direction of the switch darker.
		if (goingStraight) {
			g2.setColor(Color.lightGray);
			g2.drawArc((int) (x3 * b.width), (int) (y3 * b.height), b.width,
					b.height, startAngle, arcAngle);
			g2.setColor(color);
			g2.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
					(int) (x2 * b.width), (int) (y2 * b.height));
		} else {
			g2.setColor(Color.lightGray);
			g2.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
					(int) (x2 * b.width), (int) (y2 * b.height));
			g2.setColor(color);
			g2.drawArc((int) (x3 * b.width), (int) (y3 * b.height), b.width,
					b.height, startAngle, arcAngle);
		}

		super.draw(g);
	}

	public DirEnum getNextDirEnum(TreasureHunter newTreasureHunter) {
		Direction dir = newTreasureHunter.getDirection();
		Path currentPath = newTreasureHunter.getCurrentPath();
		Direction nD = currentPath.exit(dir);

		return nD.getOpposite().getDirection();
	}

	@Override
	public String toString() {
		return "SwitchPath";
	}
	
}
