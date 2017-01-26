package game.basic;

public class GameCell {

	private GameTile tile;
	private int indexRow;
	private int indexColumn;

	public GameCell(int indexRow, int indexColumn) {
		init(indexRow, indexColumn, null);
	}

	public GameCell(int indexRow, int indexColumn, GameTile tile) {
		init(indexRow, indexColumn, tile);
	}

	private void init(int indexRow, int indexColumn, GameTile tile) {
		this.tile = tile;
		this.indexRow = indexRow;
		this.indexColumn = indexColumn;
	}

	public GameTile getTile() {
		return tile;
	}

	public void setTile(GameTile tile) {
		this.tile = tile;
		tile.setPosition(indexRow, indexColumn);
	}

	public boolean isOccupied() {
		if (tile == null) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		if (tile != null) {
			return tile.getValue() + " ";
		}
		return "- ";
	}

	public void removeTile() {
		tile = null;
	}

	public int getIndexRow() {
		return indexRow;
	}

	public int getIndexColumn() {
		return indexColumn;
	}

}
