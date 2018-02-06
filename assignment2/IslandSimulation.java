// Simulate people running around a path.

import java.awt.*;

// class IslandSimulation
// ----------------------------------------------------------------------
// The class IslandSimulation contains all the methods and instance variables
// neccessary to keep track of and run the island simulation.

public class IslandSimulation extends Frame
{
   
    // The Paths on which the Persons run.
    public static Map[] maps = new Map[4];
    
    // The Person objects running on the Paths.
    public static Person[] people = new Person[8];
    
    // The ThreadGroups; all Person objects running on the same Map are in the same
    // ThreadGroup.
    ThreadGroup[] TG = new ThreadGroup[8];

    
    // main
    // ------------------------------------------------------------------
    // This is where it all starts.
    
    public static void main (String[] args) {
    
        IslandSimulation island = new IslandSimulation();
        
        // Map 2.
        island.maps[0] = new Map();
        island.maps[0].resize (540, 400);
        island.maps[0].move(0, 0);
        island.maps[0].setBackground(Color.white);
        island.maps[0].show();

        island.people[0] = new Person1(1);

        island.people[1] = new Person2(2);

        island.people[0].addToPath(island.maps[0], new Direction("east"), new GridLoc(2, 2));
        island.people[0].setSpeed(620);
        island.people[1].addToPath(island.maps[0], new Direction("south"), new GridLoc(1, 5));
        island.people[1].setSpeed(350);

        island.people[0].start();
        island.people[1].start();

    }
}

