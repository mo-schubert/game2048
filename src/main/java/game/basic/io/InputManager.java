/**
 * 
 */
package game.basic.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author MOS
 *
 */
public class InputManager {

	private InputManager() {
	}

	public static int readActionFromConsole() {
		String eingabe = "";
		Integer number;

		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out
					.print("Gib was ein (0: up, 1: right, 2: down, 3: left): ");
			eingabe = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			number = Integer.parseInt(eingabe);
		} catch (NumberFormatException e) {
			System.out.println("Gib bitte eine gültige Aktion ein");
			return readActionFromConsole();
		}

		return number;
	}

}
