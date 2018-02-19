import java.awt.*;

public class MapPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4723290168915826378L;

	// The buffer in which to draw the image; used for double buffering.
	private Image backBuffer;

	// The graphics context to use when double buffering.
	private Graphics backG;

	// The grid of paths.
	private Path[][] paths;

	public MapPanel() {
	}

	public void addToPanel(Path[][] pathes) {
		paths = pathes;
		setLayout(new GridLayout(paths.length, paths[0].length, 0, 0));
		for (int row = 0; row < paths.length; row++) {
			for (int col = 0; col < paths[0].length; col++) {
				add("", paths[row][col]);
			}
		}
	}

	// paint
	// ------------------------------------------------------------------
	// Paint the display.
	@Override
	public void paint(Graphics g) {
		update(g);
	}

	@Override
	public Insets insets() {
		return new Insets(10, 10, 10, 10);
	}

	// update
	// ------------------------------------------------------------------
	// Update the display; tell all my Paths to update themselves.
	@Override
	public void update(Graphics g) {
		Rectangle rec = getBounds();
		// Get my width and height.
		int w = rec.width;
		int h = rec.height;

		// If we don't yet have an Image, create one.
		if (backBuffer == null || backBuffer.getWidth(null) != w
				|| backBuffer.getHeight(null) != h) {
			backBuffer = createImage(w, h);
			if (backBuffer != null) {

				// If we have a backG, it belonged to an old Image.
				// Get rid of it.
				if (backG != null) {
					backG.dispose();
				}
				backG = backBuffer.getGraphics();
			}
		}

		if (backBuffer != null) {
			// Fill in the Graphics context backG.
			g.setColor(Color.white);
			g.fillRect(0, 0, w, h);

			// Now copy the new image to g.
			// g.drawImage(backBuffer, 0, 0, null);
		}
	}

}
