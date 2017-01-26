package game.basic;

public class GameTile {

	private int value;
	private int[] position;

	public GameTile(int value) {
		this.value = value;
		this.position = new int[2];
	}

	public void setPosition(int indexRow, int indexColumn) {
		position[0] = indexRow;
		position[1] = indexColumn;
	}

	public int getValue() {
		return value;
	}

	public void doubleValue() {
		value *= 2;
	}

	public int[] getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "position: " + position[0] + "-" + position[1] + ", value: "
				+ value;
	}

	public int getIndexRow() {
		return position[0];
	}

	public int getIndexColumn() {
		return position[1];
	}

}
