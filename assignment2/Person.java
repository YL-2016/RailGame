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

	public Person(String threadName, int id) {
		super(threadName);
		treasureHunter = new TreasureHunter(id);
	}

	// Set my delay between moves to d.
	public void setSpeed(long speed) {
		delay = speed;
	}

	public int getScore() {
		return treasureHunter.getScore();
	}

	// Add me to Map T at location loc moving in direction dir.
	public void addToPath(Map map, Direction dir, GridLocation loc) {
		treasureHunter.setDirection(dir);

		theMap = map;
		theMap.addPerson(this);
		theMap.setHunderInfo(loc, treasureHunter);
	}

	// Halve my delay.
	public void accelerateALot() {
		if (delay / 2 >= 1) {
			delay /= 2;
		}
	}

	// Double my delay.
	public void decelerateALot() {
		delay *= 2;
	}

	// Speed up by a factor of 20ms.
	public void accelerate() {
		if (delay - 20 >= 1) {
			delay -= 20;
		}
	}

	// Slow down by a factor of 20ms.
	public void decelerate() {
		delay += 20;
	}

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
