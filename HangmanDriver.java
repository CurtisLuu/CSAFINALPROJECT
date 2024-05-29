package Hangman;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class HangmanDriver {
    public static void main(String[] args) throws FileNotFoundException {
        int maxGuessesInput;
        int targetIndex;
        char guess = '1';//Initial guess character (placeholder)
        String generatedTarget;
        
        // ArrayList to store words from the file
        ArrayList<String> line1 = new ArrayList<String>();
        
        // Scanner to read from the file
        Scanner fileScan;
        fileScan = new Scanner(new File("C:\\newCSA\\Hangman\\listOfWords.txt"));
        
        // Read each line from the file and add it to the ArrayList
        while (fileScan.hasNext()) {
            line1.add(fileScan.nextLine());
    }

    // Generate a random index to select a target word from the list
    Random random = new Random();
    targetIndex = random.nextInt(162) + 1;
    generatedTarget = line1.get(targetIndex); // Get the word at the random index
    
    // Welcome message and prompt for the number of allowed errors
    System.out.println("Welcom to Hangman. You will be prompted to enter the amount of errors you are allowed.\nEnter 0 at anytime to quit!");
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the number of chances: ");
    maxGuessesInput = scan.nextInt();// Read the number of allowed guesses

    // Initialize the Man (Hangman) game with the target word and max guesses
    Man game = new Man(maxGuessesInput, generatedTarget);

    // Initialize the progress with blanks for each letter in the target word
    for (int i = 0; i < generatedTarget.length(); i++){
        game.addToProgress();
    }

    // Print the initial progress (all blanks)
    System.out.println(game.getProgress());

    // Main game loop
    while (!game.isGameOver() && !game.isGameWon() && guess != '0') {
        // Prompt for the player's guess
        System.out.print("\nEnter your guess: ");
        guess = scan.next().charAt(0);
       
        // Check if the guessed character is in the target word
        if (game.testChar(guess)) {
            System.out.println("Good guess!");
        } else {
            System.out.println("Incorrect guess.");
        }

        // Display the current game state
        System.out.println("Correct Guesses: " + game.getCorrectGuesses());
        System.out.println("Incorrect guesses: " + game.getIncorrectGuesses());
        System.out.println("Chances: " + game.remainingGuesses());
        System.out.println("Current Progress: " + game.getProgress());
    }

    // End of game messages
    if (game.isGameWon()) {
        System.out.println("\nCongratulations! You've guessed the word: " + generatedTarget);
    } else {
        System.out.println("\nGame over! The word was: " + generatedTarget);
    }

    scan.close();
}
}
