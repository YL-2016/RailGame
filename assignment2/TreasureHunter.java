import java.awt.*;
import java.awt.geom.Ellipse2D;

/*

 The TreasureHunter class. It has c, and can draw()
 and move().

 */
public class TreasureHunter {

	// My c.
	private Color color;

	// My score.
	private int score;

	// My ID number.
	private int id;

	// The Path that I am currently occupying.
	private Path currentPath;

	// The direction in which I entered the current Path.
	private Direction dir;

	/**
	 * Create a TreasureHunter with a specified id
	 * and set the color to blue and initialize the score to 0
	 * @param id the id
	 */
	public TreasureHunter(int id) {
		this.id = id;
		color = Color.blue;
		score = 0;
	}

	/**
	 * return the TreasureHunter's id
	 * @return the TreasureHunter's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Add up the score
	 */
	public void plusOneScore() {
		++score;
	}

	/**
	 * return the TreasureHunter's score
	 * @returnt he TreasureHunter's score
	 */
	public int getScore() {
		return score;
	}


	/**
	 * 	Set me moving in direction d.
	 * @param direction a direction
	 */
	public void setDirection(Direction direction) {
		dir = direction;
	}

	/**
	 * return the direction
	 * @return the direction
	 */
	public Direction getDirection() {
		return dir;
	}


	/**
	 * Place this TreasureHunter on Path r.
	 * @param path a Path
	 */
	public void setCurrentPath(Path path) {
		currentPath = path;
	}

	/**
	 * return the currentPath the TreasureHunter in
	 * @return the currentPath the TreasureHunter in
	 */
	public Path getCurrentPath() {
		return currentPath;
	}


	/**
	 * Move forward one PathPiece; t is the current PathPiece.
	 * Tell all of the cars I am pulling to move as well.
	 */
	public void move() {
		Direction nextDir = currentPath.exit(dir).getOpposite();
		Path nextPath = currentPath.nextPath(dir);

		if (nextPath.enter(this)) {
			dir = nextDir;
			currentPath.leave();
			currentPath = nextPath;

			// We have to call this here rather than within currentPath.enter()
			// because otherwise the wrong Path is used...
			currentPath.repaint();
		}
	}


	/**
	 * Redraw myself.
	 * @param g the Greaphics
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Rectangle b = currentPath.getBounds();

		double width = b.width;
		double height = b.height;

		Ellipse2D circle = new Ellipse2D.Double(width / 3, height / 3,
				width / 2, height / 2);

		g2.setColor(color);
		g2.fill(circle);
		g2.setStroke(new BasicStroke(2));
		g2.draw(circle);

		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(5));
		g2.setFont(new Font("default", Font.BOLD, 16));
		g2.drawString(new Integer(id).toString(), (int) width / 2,
				(int) height / 2);
	}

	@Override
	public String toString() {
		return "TreasureHunter " + id;
	}

}
