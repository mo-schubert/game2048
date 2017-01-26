package game.possibleMoves;

import static org.junit.Assert.assertEquals;
import game.basic.Game;
import game.basic.GameCell;
import game.basic.GameConfiguration;
import game.basic.GameGrid;
import game.basic.GameMovement;
import game.basic.GameState;
import game.basic.GameTile;
import game.basic.io.OutputManager;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestGamePossibleMoves8 {

	private GameGrid grid;

	private Game game;

	@Before
	public void setUp() throws Exception {
		GameCell[][] cells = new GameCell[GameConfiguration.SIZE][GameConfiguration.SIZE];

		for (int indexRow = 0; indexRow < GameConfiguration.SIZE; indexRow++) {
			for (int indexColumn = 0; indexColumn < GameConfiguration.SIZE; indexColumn++) {
				cells[indexRow][indexColumn] = new GameCell(indexRow,
						indexColumn);
			}
		}

		// test setting
		// - - - -
		// - - - -
		// - - - -
		// - - 2 4

		cells[3][2].setTile(new GameTile(2));
		cells[3][3].setTile(new GameTile(4));

		grid = new GameGrid(cells);
		game = new Game(grid, new GameState());

		System.out.println("Test game grid:");
		OutputManager.printGameField(grid);
	}

	@Test
	public void testPossibleMoves() {
		List<Integer> possibleMoves = game.possibleMoves();

		assertEquals(true, possibleMoves.contains(new Integer(GameMovement.LEFT)));
		assertEquals(false, possibleMoves.contains(new Integer(GameMovement.RIGHT)));
		assertEquals(true, possibleMoves.contains(new Integer(GameMovement.UP)));
		assertEquals(false, possibleMoves.contains(new Integer(GameMovement.DOWN)));
	}

}
