package game.move.simple;

import static org.junit.Assert.assertEquals;
import game.basic.Game;
import game.basic.GameCell;
import game.basic.GameConfiguration;
import game.basic.GameGrid;
import game.basic.GameMovement;
import game.basic.GameState;
import game.basic.GameTile;
import game.basic.io.OutputManager;

import org.junit.Before;
import org.junit.Test;

public class TestGameSimpleMovement9 {

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
		// - - - 4
		// - - - 2
		// 2 - 4 8

		cells[1][3].setTile(new GameTile(4));

		cells[2][3].setTile(new GameTile(2));

		cells[3][0].setTile(new GameTile(2));
		cells[3][2].setTile(new GameTile(4));
		cells[3][3].setTile(new GameTile(8));

		grid = new GameGrid(cells);
		game = new Game(grid, new GameState());

		System.out.println("Test game grid:");
		OutputManager.printGameField(grid);
	}

	@Test
	public void testMoveRight() {
		game.performAction(GameMovement.RIGHT);

		GameCell[][] cells = grid.getCells();

		// test setting
		// - - - -
		// - - - 4
		// - - - 2
		// 2 - 4 8

		assertEquals(null, cells[0][0].getTile());
		assertEquals(null, cells[0][1].getTile());
		assertEquals(null, cells[0][2].getTile());
		assertEquals(null, cells[0][3].getTile());

		assertEquals(null, cells[1][0].getTile());
		assertEquals(null, cells[1][1].getTile());
		assertEquals(null, cells[1][2].getTile());
		assertEquals(4, cells[1][3].getTile().getValue());

		assertEquals(null, cells[2][0].getTile());
		assertEquals(null, cells[2][1].getTile());
		assertEquals(null, cells[2][2].getTile());
		assertEquals(2, cells[2][3].getTile().getValue());

		assertEquals(null, cells[3][0].getTile());
		assertEquals(2, cells[3][1].getTile().getValue());
		assertEquals(4, cells[3][2].getTile().getValue());
		assertEquals(8, cells[3][3].getTile().getValue());
	}

	@Test
	public void testMoveLeft() {
		game.performAction(GameMovement.LEFT);

		// test setting
		// - - - -
		// - - - 4
		// - - - 2
		// 2 - 4 8

		GameCell[][] cells = grid.getCells();

		assertEquals(null, cells[0][0].getTile());
		assertEquals(null, cells[0][1].getTile());
		assertEquals(null, cells[0][2].getTile());
		assertEquals(null, cells[0][3].getTile());

		assertEquals(4, cells[1][0].getTile().getValue());
		assertEquals(null, cells[1][1].getTile());
		assertEquals(null, cells[1][2].getTile());
		assertEquals(null, cells[1][3].getTile());

		assertEquals(2, cells[2][0].getTile().getValue());
		assertEquals(null, cells[2][1].getTile());
		assertEquals(null, cells[2][2].getTile());
		assertEquals(null, cells[2][3].getTile());

		assertEquals(2, cells[3][0].getTile().getValue());
		assertEquals(4, cells[3][1].getTile().getValue());
		assertEquals(8, cells[3][2].getTile().getValue());
		assertEquals(null, cells[3][3].getTile());
	}

	@Test
	public void testMoveUp() {
		game.performAction(GameMovement.UP);

		// test setting
		// - - - -
		// - - - 4
		// - - - 2
		// 2 - 4 8

		GameCell[][] cells = grid.getCells();

		assertEquals(2, cells[0][0].getTile().getValue());
		assertEquals(null, cells[0][1].getTile());
		assertEquals(4, cells[0][2].getTile().getValue());
		assertEquals(4, cells[0][3].getTile().getValue());

		assertEquals(null, cells[1][0].getTile());
		assertEquals(null, cells[1][1].getTile());
		assertEquals(null, cells[1][2].getTile());
		assertEquals(2, cells[1][3].getTile().getValue());

		assertEquals(null, cells[2][0].getTile());
		assertEquals(null, cells[2][1].getTile());
		assertEquals(null, cells[2][2].getTile());
		assertEquals(8, cells[2][3].getTile().getValue());

		assertEquals(null, cells[3][0].getTile());
		assertEquals(null, cells[3][1].getTile());
		assertEquals(null, cells[3][2].getTile());
		assertEquals(null, cells[3][3].getTile());
	}

	@Test
	public void testMoveDown() {
		game.performAction(GameMovement.DOWN);

		// test setting
		// - - - -
		// - - - 4
		// - - - 2
		// 2 - 4 8

		GameCell[][] cells = grid.getCells();

		assertEquals(null, cells[0][0].getTile());
		assertEquals(null, cells[0][1].getTile());
		assertEquals(null, cells[0][2].getTile());
		assertEquals(null, cells[0][3].getTile());

		assertEquals(null, cells[1][0].getTile());
		assertEquals(null, cells[1][1].getTile());
		assertEquals(null, cells[1][2].getTile());
		assertEquals(4, cells[1][3].getTile().getValue());

		assertEquals(null, cells[2][0].getTile());
		assertEquals(null, cells[2][1].getTile());
		assertEquals(null, cells[2][2].getTile());
		assertEquals(2, cells[2][3].getTile().getValue());

		assertEquals(2, cells[3][0].getTile().getValue());
		assertEquals(null, cells[3][1].getTile());
		assertEquals(4, cells[3][2].getTile().getValue());
		assertEquals(8, cells[3][3].getTile().getValue());
	}

}
