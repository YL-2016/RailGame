/*

The Map class.  A Map object is made up of Paths, and
has zero or more people in it.

 */

import java.awt.*;

public class Map extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4332879029912359649L;

	// The Panel on which the Map appears.
	private MapPanel mapPanel;

	// ------------------------------------------------------------------
	// The following items are the Paths and TreasureHunters on the Map.

	// The maximum number of people I can hold.
	private final static int MAX_PATHS = 10;
	private final static int MAX_PERSONS = 10;
	// The people running on me.
	private Person[] personList = new Person[MAX_PERSONS];
	private int numberOfPersons = 0;

	private Path[][] paths; // The grid of paths.
	// ------------------------------------------------------------------
	// The following buttons define the interface for running
	// and saving me.
	private Button runStopButton, quitButton;

	// The following buttons are used to control the TreasureHunters.
	private Button accelButton, decelButton, accelLotsButton, decelLotsButton;

	// The following label is used to display the scores of the TreasureHunters.
	private Label statusLabel;

	// Set up a new, simple Map.
	public Map() {
		buildGameLayout();
		spawnPaths();
		spawnTreasure(5, 3);
	}

	private void spawnPaths() {
		paths = new Path[MAX_PATHS][MAX_PATHS];

		paths[0][0] = new SEPath(new GridLocation(0, 0), this);
		paths[0][1] = new EWPath(new GridLocation(0, 1), this);
		paths[0][2] = new SWPath(new GridLocation(0, 2), this);
		paths[1][2] = new NSPath(new GridLocation(1, 2), this);
		paths[2][2] = new CrossPath(new GridLocation(2, 2), this);
		paths[2][3] = new EWPath(new GridLocation(2, 3), this);
		paths[2][4] = new EWPath(new GridLocation(2, 4), this);
		paths[2][5] = new CrossPath(new GridLocation(2, 5), this);
		paths[2][6] = new SWPath(new GridLocation(2, 6), this);
		paths[3][6] = new NWPath(new GridLocation(3, 6), this);
		paths[3][5] = new NEPath(new GridLocation(3, 5), this);
		paths[1][5] = new SEPath(new GridLocation(1, 5), this);
		paths[1][6] = new EWPath(new GridLocation(1, 6), this);
		paths[1][7] = new SWPath(new GridLocation(1, 7), this);
		paths[2][7] = new NSPath(new GridLocation(2, 7), this);
		paths[3][7] = new NSPath(new GridLocation(3, 7), this);
		paths[4][7] = new NWPath(new GridLocation(4, 7), this);
		paths[4][6] = new EWPath(new GridLocation(4, 6), this);
		paths[4][5] = new WESPath(new GridLocation(4, 5), this);
		paths[4][4] = new NEPath(new GridLocation(4, 4), this);
		paths[3][4] = new SWPath(new GridLocation(3, 4), this);
		paths[3][3] = new EWSPath(new GridLocation(3, 3), this);
		paths[4][3] = new SNWPath(new GridLocation(4, 3), this);
		paths[5][3] = new NEPath(new GridLocation(5, 3), this);
		paths[5][4] = new EWPath(new GridLocation(5, 4), this);
		paths[5][5] = new NWPath(new GridLocation(5, 5), this);
		paths[3][2] = new CrossPath(new GridLocation(3, 2), this);
		paths[4][2] = new WENPath(new GridLocation(4, 2), this);
		paths[4][1] = new NEPath(new GridLocation(4, 1), this);
		paths[3][1] = new SEPath(new GridLocation(3, 1), this);
		paths[2][1] = new EWPath(new GridLocation(2, 1), this);
		paths[2][0] = new NEPath(new GridLocation(2, 0), this);
		paths[1][0] = new NSPath(new GridLocation(1, 0), this);

		for (int row = 0; row < paths.length; row++) {
			for (int col = 0; col < paths[0].length; col++) {
				if (paths[row][col] == null) {
					paths[row][col] = new EmptyPath(this);
				}
			}
		}

		for (int row = 0; row < paths.length; row++) {
			for (int col = 0; col < paths[0].length; col++) {
				connectPath(row, col);
			}
		}

		mapPanel.addToPanel(paths);
	}

	// Add the buttons for placing Paths.
	private void buildGameLayout() {
		mapPanel = new MapPanel();
		mapPanel.setBackground(new Color(152, 251, 152));
		add("Center", mapPanel);

		runStopButton = new Button("Run");
		quitButton = new Button("Quit");
		accelButton = new Button("Accelerate");
		decelButton = new Button("Decelerate");
		accelLotsButton = new Button("Accelerate A Lot");
		decelLotsButton = new Button("Decelerate A Lot");

		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(0, 1));
		p2.add(runStopButton);
		p2.add(accelLotsButton);
		p2.add(decelLotsButton);
		p2.add(accelButton);
		p2.add(decelButton);
		p2.add(quitButton);
		add("East", p2);

		statusLabel = new Label("Player 1: 0 --- Player 2: 0");

		Panel p3 = new Panel();
		p3.add(statusLabel);
		add("South", p3);

		pack();
	}

	// Read Path-placing commands from the user.
	@Override
	public boolean handleEvent(Event evt) {
		Object target = evt.target;

		if (evt.id == Event.ACTION_EVENT) {
			if (target instanceof Button) {
				if ("Run".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].start();
					}
					((Button) target).setLabel("Suspend");
				} else if ("Suspend".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].suspend();
					}
					((Button) target).setLabel("Resume");
				} else if ("Resume".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].resume();
					}
					((Button) target).setLabel("Suspend");
				} else if ("Accelerate".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].accelerate();
					}
				} else if ("Decelerate".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].decelerate();
					}
				} else if ("Accelerate A Lot".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].accelerateALot();
					}
				} else if ("Decelerate A Lot".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].decelerateALot();
					}
				} else if ("Quit".equals(evt.arg)) {
					for (int i = 0; i < numberOfPersons; i++) {
						personList[i].stop();
					}
					System.exit(0);
				}

				return true;
			}
		}

		// If we get this far, I couldn't handle the event
		return false;
	}

	// If test and r1 != null and r1.exitOK(r1), connect or unrester
	// r1 and r2 depending on whether r2's exits match r1's.
	private void registerOrUnRegister(boolean flag, Path r1, Path r2,
			Direction d) {
		if (flag && r1 != null && r1.exitOK(d)) {
			if (r2.exitOK(d.getOpposite())) {
				connectPaths(r1, r2, d);
			} else {
				r1.unRegister(d);
			}
		}
	}

	// Connect the Path at (row, col) to its neighbours.
	private void connectPath(int row, int col) {
		Path r = paths[row][col];

		Direction north = new Direction(DirEnum.NORTH);
		Direction south = new Direction(DirEnum.SOUTH);
		Direction east = new Direction(DirEnum.EAST);
		Direction west = new Direction(DirEnum.WEST);

		if (r != null) {
			Path rN = row > 0 ? paths[row - 1][col] : null;
			Path rS = row < paths.length - 1 ? paths[row + 1][col] : null;
			Path rE = col < paths[0].length - 1 ? paths[row][col + 1] : null;
			Path rW = col > 0 ? paths[row][col - 1] : null;

			registerOrUnRegister(row > 0, rN, r, south);
			registerOrUnRegister(row < paths.length - 1, rS, r, north);
			registerOrUnRegister(col > 0, rW, r, east);
			registerOrUnRegister(col < paths[0].length - 1, rE, r, west);
		}
	}

	// Connect paths r1 and r2; r2 is in direction d from r1.
	private void connectPaths(Path path1, Path path2, Direction direction) {
		path1.register(path2, direction);
		path2.register(path1, direction.getOpposite());
	}

	// Add e to the path at location loc.
	public void setHunderInfo(GridLocation loc, TreasureHunter hunter) {
		int row = loc.getRow();
		int col = loc.getCol();

		paths[row][col].enter(hunter);
		hunter.setPath(paths[row][col]);
	}

	// paint
	// ------------------------------------------------------------------
	// Paint the display.
	@Override
	public void paint(Graphics g) {
		update(g);
	}

	// update
	// ------------------------------------------------------------------
	// Update the display; tell all my Paths to update themselves.
	@Override
	public void update(Graphics g) {
		mapPanel.repaint();
	}

	// Add T to myself.
	public void addPerson(Person person) {
		personList[numberOfPersons++] = person;
	}

	public void updateStatusBar() {
		statusLabel.setText(personList[0].getScoreInfo() + " --- "
				+ personList[1].getScoreInfo());
		statusLabel.repaint();
	}

	// Spawn treasure in a random location on the path
	public void spawnTreasure() {
		int row, col;
		// find suitable place for treasure to respawn
		do {
			row = (int) (Math.random() * MAX_PATHS);
			col = (int) (Math.random() * MAX_PATHS);
		} while ((paths[row][col] instanceof EmptyPath));
		spawnTreasure(row, col);
	}

	private void spawnTreasure(int row, int col) {
		paths[row][col].placeTreasure();
		paths[row][col].repaint();
	}

}
