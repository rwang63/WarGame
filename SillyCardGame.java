/**
 * Ruifeng Wang
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package rwang_p3EC;

import java.util.*;

/**
 * @author Ruifeng Wang
 * @version 1.0
 * Plays a card game where the users card is compared to the top of the discard
 * pile and an action is taken
 */
public class SillyCardGame {
	
	/**
	 * Main method for playing the card game
	 * @param args A string array containing the command line arguments
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in); // Scanner
		// Queue to store all player objects
		GenericQueue<Player> players = new GenericQueue<Player>();
		int playerCount; // number of players
		String continuePlaying = "yes"; // tracks if players want to continue

		printWelcome();
		while(!continuePlaying.equals("no")) {
			playerCount = getPlayerCount(keyboard);
			players = getPlayerNames(keyboard, playerCount);	
			GameModel game = new GameModel(players, playerCount);
			
			playSingleGame(game, keyboard);
			
			System.out.println("The game has finished.\n");
			System.out.print("Play again (no to quit): ");
			continuePlaying = keyboard.next();
			keyboard.nextLine();
			System.out.println();
		}

		printGoodbye();
		keyboard.close();
	}
	
	/**
	 * Prints out the goodbye message for the user once the game ends
	 */
	private static void printGoodbye() {
		System.out.println("Thanks for playing this Silly Card Game!");
		System.out.println("We hope you enjoyed your game.");
		System.out.println("Please come back again soon!");
	}
	
	/**
	 * Prints out the welcome message introducing the user to the game
	 */
	private static void printWelcome() {
		System.out.println("Welcome to this Silly Card Game!");
		System.out.println("This game is played with two to six people "
				+ "and will run until one player has no cards left in their "
				+ "hand.");
		System.out.println("Once that happens, that player is declared the "
				+ "winner.");
		System.out.println("Please play as many times as you would like!\n");		
		
	}
	
	/**
	 * Asks how many total players, between 2 and 6, will be participating
	 * @param keyboard Scanner
	 * @return Integer number of players
	 */
	private static int getPlayerCount(Scanner keyboard) {
		int playerCount = 0;
		while(playerCount > 6 || playerCount < 2) {
			System.out.print("How many players? (Between 2 - 6): ");
			playerCount = keyboard.nextInt();
			keyboard.nextLine();
		}
		return playerCount;
	}
	
	/**
	 * Gets the player names
	 * @param keyboard 			Scanner
	 * @param playerCount		Total number of players
	 * @return					Queue of players
	 */
	private static GenericQueue<Player> getPlayerNames(Scanner keyboard, 
			int playerCount) {
		String name;
		GenericQueue<Player> players = new GenericQueue<Player>();
		for(int i = 0; i < playerCount; i++) {
			System.out.print("Player name? ");
			name = keyboard.next();
			players.enqueue(new Player(name));
			keyboard.nextLine();
		}
		System.out.println();
		return players;
	}
	
	/**
	 * Plays a single game of the Silly Card Game
	 * @param game			Current game object
	 * @param keyboard		Scanner
	 */
	private static void playSingleGame(GameModel game, Scanner keyboard) {
		while(!game.getPlayers().peek().getQueue().empty()) {
			if(game.getDealStack().empty()) {
				game.refillDealStack();
			} else {
				System.out.println(game.getPlayers().peek().getName() 
						+ "'s turn, cards: ");
				printGame(game, keyboard);	
			}
		}
	}
	
	/**
	 * Prints out the current move for the game
	 * @param game			Current game object
	 * @param keyboard		Scanner
	 */
	private static void printGame(GameModel game, Scanner keyboard) {
		System.out.println(game.getPlayers().peek().getQueue());
		System.out.println("Discard pile card: " + game.getDiscardStackTop());
		System.out.println("Your current card: " 
							+ game.getPlayers().peek().getQueue().peek());
		System.out.print("Press RETURN to take a turn.");
		keyboard.nextLine();
		System.out.println(game.playerMove());
		System.out.println();
	}

}
