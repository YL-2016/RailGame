import java.awt.*;

/*

 The CrossPath class.  A CrossPath object has four ends.

 */

public class CrossPath extends Path {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6959438589214923439L;

	// My line coordinates for drawing myself.
	private double x1, y1;
	private double x2, y2;
	private double x3, y3;
	private double x4, y4;

	// (end1,end2) and (end3,end4) are the two pairs.
	// The are, in order, always 'north', 'south', 'east', and 'west'.
	private Direction end1, end2, end3, end4;

	private Path neighbour1; // The Path in the direction end1.
	private Path neighbour2; // The Path in the direction end2.
	private Path neighbour3; // The Path in the direction end3.
	private Path neighbour4; // The Path in the direction end4.

	public CrossPath(GridLocation loc, Map map) {
		super(loc, map);

		color = Color.orange;
		end1 = new Direction(DirEnum.NORTH);
		end2 = new Direction(DirEnum.SOUTH);
		end3 = new Direction(DirEnum.EAST);
		end4 = new Direction(DirEnum.WEST);
	}

	@Override
	protected void initCoordinates() {
		x1 = 0.0;
		y1 = 0.5;
		x2 = 1.0;
		y2 = 0.5;
		x3 = 0.5;
		y3 = 0.0;
		x4 = 0.5;
		y4 = 1.0;
	}

	@Override
	public boolean exitOK(Direction direction) {
		return direction.isSameDirection(end1)
				|| direction.isSameDirection(end2)
				|| direction.isSameDirection(end3)
				|| direction.isSameDirection(end4);
	}

	@Override
	public String getDirectionInfo() {
		return end1 + " " + end2 + " " + end3 + " " + end4 + " ";
	}

	@Override
	public void register(Path path, Direction direction) {
		if (validDir(direction)) {
			if (direction.isSameDirection(end1)) {
				neighbour1 = path;
			} else if (direction.isSameDirection(end2)) {
				neighbour2 = path;
			} else if (direction.isSameDirection(end3)) {
				neighbour3 = path;
			} else if (direction.isSameDirection(end4)) {
				neighbour4 = path;
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
			} else if (direction.isSameDirection(end3)) {
				neighbour3 = null;
			} else if (direction.isSameDirection(end4)) {
				neighbour4 = null;
			}
		}
	}

	@Override
	public Direction exit(Direction direction) {
		if (validDir(direction)) {
			if (direction.isSameDirection(end1)) {
				return end2;
			} else if (direction.isSameDirection(end2)) {
				return end1;
			} else if (direction.isSameDirection(end3)) {
				return end4;
			} else if (direction.isSameDirection(end4)) {
				return end3;
			}
		}

		return null;
	}

	@Override
	public Path nextPath(Direction direction) {
		if (validDir(direction)) {
			if (direction.isSameDirection(end1)) {
				return neighbour2;
			} else if (direction.isSameDirection(end2)) {
				return neighbour1;
			} else if (direction.isSameDirection(end3)) {
				return neighbour4;
			} else if (direction.isSameDirection(end4)) {
				return neighbour3;
			}
		}

		return null;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(12));

		g.setColor(color);
		Rectangle b = getBounds();
		g.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
				(int) (x2 * b.width), (int) (y2 * b.height));
		g.drawLine((int) (x3 * b.width), (int) (y3 * b.height),
				(int) (x4 * b.width), (int) (y4 * b.height));

		super.draw(g);
	}

	@Override
	public String toString() {
		return "CrossPath";
	}

}
