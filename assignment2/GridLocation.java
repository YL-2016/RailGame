// The (x,y) location on the Map.
public class GridLocation {
	private int row;
	private int col;

	public GridLocation(int xPos, int yPos) {
		row = xPos;
		col = yPos;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return (row + " " + col);
	}

}
