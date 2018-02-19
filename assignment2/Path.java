/*

The Path class. A Path object is a piece of the map. It knows
whether there is a Person on it or not, and these Persons can enter()
and leave(). Given an entrance, a Path can report where the exit()
is.

 */

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Path extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8969555959818787955L;

	// The amount of space in which Paths have to draw themselves.
	public final static int size = 20;

	protected Color color; // color

	private boolean isOccupied; // isOccupied
	private boolean hasTreasure; // hasTreasure
	private TreasureHunter currentTreasureHunter; // currentTreasureHunter
	private GridLocation location; // location
	private Map theMap; // theMap

	public Path(Map map) {
		theMap = map;
		isOccupied = false;
		setSize(size, size);
	}

	public Path(GridLocation loc, Map map) {
		location = loc;
		theMap = map;
		isOccupied = false;
		initCoordinates();
	}

	public void setHasTreasure(boolean flag) {
		hasTreasure = flag;
	}

	// Return true iff there is a treasure hunter on me.
	public boolean isOccupied() {
		return isOccupied;
	}

	public void setGridLocation(GridLocation loc) {
		location = loc;
	}

	public GridLocation getGridLocation() {
		return location;
	}

	// Redraw myself.
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Rectangle b = getBounds();

		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.lightGray);
		g2.drawRect(0, 0, b.width - 1, b.height - 1);
		g2.setStroke(new BasicStroke(12));

		if (isOccupied) {
			currentTreasureHunter.draw(g2);
		}

		if (hasTreasure) {
			Ellipse2D circle = new Ellipse2D.Double(b.width / 3, b.height / 3,
					b.width / 2, b.height / 2);
			g2.setColor(Color.YELLOW);
			g2.fill(circle);
		}
	}

	// Register that a Person is on me. Return true if successful,
	// false otherwise.
	public synchronized boolean enter(TreasureHunter newTreasureHunter) {
		isOccupied = true;
		currentTreasureHunter = newTreasureHunter;

		if (hasTreasure) {
			currentTreasureHunter.plusOneScore();
			hasTreasure = false;
			theMap.updateStatusBar();
			theMap.spawnTreasure();
		}

		return true;
	}

	// Register that a Person is no longer on me.
	public void leave() {
		isOccupied = false;
		repaint();
	}

	// Update my display.
	public void paint(Graphics g) {
		draw(g);
	}

	// Return true if d is a valid direction for me.
	public abstract boolean exitOK(Direction direction);

	// Register that Path r is in Direction d.
	public abstract void register(Path path, Direction direction);

	// Register that there is no Path in Direction d.
	public abstract void unRegister(Direction direction);

	// Given that d is the Direction from which a TreasureHunter entered,
	// report where the TreasureHunter will exit.
	public abstract Direction exit(Direction direction);

	// Given that d is the Direction from which a TreasureHunter entered,
	// report which Path is next.
	public abstract Path nextPath(Direction direction);

	public abstract String getDirectionInfo();

	protected abstract void initCoordinates();

	// Return true if d is valid for this Path, return false and
	// print an error otherwise.
	protected boolean validDir(Direction direction) {
		if (!exitOK(direction)) {
			System.err.print("exit(): Not a valid dir for this piece: ");
			System.err.println(getDirectionInfo() + " " + direction);
			Exception e = new Exception();
			e.printStackTrace(System.out);

			return false;
		}

		return true;
	}

	// Return myself as a string.
	public String toString() {
		return "Path";
	}

}
