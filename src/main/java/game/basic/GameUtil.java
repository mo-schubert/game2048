/**
 * 
 */
package game.basic;

import java.util.List;

/**
 * @author MOS
 *
 */
public class GameUtil {

	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;

	private GameUtil() {
	}

	// Get the vector representing the chosen direction
	public static int[] getVector(int direction) {
		int[] vector = new int[] { 0, 0 };

		switch (direction) {
		case UP:
			vector[0] = 0;
			vector[1] = -1;
			break;
		case RIGHT:
			vector[0] = 1;
			vector[1] = 0;
			break;
		case DOWN:
			vector[0] = 0;
			vector[1] = 1;
			break;
		case LEFT:
			vector[0] = -1;
			vector[1] = 0;
			break;
		default:
			vector[0] = 0;
			vector[1] = 0;

		}

		return vector;
	}

	public static String getActionAsString(int action) {
		switch (action) {
		case GameMovement.UP:
			return "up";
		case GameMovement.DOWN:
			return "down";
		case GameMovement.RIGHT:
			return "right";
		case GameMovement.LEFT:
			return "left";
		default:
			return "****************************** not known action " + action;
		}
	}

	static List<Integer> joinMoves(List<Integer> moves1, List<Integer> moves2) {
		for (Integer move : moves2) {
			if (!moves1.contains(move)) {
				moves1.add(move);
			}
		}
	
		return moves1;
	}

}
