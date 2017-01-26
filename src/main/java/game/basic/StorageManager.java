/**
 * 
 */
package game.basic;

/**
 * @author MOS
 *
 */
public class StorageManager {

	private GameState state;
	private int bestScore;

	public StorageManager() {
		bestScore = 0;
		state = null;
	}

	public void clearGameState() {
		state.clear();
	}

	public GameState getGameState() {
		return state;
	}

	public void saveGameState(GameState state) {
		this.state = state;
	}

	public int getBestScore() {
		return bestScore;
	}

	public void saveBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

}
