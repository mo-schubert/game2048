/**
 * 
 */
package game.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MOS
 *
 */
public class GameGrid {

	private GameCell[][] cells;

	public GameGrid() {
		cells = new GameCell[GameConfiguration.SIZE][GameConfiguration.SIZE];

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; ++indexRow) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; ++indexColumn) {
				cells[indexRow][indexColumn] = new GameCell(indexRow,
						indexColumn);
			}
		}
	}

	public GameGrid(GameCell[][] cells) {
		this.cells = cells;
	}

	public GameCell[][] getCells() {
		return cells;
	}

	public List<GameTile> getTiles() {
		List<GameTile> tiles = new ArrayList<>();
		GameTile tile;

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; ++indexRow) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; ++indexColumn) {
				tile = cells[indexRow][indexColumn].getTile();
				if (tile != null) {
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}

	private boolean isCellAvailable(int indexRow, int indexColumn) {
		if (cells[indexRow][indexColumn].getTile() == null) {
			return true;
		}
		return false;
	}

	public boolean cellsAvailable() {
		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; ++indexRow) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; ++indexColumn) {
				if (isCellAvailable(indexRow, indexColumn)) {
					return true;
				}
			}
		}
		return false;
	}

	public GameCell randomAvailableCell() {
		List<GameCell> availableCells = getAvailableCells();

		int numberOfAvialableCells = availableCells.size();
		int randomCellIndex = (int) (Math.random() * numberOfAvialableCells);
		return availableCells.get(randomCellIndex);
	}

	public void insertTile(GameTile tile, int indexRow, int indexColumn) {
		cells[indexRow][indexColumn].setTile(tile);
	}

	public List<GameCell> getAvailableCells() {
		List<GameCell> availableCells = new ArrayList<>();

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; ++indexRow) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; ++indexColumn) {
				if (isCellAvailable(indexRow, indexColumn)) {
					availableCells.add(cells[indexRow][indexColumn]);
				}
			}
		}
		return availableCells;
	}

	public List<GameCell> getOccupiedCells() {
		List<GameCell> occupiedCells = new ArrayList<>();

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; ++indexRow) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; ++indexColumn) {
				if (!isCellAvailable(indexRow, indexColumn)) {
					occupiedCells.add(cells[indexRow][indexColumn]);
				}
			}
		}
		return occupiedCells;
	}

	public void insertTileAtRandomPosition(GameTile tile) {
		randomAvailableCell().setTile(tile);
	}

	public void removeTile(int indexRow, int indexColumn) {
		cells[indexRow][indexColumn].removeTile();
	}

	public GameTile getNextTile(int indexRow, int indexColumn, int direction) {

		switch (direction) {
		case GameMovement.UP:
			return getNextTileForDirectionUp(indexRow, indexColumn);
		case GameMovement.DOWN:
			return getNextTileForDirectionDown(indexRow, indexColumn);
		case GameMovement.RIGHT:
			return getNextTileForDirectionRight(indexRow, indexColumn);
		case GameMovement.LEFT:
			return getNextTileForDirectionLeft(indexRow, indexColumn);
		default:
			return null;
		}
	}

	private GameTile getNextTileForDirectionUp(int indexRow, int indexColumn) {
		int index = 1;
		GameTile currentTile;

		while ((indexRow + index) < GameConfiguration.SIZE) {
			currentTile = cells[indexRow + index][indexColumn].getTile();
			index++;

			if (currentTile != null) {
				return currentTile;
			}
		}
		return null;
	}

	private GameTile getNextTileForDirectionDown(int indexRow, int indexColumn) {
		int index = 1;
		GameTile currentTile;

		while ((indexRow - index) >= 0) {
			currentTile = cells[indexRow - index][indexColumn].getTile();
			index++;

			if (currentTile != null) {
				return currentTile;
			}
		}
		return null;
	}

	private GameTile getNextTileForDirectionRight(int indexRow, int indexColumn) {
		int index = 1;
		GameTile currentTile;

		while ((indexColumn - index) >= 0) {
			currentTile = cells[indexRow][indexColumn - index].getTile();
			index++;

			if (currentTile != null) {
				return currentTile;
			}
		}
		return null;
	}

	private GameTile getNextTileForDirectionLeft(int indexRow, int indexColumn) {
		int index = 1;
		GameTile currentTile;

		while ((indexColumn + index) < GameConfiguration.SIZE) {
			currentTile = cells[indexRow][indexColumn + index].getTile();
			index++;

			if (currentTile != null) {
				return currentTile;
			}
		}
		return null;
	}

	public GameTile getTile(int[] position) {
		return cells[position[0]][position[1]].getTile();
	}

	public boolean isCellOccupied(int indexRow, int indexColumn) {
		return cells[indexRow][indexColumn].getTile() != null;
	}

	public boolean twoEqualTilesInColumn() {
		for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
			for (int indexRow = 0; indexRow < GameConfiguration.SIZE - 1; indexRow++) {
				if ((cells[indexRow][indexColumn].getTile() != null)
						&& (cells[indexRow + 1][indexColumn].getTile() != null)
						&& cells[indexRow][indexColumn].getTile().getValue() == cells[indexRow + 1][indexColumn]
								.getTile().getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean twoEqualTilesInRow() {
		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE - 1; indexColumn++) {
				if ((cells[indexRow][indexColumn].getTile() != null)
						&& (cells[indexRow][indexColumn + 1].getTile() != null)
						&& cells[indexRow][indexColumn].getTile().getValue() == cells[indexRow][indexColumn + 1]
								.getTile().getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Integer> getPossibleMovesForMoving() {
		List<GameCell> occupiedCells = getOccupiedCells();
		boolean moveRightPossible = false;
		boolean moveLeftPossible = false;
		boolean moveUpPossible = false;
		boolean moveDownPossible = false;
		int indexColumn;
		int indexRow;

		for (GameCell cell : occupiedCells) {
			indexColumn = cell.getIndexColumn();
			indexRow = cell.getIndexRow();

			if (!moveLeftPossible) {
				moveLeftPossible = isMoveLeftPossible(indexColumn, indexRow);
			}
			if (!moveRightPossible) {
				moveRightPossible = isMoveRightPossible(indexColumn, indexRow);
			}
			if (!moveUpPossible) {
				moveUpPossible = isMoveUpPossible(indexColumn, indexRow);
			}
			if (!moveDownPossible) {
				moveDownPossible = isMoveDownPossible(indexColumn, indexRow);
			}
		}

		ArrayList<Integer> moves = new ArrayList<>();
		if (moveRightPossible) {
			moves.add(GameMovement.RIGHT);
		}
		if (moveLeftPossible) {
			moves.add(GameMovement.LEFT);
		}
		if (moveUpPossible) {
			moves.add(GameMovement.UP);
		}
		if (moveDownPossible) {
			moves.add(GameMovement.DOWN);
		}
		return moves;
	}

	private boolean isMoveDownPossible(int indexColumn, int indexRow) {
		if ((indexRow < GameConfiguration.SIZE - 1)
				&& (!getCells()[indexRow + 1][indexColumn].isOccupied())) {
			return true;
		}
		return false;
	}

	private boolean isMoveUpPossible(int indexColumn, int indexRow) {
		if (indexRow > 0 && !getCells()[indexRow - 1][indexColumn].isOccupied()) {
			return true;
		}
		return false;
	}

	private boolean isMoveRightPossible(int indexColumn, int indexRow) {
		if ((indexColumn < GameConfiguration.SIZE - 1)
				&& (!getCells()[indexRow][indexColumn + 1].isOccupied())) {
			return true;
		}
		return false;
	}

	private boolean isMoveLeftPossible(int indexColumn, int indexRow) {
		if (indexColumn > 0
				&& !getCells()[indexRow][indexColumn - 1].isOccupied()) {
			return true;
		}
		return false;
	}

	public List<Integer> getPossibleMovesForSumUp() {
		ArrayList<Integer> possibleMoves = new ArrayList<>();
		if (twoEqualTilesInRow()) {
			possibleMoves.add(GameMovement.RIGHT);
			possibleMoves.add(GameMovement.LEFT);
		}
		if (twoEqualTilesInColumn()) {
			possibleMoves.add(GameMovement.UP);
			possibleMoves.add(GameMovement.DOWN);
		}
		return possibleMoves;
	}

	public List<GameCell> getFarthesLeftPositions() {
		GameCell[][] allCells = getCells();
		List<GameCell> positions = new ArrayList<>();
		GameCell current;

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
				current = allCells[indexRow][indexColumn];
				if (current.getTile() != null) {
					positions.add(current);
					break;
				}
			}
		}

		return positions;
	}

	private List<GameCell> getFarthesRightPositions() {
		GameCell[][] allCells = getCells();
		List<GameCell> positions = new ArrayList<>();
		GameCell current;

		for (int indexRow = GameConfiguration.SIZE - 1; indexRow >= 0; indexRow--) {
			for (int indexColumn = GameConfiguration.SIZE - 1; indexColumn >= 0; indexColumn--) {
				current = allCells[indexRow][indexColumn];
				if (current.getTile() != null) {
					positions.add(current);
					break;
				}
			}
		}

		return positions;
	}

	private List<GameCell> getFarthesDownPositions() {
		GameCell[][] allCells = getCells();
		List<GameCell> positions = new ArrayList<>();
		GameCell current;

		for (int indexColumn = GameConfiguration.SIZE - 1; indexColumn >= 0; indexColumn--) {
			for (int indexRow = GameConfiguration.SIZE - 1; indexRow >= 0; indexRow--) {
				current = allCells[indexRow][indexColumn];
				if (current.getTile() != null) {
					positions.add(current);
					break;
				}
			}
		}

		return positions;
	}

	private List<GameCell> getFarthesUpPositions() {
		GameCell[][] allCells = getCells();
		List<GameCell> positions = new ArrayList<>();
		GameCell current;

		for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
			for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
				current = allCells[indexRow][indexColumn];
				if (current.getTile() != null) {
					positions.add(current);
					break;
				}
			}
		}
		return positions;
	}

	public List<Integer> getFreePositionsForRow(int indexRow) {
		List<Integer> positions = new ArrayList<>();

		for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
			if (!getCells()[indexRow][indexColumn].isOccupied()) {
				positions.add(indexColumn);
			}
		}

		return positions;
	}

	public List<GameCell> findFarthestPositions(int direction) {
		switch (direction) {
		case GameMovement.UP:
			return getFarthesUpPositions();
		case GameMovement.DOWN:
			return getFarthesDownPositions();
		case GameMovement.RIGHT:
			return getFarthesRightPositions();
		case GameMovement.LEFT:
			return getFarthesLeftPositions();
		default:
			return new ArrayList<>();
		}
	}

	public List<Integer> getFreePositionsForColumn(int indexColumn) {
		List<Integer> positions = new ArrayList<>();

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			if (!cells[indexRow][indexColumn].isOccupied()) {
				positions.add(indexRow);
			}
		}

		return positions;
	}

	public void moveTile(int[] positionSource, int[] positionTarget) {
		GameTile tile = getTile(positionSource);
		tile.setPosition(positionTarget[0], positionTarget[1]);
		insertTile(tile, positionTarget[0], positionTarget[1]);
		removeTile(positionSource[0], positionSource[1]);
	}

	public double[][] getGameGridAsDoubleArray() {
		double[][] array = new double[4][4];

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
				if (!cells[indexRow][indexColumn].isOccupied()) {
					array[indexRow][indexColumn] = 0.0;
				} else {
					array[indexRow][indexColumn] = cells[indexRow][indexColumn]
							.getTile().getValue();
				}
			}
		}

		return array;
	}

	public double[] getGameGridAsLinearDoubleArray() {
		int size = GameConfiguration.SIZE * GameConfiguration.SIZE;
		double[] array = new double[size];
		int index = 0;

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
				if (!cells[indexRow][indexColumn].isOccupied()) {
					array[index] = 0.0;
				} else {
					array[index] = cells[indexRow][indexColumn].getTile()
							.getValue();
				}
				index++;
			}
		}

		return array;
	}

	public int[][] getGameGridAsIntArray() {
		int[][] array = new int[4][4];

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
				if (!cells[indexRow][indexColumn].isOccupied()) {
					array[indexRow][indexColumn] = 0;
				} else {
					array[indexRow][indexColumn] = cells[indexRow][indexColumn]
							.getTile().getValue();
				}
			}
		}

		return array;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < GameConfiguration.SIZE; ++i) {
			for (int j = 0; j < GameConfiguration.SIZE; ++j) {
				result.append(cells[i][j].toString() + " ");
			}
			result.append("/");
		}

		return result.toString();
	}

}
