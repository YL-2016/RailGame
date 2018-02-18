/*

The EmptyPath class.  This is a place on the Map which does not have an actual
piece of path.

 */

public class EmptyPath extends Path {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275207235685976193L;

	public EmptyPath(Map T) {
		super(T);
	}

	@Override
	protected void initCoordinates() {
	}

	@Override
	protected boolean validDir(Direction d) {
		return false;
	}

	@Override
	public boolean exitOK(Direction d) {
		return false;
	}

	@Override
	public void register(Path r, Direction d) {
		return;
	}

	@Override
	public void unRegister(Direction d) {
		return;
	}

	@Override
	public Direction exit(Direction d) {
		return null;
	}

	@Override
	public Path nextPath(Direction d) {
		return null;
	}

	@Override
	public String getDirectionInfo() {
		return "";
	}

}
