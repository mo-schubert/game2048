/**
 * 
 */
package game.basic;

import java.util.List;

/**
 * @author MOS
 *
 */
public class GameMovement {

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;

	private GameMovement() {

	}

	public static boolean moveUp(GameGrid grid) {
		boolean hasPerformedAction = false;
		List<Integer> freePositions;

		for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
			freePositions = grid.getFreePositionsForColumn(indexColumn);

			for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
				Integer index = getLowestIndex(freePositions);
				if ((index < indexRow)
						&& (grid.getCells()[indexRow][indexColumn].isOccupied())) {
					int[] target = { index, indexColumn };
					int[] source = { indexRow, indexColumn };
					grid.moveTile(source, target);
					freePositions.remove(index);
					freePositions.add(indexRow);
					hasPerformedAction = true;
				}
			}
		}

		return hasPerformedAction;
	}

	public static boolean moveDown(GameGrid grid) {
		boolean hasPerformedAction = false;
		List<Integer> freePositions;

		for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
			freePositions = grid.getFreePositionsForColumn(indexColumn);

			for (int indexRow = GameConfiguration.SIZE - 1; indexRow >= 0; indexRow--) {
				Integer index = getHighestIndex(freePositions);
				if ((index > indexRow)
						&& (grid.getCells()[indexRow][indexColumn].isOccupied())) {
					int[] target = { index, indexColumn };
					int[] source = { indexRow, indexColumn };
					grid.moveTile(source, target);
					freePositions.remove(index);
					freePositions.add(indexRow);
					hasPerformedAction = true;
				}
			}
		}

		return hasPerformedAction;
	}

	public static boolean moveLeft(GameGrid grid) {
		boolean hasPerformedAction = false;
		List<Integer> freePositions;

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			freePositions = grid.getFreePositionsForRow(indexRow);

			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
				Integer index = getLowestIndex(freePositions);
				if ((index < indexColumn)
						&& (grid.getCells()[indexRow][indexColumn].isOccupied())) {
					int[] target = { indexRow, index };
					int[] source = { indexRow, indexColumn };
					grid.moveTile(source, target);
					freePositions.remove(index);
					freePositions.add(indexColumn);
					hasPerformedAction = true;
				}
			}
		}

		return hasPerformedAction;
	}

	public static boolean moveRight(GameGrid grid) {
		boolean hasPerformedAction = false;
		List<Integer> freePositions;

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			freePositions = grid.getFreePositionsForRow(indexRow);

			for (int indexColumn = GameConfiguration.SIZE - 1; indexColumn >= 0; indexColumn--) {
				Integer index = getHighestIndex(freePositions);
				if ((index > indexColumn)
						&& (grid.getCells()[indexRow][indexColumn].isOccupied())) {
					int[] target = { indexRow, index };
					int[] source = { indexRow, indexColumn };
					grid.moveTile(source, target);
					freePositions.remove(index);
					freePositions.add(indexColumn);
					hasPerformedAction = true;
				}
			}
		}

		return hasPerformedAction;
	}

	private static Integer getHighestIndex(List<Integer> list) {
		Integer highestValue = 0;
		for (Integer index : list) {
			if (index > highestValue) {
				highestValue = index;
			}
		}
		return highestValue;
	}

	private static Integer getLowestIndex(List<Integer> list) {
		Integer lowestValue = GameConfiguration.SIZE - 1;
		for (Integer index : list) {
			if (index < lowestValue) {
				lowestValue = index;
			}
		}
		return lowestValue;
	}

}
