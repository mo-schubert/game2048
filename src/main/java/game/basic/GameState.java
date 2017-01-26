/**
 * 
 */
package game.basic;

/**
 * @author MOS
 *
 */
public class GameState {

	private int score;
	private int scoreOfLastAction;

	public GameState() {
		this.score = 0;
		this.scoreOfLastAction = 0;
	}

	public GameState(int score) {
		this.score = score;
		this.scoreOfLastAction = 0;
	}

	public int getScore() {
		return score;
	}

	public boolean hasWon() {
		if (score == 2048) {
			return true;
	}
		return false;
	}

	public void clear() {
		score = 0;
	}

	public void addScoreForAction(int score) {
		this.score += score;
		this.scoreOfLastAction = score;
	}

	public int getScoreOfLastAction() {
		return scoreOfLastAction;
	}

}
