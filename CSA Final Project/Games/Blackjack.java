//Text-based blackjack game
//Curtis Lu 5.13.24 Last Edit: 5.13.24 9:46AM

 package Games;

import java.util.ArrayList;
import java.util.Scanner;
 
 public class Blackjack{
     
     // Declaring necessary variables and data structures
     static ArrayList<Integer> deck = new ArrayList<Integer>(); // Represents the deck of cards
     static ArrayList<Integer> playerHand = new ArrayList<Integer>(); // Stores the player's hand
     static ArrayList<Integer> computerHand = new ArrayList<Integer>(); // Stores the computer's hand
     static Scanner scan = new Scanner(System.in); // Scanner for user input
     static int winCount = 0; // Count of wins
     static int loseCount = 0; // Count of losses
     static int tieCount = 0; // Count of ties
     
     // Main method to play multiple games
     public static void playMultipleGames() {
         boolean keepPlaying = true;
         while (keepPlaying) {
             System.out.println("Do you want to play blackjack? (Y/N)");
             resetGame(); // Resetting the game state
             String choice = scan.next();
             if (choice.equalsIgnoreCase("Y")) {
                 playGame(); // Start a new game
             } else {
                 System.out.println("Your win loss tie ratio is: " + "W: " + winCount + " L: " + loseCount + " T: " 
                 + tieCount + "\n************************************");
                 keepPlaying = false;
             }
         }
     }
 
     // Method to play a single game
     public static void playGame() {
         boolean result = true;
        //Only generate deck if 50% of deck is used/deck is empty (looked up casino rules)
         if(deck.size()<=26){
             generateDeck(); // Generating a new deck of cards
         }
         generateHand(); // Dealing initial hands
         while (result) {
            //Check if first hand is blackjack for player
            if(getSum(playerHand)==21){
                wait(850);
                System.out.println("Blackjack!" + "\n************************************"); // Player gets a Blackjack
                winCount++;
                result = false;
                break;
            }
             System.out.println("What do you want to do? \n [1] - Hit \n [2] - Stay");
             wait(850); // Adding a slight delay for better user experience
             int input = scan.nextInt();
             switch (input) {
                 case 1:
                     hitCard("player"); // Player chooses to hit
                     if (getSum(playerHand) == 21) {
                         wait(850);
                         System.out.println("Blackjack!" + "\n************************************"); // Player gets a Blackjack
                         winCount++;
                         result = false;
                     } else if (getSum(playerHand) > 21) {
                         wait(1000);
                         System.out.println("Bust! You Lose!" + "\n************************************"); // Player busts
                         loseCount++;
                         result = false;
                     }
                     break;
                 case 2:
                     System.out.println("Dealer's hand: " + computerHand + " Total: " + getSum(computerHand) 
                     + "\n************************************");
                     //check if initial dealer hand is blackjack
                     if(getSum(computerHand)==21){
                        wait(850);
                        System.out.println("Blackjack for dealer!" + "\n************************************"); // Player gets a Blackjack
                        loseCount++;
                        result = false;
                        break;
                     }
                     while (getSum(computerHand) <= getSum(playerHand)) {
                         if (getSum(computerHand) == getSum(playerHand)) {
                             System.out.println("Tie!" + "\n************************************"); // Player and dealer have the same total
                             tieCount++;
                             result = false;
                             break;
                         }
                         wait(850); // Adding a slight delay for better user experience
                         hitCard("computer"); // Dealer chooses to hit
                     }
                     if (getSum(computerHand) > 21) {
                         wait(850);
                         System.out.println("Dealer Busted! Player Wins!" + "\n************************************"); // Dealer busts
                         winCount++;
                         result = false;
                     } else if (getSum(computerHand) > getSum(playerHand)) {
                         wait(850);
                         System.out.println("Dealer won!" + " Dealer total: " + getSum(computerHand)
                        + "\n************************************"); // Dealer wins
                         loseCount++;
                         result = false;
                     }
                     break;
             }
         }
         wait(850);
         System.out.println("Wins: " + winCount + ", Losses: " + loseCount + ", Ties: " + tieCount 
         + "\n************************************"); // Displaying game results
     }
         
     // Method to generate a deck of cards
     public static void generateDeck() {
         for (int i = 1; i <= 13; i++) {
             // Ace is represented as 1, Jack as 11, Queen as 12, King as 13
             int cardValue;
             if (i >= 11 && i <= 13) {
                 cardValue = 10; // Jack, Queen, King
             } else {
                 cardValue = i; // Number cards (Ace to 10)
             }
             
             // Add each card value four times for each suit
             for (int j = 0; j < 4; j++) {
                 deck.add(cardValue);
             }
         }
     }
 
     // Method to generate initial hands for the player and the dealer
     public static void generateHand() {
         for (int i = 0; i < 2; i++) {
             int randomIndex = (int) (Math.random() * deck.size()); 
             playerHand.add(deck.get(randomIndex));
             if(deck.get(randomIndex)==1 && getSum(playerHand) + 10 <= 21){
                playerHand.set(playerHand.size() - 1, 11);
             }
             deck.remove(randomIndex);
             randomIndex = (int) (Math.random() * deck.size()); 
             computerHand.add(deck.get(randomIndex)); 
             if(deck.get(randomIndex)==1 && getSum(playerHand) + 10 <= 21){
                playerHand.set(playerHand.size() - 1, 11);
             }
             deck.remove(randomIndex);
         }
         System.out.println("Your hand: " + playerHand + " | " + "Your Total: " + getSum(playerHand)
          + "\n************************************");
     }
 
     // Method to draw a card for either the player or the dealer
     public static void hitCard(String name) {
         if (name.equals("player")) {
             int randomIndex = (int) (Math.random() * deck.size());
             int cardValue = deck.get(randomIndex);
             playerHand.add(cardValue);
             deck.remove(randomIndex);
 
             // Check if the drawn card is an Ace and if converting it to 11 will not cause bust
             if (cardValue == 1 && getSum(playerHand) + 10 <= 21) {
                 playerHand.set(playerHand.size() - 1, 11); // Convert Ace to 11
             }
             System.out.println("Your new hand: " + playerHand + " | " + "Your total: " + getSum(playerHand)
              + "\n************************************");
         } else {
             int randomIndex = (int) (Math.random() * deck.size());
             int cardValue = deck.get(randomIndex);
             computerHand.add(cardValue);
             deck.remove(randomIndex);  
 
             // Check if the drawn card is an Ace and if converting it to 11 will not cause bust
             if (cardValue == 1 && getSum(computerHand) + 10 <= 21) {
                 computerHand.set(computerHand.size() - 1, 11); // Convert Ace to 11
             }
             System.out.println("Dealer's new hand: " + computerHand + " | " + "Dealer's total: " + getSum(computerHand) 
             + "\n************************************");
         }
     }
 
     // Method to calculate the sum of card values in a given hand
     public static int getSum(ArrayList<Integer> arr) {
         int sum = 0;
         for (int i = 0; i < arr.size(); i++) {
             sum += arr.get(i);
         }
         return sum;
     }
     
     //Copied method from internet to add delay to program
     // Method to add a delay in milliseconds
     public static void wait(int ms) {
         try {
             Thread.sleep(ms);
         } catch (InterruptedException ex) {
             Thread.currentThread().interrupt();
         }
     }
 
     // Method to reset the game state
     public static void resetGame() {
        //if deck is less than 50% full shuffle the deck (according to casino rules)
        if(deck.size()<=26){
            deck.clear(); // Clear the deck before generating a new one
        }
         playerHand.clear(); // Clear player's hand before generating a new one
         computerHand.clear(); // Clear computer's hand before generating a new one
     }
 }
 