package Hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Man {
    // Instance variables
    private int maxGuesses; // Maximum number of incorrect guesses allowed
    private String targetWord; // The word to be guessed
    private ArrayList<Character> correctGuesses; // List of correct guesses
    private ArrayList<Character> incorrectGuesses; // List of incorrect guesses
    private ArrayList<Character> progress; // List representing the current progress of guessed word
    
    // Constructor to initialize the game with maximum guesses and target word
    public Man (int maxGuesses, String word){
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


