/*

The NSPath class.  A NSPath object has two ends,
which must be opposite each other.

*/

class NSPath extends StraightPath
{
    
    public NSPath(GridLoc loc, Map T) {
        super(new Direction("north"), new Direction("south"), loc, T);
        setLoc(loc);
    }
    
    public NSPath(Map T) {
        super(new Direction("north"), new Direction("south"), T);
    }
    
    public void setLoc(GridLoc loc) {
        super.setLoc(loc);
        x1 = 0.5;
        y1 = 0.0;
        x2 = 0.5;
        y2 = 1.0;
    }

    public String toString() { return "NSPath"; };
}

