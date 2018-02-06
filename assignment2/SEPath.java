/*

The SEPath class.  A SEPath object has ends at the south
and east.

*/

class SEPath extends CornerPath
{
            
    public SEPath(GridLoc loc, Map T) {
        super(new Direction("south"), new Direction("east"), loc, T);
        setLoc(loc);
        startAngle = 90;
    }
    
    public SEPath(Map T) {
        super(new Direction("south"), new Direction("east"), T);
        startAngle = 90;
    }
    
    public void setLoc(GridLoc loc) {
        super.setLoc(loc);
        x1 = 0.5;
        y1 = 0.5;
    }

    public String toString() { return "SEPath"; };
}

