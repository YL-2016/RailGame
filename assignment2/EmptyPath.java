/*

The EmptyPath class.  This is a place on the Map which does not have an actual
piece of path.

 */

public class EmptyPath extends Path {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275207235685976193L;

	public EmptyPath(Map map) {
		super(map);
	}

	@Override
	protected void initCoordinates() {
	}

	@Override
	protected boolean validDir(Direction direction) {
		return false;
	}

	@Override
	public boolean exitOK(Direction direction) {
		return false;
	}

	@Override
	public void register(Path path, Direction direction) {
		return;
	}

	@Override
	public void unRegister(Direction direction) {
		return;
	}

	@Override
	public Direction exit(Direction direction) {
		return null;
	}

	@Override
	public Path nextPath(Direction direction) {
		return null;
	}

	@Override
	public String getDirectionInfo() {
		return "";
	}

}
