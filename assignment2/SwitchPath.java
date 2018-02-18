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

	public SwitchPath(Direction e1, Direction e2, Direction e3, GridLoc loc,
			Map T) {
		super(loc, T);
		color = Color.magenta;
		arcAngle = 90;
		end1 = e1;
		end2 = e2;
		end3 = e3;
	}

	@Override
	public boolean exitOK(Direction d) {
		return d.equals(end1) || d.equals(end2) || d.equals(end3);
	}

	@Override
	public String getDirectionInfo() {
		return end1.direction + " " + end2.direction + " " + end3.direction
				+ " ";
	}

	@Override
	public void register(Path p, Direction d) {
		if (validDir(d)) {
			if (d.equals(end1)) {
				neighbour1 = p;
			} else if (d.equals(end2)) {
				neighbour2 = p;
			} else {
				neighbour3 = p;
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
			} else {
				neighbour3 = null;
			}
		}
	}

	@Override
	public Direction exit(Direction d) {
		if (validDir(d)) {
			if (goingStraight) {
				return d.equals(end1) ? end2 : end1;
			} else {
				return d.equals(end1) ? end3 : end1;
			}
		}

		return null;
	}

	@Override
	public Path nextPath(Direction d) {
		if (validDir(d)) {
			if (goingStraight) {
				return d.equals(end1) ? neighbour2 : neighbour1;
			} else {
				return d.equals(end1) ? neighbour3 : neighbour1;
			}
		}

		return null;
	}

	@Override
	public boolean handleEvent(Event evt) {
		if (evt.id == Event.MOUSE_DOWN && !isOccupied()) {
			goingStraight = !goingStraight;
			repaint();
			return true;
		}

		// If we get this far, I couldn't handle the event
		return false;
	}

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

	public Direction getNextDirection(TreasureHunter newTreasureHunter) {
		Direction dir = newTreasureHunter.getDirection();
		Path currentPath = newTreasureHunter.getCurrentPath();
		Direction nD = currentPath.exit(dir);

		return nD.opposite();
	}

	@Override
	public String toString() {
		return "SwitchPath";
	}
}
