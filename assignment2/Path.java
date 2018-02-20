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

	/**
	 * Create a Path without a location
	 * @param map the Map
	 */
	public Path(Map map) {
		theMap = map;
		isOccupied = false;
		setSize(size, size);
	}

	/**
	 * Create a Path with specified location and Map, and
	 * set the isoccupied to false
	 * @param loc the location of the Path
	 * @param map the Map
	 */
	public Path(GridLocation loc, Map map) {
		location = loc;
		theMap = map;
		isOccupied = false;
		initCoordinates();
	}

	// to modified a path has treasure
	public void placeTreasure() {
		hasTreasure = true;
	}

	/**
	 * Return true iff there is a treasure hunter on me.
	 * @return true iff there is a treasure hunter on me.
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Set the location of the Path
	 * @param loc the location of the Path
	 */
	public void setGridLocation(GridLocation loc) {
		location = loc;
	}

	/**
	 * Return location of the Path
	 * @return location of the Path
	 */
	public GridLocation getGridLocation() {
		return location;
	}


	/**
	 * Redraw myself.
	 * @param g the Graphics
	 */
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

	/**
	 * Register a Person that is on Path, true if enter successfully, otherwise false
	 * Set isOccupied to true and currentTreasureHunter to be newTreasureHunter
	 * @param newTreasureHunter the TreasureHunter going to enter the Path
	 * @return true if enter successfully, otherwise false
	 */
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


	/**
	 * Register that a Person is no longer on me.
	 */
	public void leave() {
		isOccupied = false;
		repaint();
	}

	/**
	 * update my display
	 * @param g the Graphics
	 */
	@Override
	public void paint(Graphics g) {
		draw(g);
	}

	/**
	 * Return true if d is a valid direction for me.
	 * @param direction a Direction
	 * @return Return true if d is a valid direction for me.
	 */
	public abstract boolean exitOK(Direction direction);


	/**
	 * Register that Path r is in Direction d.
	 * @param path the Path that to be register a certain direction
	 * @param direction a direction that is to be registered
	 */
	public abstract void register(Path path, Direction direction);

	/**
	 * Register that there is no Path in Direction d.
	 * @param direction the direction that to be unregistered
	 */
	public abstract void unRegister(Direction direction);

	/***
	 * 	Given that d is the Direction from which a TreasureHunter entered
	 * 	report where the TreasureHunter will exit.
	 * @param direction the direction that currently entered
	 * @return the exit direction
	 */
	public abstract Direction exit(Direction direction);

	/**
	 * Given that d is the Direction from which a TreasureHunter entered,
	 * report which Path is next.
	 * @param direction the direction of a TreasureHunter entered
	 * @return the next Path
	 */
	public abstract Path nextPath(Direction direction);

	public abstract String getDirectionInfo();

	protected abstract void initCoordinates();


	/**
	 * 	Return true if d is valid for this Path, return false and
	 * 	print an error otherwise.
	 * @param direction a direction
	 * @return if the exit exists at a certain direction
	 */
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
	@Override
	public String toString() {
		return "Path";
	}

}
