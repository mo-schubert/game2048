/**
 * 
 */
package game.basic;

import game.basic.io.InputManager;
import game.basic.io.OutputManager;

/**
 * @author MOS
 *
 */
public class GameManager {

	private StorageManager storageManager;
	private Game game;

	public GameManager() {
		this.storageManager = new StorageManager();
	}

	public void restart() {
		storageManager.clearGameState();
		setupGame();
	}


	public void run() {
		int aktion;
		boolean hasPerformedAction;
		
		setupGame();

		while (!game.isOver()) {
			aktion = InputManager.readActionFromConsole();
			
			hasPerformedAction = game.performAction(aktion);
			if (hasPerformedAction) {
				game.addRandomTile();
			} else {
				OutputManager.printActionCouldNotBePerformed();
			}
			OutputManager.printGameField(game.getGrid());
		}

		OutputManager.printGameOver();
	}

	private void setupGame() {
		game = new Game(true);
		OutputManager.printGameField(game.getGrid());
	}

}
