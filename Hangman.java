package Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    // Instance variables
    private int maxGuesses; // Maximum number of incorrect guesses allowed
    private String targetWord; // The word to be guessed
    private ArrayList<Character> correctGuesses; // List of correct guesses
    private ArrayList<Character> incorrectGuesses; // List of incorrect guesses
    private ArrayList<Character> progress; // List representing the current progress of guessed word

    public static void playGame() throws FileNotFoundException {
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
    Hangman game = new Hangman(maxGuessesInput, generatedTarget);

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
    
    // Constructor to initialize the game with maximum guesses and target word
    public Hangman (int maxGuesses, String word){
        this.maxGuesses = maxGuesses;
        targetWord = word;
        correctGuesses = new ArrayList<Character>();
        incorrectGuesses = new ArrayList<Character>();
        progress = new ArrayList<Character>();
    }
    // Method to test if a guessed character is in the target word
    public boolean testChar(char guess) {
        if (targetWord.indexOf(guess) >= 0) { // Check if the guess is in the target word
            if (!correctGuesses.contains(guess)) { // Check if the guess hasn't been guessed correctly before
                correctGuesses.add(guess); // Add the guess to correct guesses list
                for (int i = 0; i < targetWord.length(); i++) { // Update the progress list with the correct guess
                    if (targetWord.charAt(i) == guess) {
                        progress.set(i, guess);
                    }
                }
                return true; // Return true for a correct guess
            }
        }
        else{ // If the guess is incorrect
            if(!incorrectGuesses.contains(guess)){// Check if the guess hasn't been guessed incorrectly before
                incorrectGuesses.add(guess);// Add the guess to incorrect guesses list
            }
            return false;// Return false for an incorrect guess
        }
        
        return false;// Return false if the guess was already made
    }
   
    // Method to check if the game is won
    public boolean isGameWon() {// Check if all letters in the target word have been guessed
        for (char letter : targetWord.toCharArray()) {
            if (!correctGuesses.contains(letter)) {
                return false;// If any letter is not guessed, return false
            }
        }
        return true;// Return true if all letters are guessed
    }

    // Method to check if the game is over (i.e., max incorrect guesses reached)
    public boolean isGameOver(){
        return incorrectGuesses.size() >= maxGuesses;// Return true if incorrect guesses exceed or equal max guesses
    }

    // Method to get the remaining guesses
    public int remainingGuesses(){
        return maxGuesses - (incorrectGuesses.size());// Calculate and return remaining guesses
    }

    // Method to get the list of correct guesses
    public List<Character> getCorrectGuesses(){
        return correctGuesses;// Return the list of correct guesses
    }

    // Method to get the list of incorrect guesses
    public List<Character> getIncorrectGuesses(){
        return incorrectGuesses;// Return the list of incorrect guesses
    }

    // Method to initialize the progress list with underscores representing unguessed letters
    public void addToProgress(){
        progress.add('_');// Add an underscore for each letter in the target word
    }

    // Method to get the current progress of the guessed word
    public List<Character> getProgress(){
        
        return progress;// Return the current progress list
    }
}


