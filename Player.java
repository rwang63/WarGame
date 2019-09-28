/**
 * Ruifeng Wang
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package rwang_p3EC;

/**
 * @author Ruifeng Wang
 * @version 1.0
 * Class to store information about a player, including current hand and name
 */
public class Player {

	private String name; // name of the player
	private GenericQueue<Integer> hand; // hand of cards
	
	/**
	 * Constructor
	 * @param name player's name
	 */
	public Player(String name) {
		this.name = name;
		hand = new GenericQueue<Integer>();
	}
	
	/**
	 * Copy constructor
	 * @param object2	player to be copied
	 */
	public Player(Player object2) {
		name = object2.name;
		hand = object2.hand;
	}
	
	/**
	 * Getter for the name of the player
	 * @return Player's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for the hand of the player
	 * @return Player's hand (queue)
	 */
	public GenericQueue<Integer> getQueue() {
		return hand;
	}
}
