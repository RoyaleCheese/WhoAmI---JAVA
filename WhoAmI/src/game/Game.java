package game;

import java.io.IOException;

public class Game {
	protected Player player;

	private String instruction, response;

	public Game(Player player) {
		this.player = player;
	}

	public void run() throws IOException, InterruptedException {
		// Method to do a game flow, where the player wait for instructions of server
		while (true) {
			System.out.println("");
			instruction = player.getMessager().receiveMessage();

			if (instruction.contains("request.")) {
				Utils.printIn();
				response = Utils.getString();
				player.getMessager().sendMessage(response);
			} else if (instruction.contains("print.")) {
				instruction = instruction.replaceFirst("print.", "");
				System.out.print(instruction);
			} else if (instruction.contains("printc.")) {
				instruction = instruction.replaceFirst("printc.", "");
				Utils.print(instruction);
				System.out.println();
			} else if (instruction.contains("printr.")) {
				instruction = instruction.replaceFirst("printr.", "");
				Utils.printr(instruction);
				System.out.println();
			} else if (instruction.contains("println.")) {
				instruction = instruction.replaceFirst("println.", "");
				System.out.println(instruction);
			} else if (instruction.contains("exit.")){
				player.getMessager().disconnect();
				break;
			} else {
				System.out.println("DEBUG: Instrução não especificada!");
				System.out.println("DEBUG: " + instruction);
			}
		}
	}
}
