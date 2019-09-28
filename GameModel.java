/**
 * Ruifeng Wang
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package rwang_p3EC;

import java.util.ArrayList;
import java.util.Random;

import rwang_p3.GenericStack;

/**
 * @author Ruifeng Wang
 * @version 1.0
 * Plays a card game where the users card is compared to the top of the discard
 * pile and an action is taken
 */
public class GameModel {
	
	// Stack that holds the cards left to be dealt
	private GenericStack<Integer> dealStack = new GenericStack<Integer>(52);
	// Stack that holds the discard pile
	private GenericStack<Integer> discardStack = new GenericStack<Integer>(52);
	// Holds all possible cards
	private ArrayList<Integer> cards = new ArrayList<>();
	// All players in the game
	private GenericQueue<Player> players = new GenericQueue<Player>();
	// Total number of players in the game
	private int playerCount;

	/**
	 * Constructor
	 * @param players		Queue of current players in the game
	 * @param playerCount	Total number of players in the game
	 */
	public GameModel(GenericQueue<Player> players, int playerCount) {
		this.players = players;
		this.playerCount = playerCount;
		generateDeck();
		dealInitialHand();
	}
	
	/**
	 * Gets the entire deal stack
	 * @return Deal stack
	 */
	public GenericStack<Integer> getDealStack() {
		return dealStack;
	}
	
	/**
	 * Looks at the top of the discard stack
	 * @return Top of the discard stack
	 */
	public int getDiscardStackTop() {
		return discardStack.peek();
	}
	
	/**
	 * Gets queue of all players
	 * @return Queue of players
	 */
	public GenericQueue<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Performs 1 move for the current player
	 * @return	Message for the move the player made
	 */
	public String playerMove() {
		Player currentPlayer = players.peek();
		if(currentPlayer.getQueue().peek() > discardStack.peek()) {
			discardStack.push(currentPlayer.getQueue().dequeue());
			if(checkWinner(currentPlayer)) {
				return "You have won the game!";
			} else {
				players.dequeue();
				players.enqueue(currentPlayer);
				return "Your card is HIGHER, turn is over";
			}
		} else if(currentPlayer.getQueue().peek() == discardStack.peek()) {
			discardStack.push(currentPlayer.getQueue().dequeue());
			currentPlayer.getQueue().enqueue(dealStack.pop());
			if(checkWinner(currentPlayer)) {
				return "You have won the game!";
			} else {
				players.dequeue();
				players.enqueue(currentPlayer);
				return "Your card is equal, pick up 1 card";
			}
		} else if(currentPlayer.getQueue().peek() < discardStack.peek() 
				&& dealStack.stackSize() == 1) {
			discardStack.push(currentPlayer.getQueue().dequeue());
			currentPlayer.getQueue().enqueue(dealStack.pop());
			refillDealStack();
			currentPlayer.getQueue().enqueue(dealStack.pop());
			if(checkWinner(currentPlayer)) {
				return "You have won the game!";
			} else {
				players.dequeue();
				players.enqueue(currentPlayer);
				return "Your card is LOWER, pick up 2 cards";
			}
		} else {
			discardStack.push(currentPlayer.getQueue().dequeue());
			currentPlayer.getQueue().enqueue(dealStack.pop());
			currentPlayer.getQueue().enqueue(dealStack.pop());
			if(checkWinner(currentPlayer)) {
				return "You have won the game!";
			} else {
				players.dequeue();
				players.enqueue(currentPlayer);
				return "Your card is LOWER, pick up 2 cards";
			}
		}
	}
	
	/**
	 * Refills the deal stack by flipping the discard stack when the deal stack
	 * is empty
	 */
	public void refillDealStack() {
		int discardTop = discardStack.pop();
		while(!discardStack.empty()) {
			dealStack.push(discardStack.pop());
		}
		discardStack.push(discardTop);
	}
	
	/**
	 * Checks if either player has won on the current turn
	 * @param player 	Which players hand to check
	 * @return			true if winner, false if not
	 */
	private boolean checkWinner(Player currentPlayer) {
		return currentPlayer.getQueue().empty();
	}
	
	/**
	 * Deals the initial hand to the players
	 */
	private void dealInitialHand() {
		Player currentPlayer;
		for(int i = 0; i < playerCount * 7; i++) {
			currentPlayer = players.dequeue();
			currentPlayer.getQueue().enqueue(dealStack.pop());
			players.enqueue(currentPlayer);
		}
		discardStack.push(dealStack.pop());
	}
	
	/**
	 * Generates all cards of the deck
	 * @return All of the cards
	 */
	private ArrayList<Integer> generateAllCards() {
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < 14; j++) {
				cards.add(j%14);
			}
		}
		return cards;
	}
	
	/**
	 *	Takes all the cards and shuffles them into the stack, thereby generating
	 *	the deck of usable cards
	 */
	private void generateDeck() {
		cards = generateAllCards();
		shuffleDeck(cards);
		for(int i = 0; i < cards.size(); i++) {
			this.dealStack.push(cards.get(i));
		}
	}
	
	/**
	 * Shuffles the cards using the
	 * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">
	 * Fisher-Yates algorithm</a>
	 * @param cards deck to shuffle
	 */
	private void shuffleDeck(ArrayList<Integer> cards) {
	    Random rand = new Random();
	    for (int i = cards.size(); i > 1; i--) {
	        int j = rand.nextInt(i);
	        int temp = cards.get(i - 1);
	        cards.set(i - 1, cards.get(j));
	        cards.set(j, temp);
	    }
	} 
	
	
}
