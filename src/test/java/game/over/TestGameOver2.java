package game.over;

import static org.junit.Assert.assertEquals;
import game.basic.Game;
import game.basic.GameCell;
import game.basic.GameConfiguration;
import game.basic.GameGrid;
import game.basic.GameState;
import game.basic.GameTile;
import game.basic.io.OutputManager;

import org.junit.Before;
import org.junit.Test;

public class TestGameOver2 {

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
		// 2 4 2 4
		// 4 8 4 2
		// 16 8 4 8
		// 4 32 64 32

		cells[0][0].setTile(new GameTile(2));
		cells[0][1].setTile(new GameTile(4));
		cells[0][2].setTile(new GameTile(2));
		cells[0][3].setTile(new GameTile(4));

		cells[1][0].setTile(new GameTile(4));
		cells[1][1].setTile(new GameTile(8));
		cells[1][2].setTile(new GameTile(4));
		cells[1][3].setTile(new GameTile(2));

		cells[2][0].setTile(new GameTile(16));
		cells[2][1].setTile(new GameTile(8));
		cells[2][2].setTile(new GameTile(4));
		cells[2][3].setTile(new GameTile(8));

		cells[3][0].setTile(new GameTile(2));
		cells[3][1].setTile(new GameTile(32));
		cells[3][2].setTile(new GameTile(64));
		cells[3][3].setTile(new GameTile(32));

		grid = new GameGrid(cells);
		game = new Game(grid, new GameState());

		System.out.println("Test game grid:");
		OutputManager.printGameField(grid);
	}

	@Test
	public void testGameOver() {
		assertEquals(false, game.isOver());
	}


}
