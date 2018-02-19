// Simulate people running around a path.

import java.awt.*;

// class IslandSimulation
// ----------------------------------------------------------------------
// The class IslandSimulation contains all the methods and instance variables
// neccessary to keep track of and run the island simulation.

public class IslandSimulation {
	// The Paths on which the Persons run.
	private Map map;

	public IslandSimulation() {
		map = new Map();
		map.setSize(540, 400);
		map.setLocation(0, 0);
		map.setBackground(Color.WHITE);
	}

	public void addPlayer(Person player, Direction dir, GridLocation loc) {
		player.addIntoMap(map, dir, loc);
	}

	public void play() {
		map.setVisible(true);
	}

	// main
	// ------------------------------------------------------------------
	// This is where it all starts.
	public static void main(String[] args) {
		Person playerA = new Person("Person 1", 1, 620);
		Person playerB = new Person("Person 2", 2, 350);

		IslandSimulation island = new IslandSimulation();
		island.addPlayer(playerA, new Direction(DirEnum.EAST),
				new GridLocation(2, 2));
		island.addPlayer(playerB, new Direction(DirEnum.SOUTH),
				new GridLocation(1, 5));

		island.play();
	}
	
}
