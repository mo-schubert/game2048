/**
 * 
 */
package game.main;

import game.basic.GameManager;

/**
 * @author MOS
 *
 */
public class GameMain {

	private GameMain() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameManager gameManager = new GameManager();
		gameManager.run();
	}
}
