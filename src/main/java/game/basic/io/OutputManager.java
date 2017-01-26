/**
 * 
 */
package game.basic.io;

import game.basic.GameCell;
import game.basic.GameConfiguration;
import game.basic.GameGrid;

/**
 * @author MOS
 *
 */
public class OutputManager {

	private OutputManager() {
	}

	public static void printGameField(GameGrid grid) {
		GameCell[][] cells = grid.getCells();

		for (int i = 0; i < GameConfiguration.SIZE; ++i) {
			for (int j = 0; j < GameConfiguration.SIZE; ++j) {
				System.out.print(cells[i][j].toString());
			}
			System.out.println();
		}
	}

	public static void printActionCouldNotBePerformed() {
		System.out.println("Aktion konnte nicht durchgeführt werden");
	}

	public static void printGameOver() {
		System.out.println("Game over");
	}

}
