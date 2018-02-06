/*

The EWSPath class.  An EWSPath object has three ends.

*/

class EWSPath extends SwitchPath
{
    
    public EWSPath(GridLoc loc, Map T) {
        super(new Direction("east"), new Direction("west"), new Direction("south"), loc, T);
        setLoc(loc);
        startAngle = 90;
    }

    public EWSPath(Map T) {
        super(new Direction("east"), new Direction("west"), new Direction("south"), T);
        startAngle = 90;
    }
    
    public void setLoc(GridLoc loc) {
        super.setLoc(loc);
        x1 = 0.0;
        y1 = 0.5;
        x2 = 1.0;
        y2 = 0.5;
        x3 = 0.5;
        y3 = 0.5;
    }

    public String toString() { return "EWSPath"; };
}

