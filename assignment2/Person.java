/*

The Person class.

A person has a delay, which is directly related to the speed -- the faster
the person is moving, the shorter the delay. Each turn, a Person will move
one path piece in the current direction.

 */
public class Person extends Thread {

	// The amount of time between each of my turns.
	private long delay;

	// The TreasureHunter this person represents
	private TreasureHunter treasureHunter;

	// The Map on which I am running.
	private Map theMap;

	/**
	 * Create a person with specified id and speed
	 * @param threadName a treadName
	 * @param id a person id
	 * @param speed a certain speed
	 */
	public Person(String threadName, int id, long speed) {
		super(threadName);
		treasureHunter = new TreasureHunter(id);
		delay = speed;
	}

	/**
	 *
	 * @return the person's score
	 */
	public String getScoreInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("Player " + treasureHunter.getId() + ": ").append(
				treasureHunter.getScore());

		return sb.toString();
	}



	/**
	 * Add me to Map T at location loc moving in direction dir.
	 * @param map the Map i am in
	 * @param dir a direction
	 * @param loc a location
	 */
	public void addIntoMap(Map map, Direction dir, GridLocation loc) {
		treasureHunter.setDirection(dir);

		theMap = map;
		theMap.addPerson(this);
		theMap.setHunderInfo(loc, treasureHunter);
	}


	/**
	 * Halve my delay.
	 */
	public void accelerateALot() {
		if (delay / 2 >= 1) {
			delay /= 2;
		}
	}


	/**
	 * Double my delay.
	 */
	public void decelerateALot() {
		delay *= 2;
	}


	/**
	 * Speed up by a factor of 20ms.
	 */
	public void accelerate() {
		if (delay - 20 >= 1) {
			delay -= 20;
		}
	}


	/**
	 * Slow down by a factor of 20ms.
	 */
	public void decelerate() {
		delay += 20;
	}

	/**
	 * move the Person based on the delay
	 */
	public void run() {
		while (true) {
			treasureHunter.move();

			try {
				sleep(delay);
			} catch (InterruptedException e) {

			}
		}
	}

}
