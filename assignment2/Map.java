/*

The Map class.  A Map object is made up of Paths, and
has zero or more people in it.

*/

import java.awt.*;

public class Map extends Frame
{
    // The Panel on which the Map appears.
    MapPanel mapPanel;

    // ------------------------------------------------------------------
    // The following items are the Paths and TreasureHunters on the Map.

    private Path first;    // The first Path in the graph.
    
    // The maximum number of people I can hold.
    private int MAX_PATHS = 10;
    private Object[] personList = new Object[MAX_PATHS]; // The people running on me.
    private int numPersons = 0;
    
    private Path[][] paths;    // The grid of paths.
    
    // Whether my TreasureHunters are running.  If true, no more Paths can be placed.
    private boolean running = false;

    // ------------------------------------------------------------------
    // The following buttons define the interface for running
    // and saving me.
    private Button runStopButton, quitButton;

    // The following buttons are used to control the TreasureHunters.
    private Button accelButton, decelButton, accelLotsButton, decelLotsButton;

    // The following label is used to display the scores of the TreasureHunters.
    private Label statusLabel;
    
    // Add the buttons for placing Paths.
    protected void buildPath() {
        mapPanel = new MapPanel();
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
    public boolean handleEvent (Event evt) {
        Object target = evt.target;
        
        if (evt.id == Event.ACTION_EVENT) {
            if (target instanceof Button) {
                if ("Run".equals(evt.arg)) {
                    running = true;
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).start();
                    }
                    ((Button)target).setLabel("Suspend");
                } else if ("Suspend".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).suspend();
                    }
                    running = false;
                    ((Button)target).setLabel("Resume");
                } else if ("Resume".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).resume();
                    }
                    running = false;
                    ((Button)target).setLabel("Suspend");
                } else if ("Accelerate".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).accelerate();
                    }
                } else if ("Decelerate".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).decelerate();
                    }
                } else if ("Accelerate A Lot".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).accelerateALot();
                    }
                } else if ("Decelerate A Lot".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).decelerateALot();
                    }
                } else if ("Quit".equals(evt.arg)) {
                    for (int i = 0; i < numPersons; i++) {
                        ((Person)personList[i]).stop();
                    }
                    running = false;
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
    protected void registerOrUnRegister(boolean test, Path r1, Path r2, Direction d) {
        if (test && r1 != null && r1.exitOK(d)) {
            if (r2.exitOK(d.opposite())) {
                connectPaths(r1, r2, d);
            } else {
                r1.unRegister(d);
            }
        }
    }

    // Connect the Path at (row,col) to its neighbours.
    protected void connectPath(int row, int col) {
        Path r = paths[row][col];
        
        Direction north = new Direction("north");
        Direction south = new Direction("south");
        Direction east = new Direction("east");
        Direction west = new Direction("west");
        
        if (r != null) {
            Path rN = row > 0 ? paths[row-1][col] : null;
            Path rS = row < paths.length-1 ? paths[row+1][col] : null;
            Path rE = col < paths[0].length-1 ? paths[row][col+1] : null;
            Path rW = col > 0 ? paths[row][col-1] : null;
            
            registerOrUnRegister(row > 0, rN, r, south);
            registerOrUnRegister(row < paths.length-1, rS, r, north);
            registerOrUnRegister(col > 0, rW, r, east);
            registerOrUnRegister(col < paths[0].length-1, rE, r, west);
        }
    }
    
    
    // Connect paths r1 and r2; r2 is in direction d from r1.
    protected void connectPaths(Path r1, Path r2, Direction d) {
        r1.register(r2, d);
        r2.register(r1, d.opposite());
    }
    
    
    // Add e to the path at location loc.
    public void addCar(GridLoc loc, TreasureHunter e) {
        paths[loc.row][loc.col].enter(e);
        e.setPath(paths[loc.row][loc.col]);
    }

    // paint
    // ------------------------------------------------------------------
    // Paint the display.
    
    public void paint(Graphics g) {
        update(g);
    }

    // update
    // ------------------------------------------------------------------
    // Update the display; tell all my Paths to update themselves.
    
    public void update(Graphics g) {
    
        mapPanel.repaint();
    }

    // Add T to myself.
    public void addPerson(Person T) {
        personList[numPersons] = T;
        numPersons++;
    }

    // Set up a new, simple Map.
    public Map() {
        paths = new Path[10][10];

        buildPath();
        
        for (int row = 0; row < paths.length; row++) {
            for (int col = 0; col < paths[0].length; col++) {
                paths[row][col] = new EmptyPath(this);
            }
        }
            
        paths[0][0] = new SEPath(new GridLoc(0, 0), this);

        paths[0][1] = new EWPath(new GridLoc(0, 1), this);
        connectPaths(paths[0][0], paths[0][1], new Direction("east"));

        paths[0][2] = new SWPath(new GridLoc(0, 2), this);
        connectPaths(paths[0][1], paths[0][2], new Direction("east"));

        paths[1][2] = new NSPath(new GridLoc(1, 2), this);
        connectPaths(paths[0][2], paths[1][2], new Direction("south"));
        
        paths[2][2] = new CrossPath(new GridLoc(2, 2), this);
        connectPaths(paths[1][2], paths[2][2], new Direction("south"));
        
        paths[2][3] = new EWPath(new GridLoc(2, 3), this);
        connectPaths(paths[2][2], paths[2][3], new Direction("east"));
        
        paths[2][4] = new EWPath(new GridLoc(2, 4), this);
        connectPaths(paths[2][3], paths[2][4], new Direction("east"));
        
        paths[2][5] = new CrossPath(new GridLoc(2, 5), this);
        connectPaths(paths[2][4], paths[2][5], new Direction("east"));
        
        paths[2][6] = new SWPath(new GridLoc(2, 6), this);
        connectPaths(paths[2][5], paths[2][6], new Direction("east"));
        
        paths[3][6] = new NWPath(new GridLoc(3, 6), this);
        connectPaths(paths[2][6], paths[3][6], new Direction("south"));
        
        paths[3][5] = new NEPath(new GridLoc(3, 5), this);
        connectPaths(paths[3][6], paths[3][5], new Direction("west"));
        connectPaths(paths[2][5], paths[3][5], new Direction("south"));
        
        paths[1][5] = new SEPath(new GridLoc(1, 5), this);
        connectPaths(paths[2][5], paths[1][5], new Direction("north"));
        
        paths[1][6] = new EWPath(new GridLoc(1, 6), this);
        connectPaths(paths[1][5], paths[1][6], new Direction("east"));
        
        paths[1][7] = new SWPath(new GridLoc(1, 7), this);
        connectPaths(paths[1][6], paths[1][7], new Direction("east"));
        
        paths[2][7] = new NSPath(new GridLoc(2, 7), this);
        connectPaths(paths[1][7], paths[2][7], new Direction("south"));
        
        paths[3][7] = new NSPath(new GridLoc(3, 7), this);
        connectPaths(paths[2][7], paths[3][7], new Direction("south"));
        
        paths[4][7] = new NWPath(new GridLoc(4, 7), this);
        connectPaths(paths[3][7], paths[4][7], new Direction("south"));
        
        paths[4][6] = new EWPath(new GridLoc(4, 6), this);
        connectPaths(paths[4][7], paths[4][6], new Direction("west"));
        
        paths[4][5] = new WESPath(new GridLoc(4, 5), this);
        connectPaths(paths[4][6], paths[4][5], new Direction("west"));
        
        paths[4][4] = new NEPath(new GridLoc(4, 4), this);
        connectPaths(paths[4][5], paths[4][4], new Direction("west"));
        
        paths[3][4] = new SWPath(new GridLoc(3, 4), this);
        connectPaths(paths[4][4], paths[3][4], new Direction("north"));
        
        paths[3][3] = new EWSPath(new GridLoc(3, 3), this);
        connectPaths(paths[3][4], paths[3][3], new Direction("west"));

        paths[4][3] = new SNWPath(new GridLoc(4, 3), this);
        connectPaths(paths[4][3], paths[3][3], new Direction("north"));
        
        paths[5][3] = new NEPath(new GridLoc(5, 3), this);
        connectPaths(paths[5][3], paths[4][3], new Direction("north"));
        
        paths[5][4] = new EWPath(new GridLoc(5, 4), this);
        connectPaths(paths[5][4], paths[5][3], new Direction("west"));
        
        paths[5][5] = new NWPath(new GridLoc(5, 5), this);
        connectPaths(paths[5][5], paths[5][4], new Direction("west"));
        connectPaths(paths[5][5], paths[4][5], new Direction("north"));
        
        // These are just put there to see what they look like.
//        paths[6][0] = new EWNPath(new GridLoc(6, 0), this);
//        paths[6][1] = new EWSPath(new GridLoc(6, 1), this);
//        paths[6][2] = new WENPath(new GridLoc(6, 2), this);
//        paths[6][3] = new WESPath(new GridLoc(6, 3), this);
//        paths[6][4] = new NSEPath(new GridLoc(6, 4), this);
//        paths[6][5] = new NSWPath(new GridLoc(6, 5), this);
//        paths[6][6] = new SNEPath(new GridLoc(6, 6), this);
//        paths[6][7] = new SNWPath(new GridLoc(6, 7), this);
// ------------------------------------------------------------------------------
        
       
        
        paths[3][2] = new CrossPath(new GridLoc(3, 2), this);
        connectPaths(paths[3][3], paths[3][2], new Direction("west"));
        connectPaths(paths[2][2], paths[3][2], new Direction("south"));

        paths[4][2] = new WENPath(new GridLoc(4, 2), this);
        connectPaths(paths[3][2], paths[4][2], new Direction("south"));
        connectPaths(paths[4][3], paths[4][2], new Direction("west"));

        paths[4][1] = new NEPath(new GridLoc(4, 1), this);
        connectPaths(paths[4][2], paths[4][1], new Direction("west"));

        paths[3][1] = new SEPath(new GridLoc(3, 1), this);
        connectPaths(paths[3][2], paths[3][1], new Direction("west"));
        connectPaths(paths[3][1], paths[4][1], new Direction("south"));
        
        paths[2][1] = new EWPath(new GridLoc(2, 1), this);
        connectPaths(paths[2][2], paths[2][1], new Direction("west"));

        paths[2][0] = new NEPath(new GridLoc(2, 0), this);
        connectPaths(paths[2][1], paths[2][0], new Direction("west"));

        paths[1][0] = new NSPath(new GridLoc(1, 0), this);
        connectPaths(paths[2][0], paths[1][0], new Direction("north"));
        connectPaths(paths[1][0], paths[0][0], new Direction("north"));

        spawnTreasure(5, 3);

        mapPanel.addToPanel(paths);
        mapPanel.setBackground(new Color(152,251,152));
    }


    public void updateStatusBar(){
        System.out.println(((Person)personList[0]).getScore());
        System.out.println(((Person)personList[1]).getScore());
        statusLabel.setText("Player 1: " + ((Person)personList[0]).getScore() +
                " --- Player 2: " + ((Person)personList[1]).getScore());

        statusLabel.repaint();
    }

    // Spawn treasure in a random location on the path
    public void spawnTreasure(){
        int row, col;
        //find suitable place for treasure to respawn
        do {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);

            System.out.println("Selected Row: " + row + " Col: " + col);
        } while((paths[row][col] instanceof EmptyPath));

        spawnTreasure(row, col);
    }

    public void spawnTreasure(int row, int col){
        paths[row][col].hT = true;
        paths[row][col].repaint();
    }

}
