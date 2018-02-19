import java.awt.*;
import java.awt.geom.Ellipse2D;

/*

 The TreasureHunter class. It has weight, c, and can draw()
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

	public TreasureHunter(int id) {
		this.id = id;
		color = Color.blue;
		score = 0;
	}

	public int getId() {
		return id;
	}

	public void plusOneScore() {
		++score;
	}

	public int getScore() {
		return score;
	}

	public void setCurrentPath(Path currentPath) {
		this.currentPath = currentPath;
	}

	// Set me moving in direction d.
	public void setDirection(Direction direction) {
		dir = direction;
	}

	public Direction getDirection() {
		return dir;
	}

	// Place this TreasureHunter on Path r.
	public void setPath(Path path) {
		currentPath = path;
	}

	public Path getCurrentPath() {
		return currentPath;
	}

	// Move forward one PathPiece; t is the current PathPiece. Tell
	// all of the cars I am pulling to move as well.
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

	// Redraw myself.
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
