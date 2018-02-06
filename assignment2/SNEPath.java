/*

The SNEPath class.  An SNEPath object has two ends,
which must be opposite each other.

*/

class SNEPath extends SwitchPath
{
    
    public SNEPath(GridLoc loc, Map T) {
        super(new Direction("south"), new Direction("north"), new Direction("east"), loc, T);
        setLoc(loc);
        startAngle = 90;
    }

    public SNEPath(Map T) {
        super(new Direction("south"), new Direction("north"), new Direction("east"), T);
        startAngle = 90;
    }
    
    public void setLoc(GridLoc loc) {
        super.setLoc(loc);
        x1 = 0.5;
        y1 = 0.0;
        x2 = 0.5;
        y2 = 1.0;
        x3 = 0.5;
        y3 = 0.5;
    }

    public String toString() { return "SNEPath"; };

}

