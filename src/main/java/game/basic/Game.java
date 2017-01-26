/**
 * 
 */
package game.basic;

import java.util.List;

/**
 * This class is the core of the Game 2048
 * 
 * @author MOS
 *
 */
public class Game {

	private static final int NUMBER_OF_ACTIONS = 4;
	private GameGrid grid;
	private GameState currentGameState;
	private int scoreForAction;

	/**
	 * ************************************************** Constructors
	 */

	/**
	 * Constructor to start a game
	 * 
	 * @param addStartTiles
	 *            Boolean value that indicates, if tiles should be added
	 */
	public Game(boolean addStartTiles) {
		setup();
		if (addStartTiles) {
			addStartTiles();
		}
	}

	/**
	 * ************************************************** Public Methods
	 */

	/**
	 * 
	 * @param grid
	 * @param state
	 */
	public Game(GameGrid grid, GameState state) {
		this.currentGameState = state;
		this.grid = grid;
	}


	/**
	 * 
	 * @return Grid of the game as 2-dimensional double array
	 */
	public double[][] getGameGridAsDoubleArray() {
		return grid.getGameGridAsDoubleArray();
	}

	/**
	 * 
	 * @return Grid of the game as 1-dimensional double array
	 */
	public double[] getGameGridAsLinearDoubleArray() {
		return grid.getGameGridAsLinearDoubleArray();
	}

	/**
	 * 
	 * @return Grid of the game as 1-dimensional int array
	 */
	public int[][] getGameGridAsIntArray() {
		return grid.getGameGridAsIntArray();
	}

	/**
	 * Performs the given action (0: up, 1: right, 2: down, 3: left)
	 * 
	 * @param direction
	 * @return A boolean value that indicates, if the action has been performed
	 */
	public boolean performAction(int direction) {
		// 0: up, 1: right, 2: down, 3: left

		scoreForAction = 0;

		boolean hasSummedUp = sumUp(direction);
		boolean hasPerformedAction = move(direction);

		currentGameState.addScoreForAction(scoreForAction);

		return hasPerformedAction || hasSummedUp;
	}

	public void addStartTiles() {
		for (int i = 0; i < GameConfiguration.START_TITLES; i++) {
			this.addRandomTile();
		}
	}

	public void addRandomTile() {
		if (grid.cellsAvailable()) {
			int value = Math.random() < 0.8 ? 2 : 4;
			GameTile tile = new GameTile(value);

			grid.insertTileAtRandomPosition(tile);
		}
	}

	public GameGrid getGrid() {
		return grid;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

	public boolean isOver() {
		if (!grid.cellsAvailable() && possibleMoves().isEmpty()) {
			return true;
		}

		return false;
	}

	public List<Integer> possibleMoves() {
		return GameUtil.joinMoves(grid.getPossibleMovesForMoving(),
				grid.getPossibleMovesForSumUp());
	}

	public int simulateAction(int direction) {
		return simulateSumUp(direction);
	}

	public double getScoreGainOfLastAction() {
		return currentGameState.getScoreOfLastAction();
	}

	@Override
	public String toString() {
		return "Game: " + grid.toString();
	}

	public int getNumberOfActions() {
		return NUMBER_OF_ACTIONS;
	}

	/**
	 * ************************************************** Private Methods
	 */

	private void setup() {
		grid = new GameGrid();
		currentGameState = new GameState();
		scoreForAction = 0;
	}

	private boolean sumUp(int direction) {
		List<GameCell> farthestCells = grid.findFarthestPositions(direction);
		boolean hasSummedUp = false;

		for (GameCell farthestCell : farthestCells) {
			GameTile tile = farthestCell.getTile();
			GameTile next = grid.getNextTile(tile.getIndexRow(),
					tile.getIndexColumn(), direction);

			while (next != null) {
				if (tile.getValue() == next.getValue()) {
					tile.doubleValue();
					scoreForAction += tile.getValue();
					grid.removeTile(next.getIndexRow(), next.getIndexColumn());
					tile = grid.getNextTile(next.getIndexRow(),
							next.getIndexColumn(), direction);
					hasSummedUp = true;
				} else {
					tile = grid.getNextTile(tile.getIndexRow(),
							tile.getIndexColumn(), direction);
				}

				if (tile != null) {
					next = grid.getNextTile(tile.getIndexRow(),
							tile.getIndexColumn(), direction);
				} else {
					next = null; // no next tile, loop will end
				}
			}
		}
		return hasSummedUp;
	}

	private boolean move(int direction) {
		boolean hasPerformedAction;

		switch (direction) {
		case GameMovement.UP:
			hasPerformedAction = GameMovement.moveUp(grid);
			break;
		case GameMovement.DOWN:
			hasPerformedAction = GameMovement.moveDown(grid);
			break;
		case GameMovement.RIGHT:
			hasPerformedAction = GameMovement.moveRight(grid);
			break;
		case GameMovement.LEFT:
			hasPerformedAction = GameMovement.moveLeft(grid);
			break;
		default:
			hasPerformedAction = false;
		}
		return hasPerformedAction;
	}


	private int simulateSumUp(int direction) {
		List<GameCell> farthestCells = grid.findFarthestPositions(direction);
		int score = 0;

		for (GameCell farthestCell : farthestCells) {
			GameTile tile = farthestCell.getTile();
			GameTile next = grid.getNextTile(tile.getIndexRow(),
					tile.getIndexColumn(), direction);

			while (next != null) {
				if (tile.getValue() == next.getValue()) {
					tile.doubleValue();
					score += tile.getValue();
					tile = grid.getNextTile(next.getIndexRow(),
							next.getIndexColumn(), direction);
				} else {
					tile = grid.getNextTile(tile.getIndexRow(),
							tile.getIndexColumn(), direction);
				}

				if (tile != null) {
					next = grid.getNextTile(tile.getIndexRow(),
							tile.getIndexColumn(), direction);
				} else {
					next = null; // no next tile, loop will end
				}
			}
		}
		return score;
	}


}
