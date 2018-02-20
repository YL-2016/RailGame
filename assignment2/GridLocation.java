// The (x,y) location on the Map.
public class GridLocation {
	private int row;
	private int col;

	/**
	 * Create a GridLocation at ceratin row and col data
	 * @param xPos the row data
	 * @param yPos the col data
	 */
	public GridLocation(int xPos, int yPos) {
		row = xPos;
		col = yPos;
	}

	/**
	 * return the row data
	 * @return the row data
	 */
	public int getRow() {
		return row;
	}

	/**
	 * set the row data
	 * @param row the row data
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * return the col data
	 * @return the col data
	 */
	public int getCol() {
		return col;
	}

	/**
	 * set the col data
	 * @param col the col data
	 */
	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return (row + " " + col);
	}

}
