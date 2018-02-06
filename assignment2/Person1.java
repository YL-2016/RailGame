/*

The Person class.

A person has a delay, which is directly related to the speed -- the faster
the person is moving, the shorter the delay. Each turn, a Person will move
one path piece in the current direction.

*/

public class Person1 extends Person
{

    public Person1(int id) {
        super("Person 1", id);
    }

    // Add me to Map T at location loc moving in direction dir.
    public void addToPath(Map T, Direction dir, GridLoc loc) {
        theMap = T;
        theMap.addPerson(this);

        TreasureHunter currPerson = treasureHunter;
        while (currPerson != null) {
            currPerson.setDirection(dir);
            theMap.addCar(loc, currPerson);

            // Now figure out the dir for the next TreasureHunter,
            // and the next loc.

            if (dir.equals("north")) {
                loc.row--;
            } else if (dir.equals("south")) {
                loc.row++;
            } else if (dir.equals("east")) {
                loc.col++;
            } else if (dir.equals("west")) {
                loc.col--;
            }

            Direction nD = currPerson.currentPath.exit(dir);
            Path nextPath = currPerson.currentPath.nextPath(nD);

            // Now I know the Path on which the next currPerson will
            // be.  Find out how it got on to it.
            dir = nextPath.exit(dir.opposite());

            currPerson = currPerson.nextTreasureHunter;
        }
    }
}

